package net.baloby.mcrpg.world.structures;

import com.ibm.icu.impl.Pair;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.baloby.mcrpg.world.StructureGen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class LocationStructure extends UniqueFeature{

    protected Map<NpcType, NpcOffset> positions = new HashMap<>();
    protected ArrayList<Building> buildings = new ArrayList<>();


    public LocationStructure() {
        super(NoFeatureConfig.CODEC);
    }

    public Map<NpcType, NpcOffset> getPositions(){return positions;}

    public void addBuilding(Building building){
        this.buildings.add(building);
    }

    public void addNpc(NpcType npc, NpcOffset offset){
        this.positions.put(npc,offset);
    }

    public void addNpcs(ServerWorld world, Vector3d pos, Rotation rotation){
        for (Map.Entry<NpcType,NpcOffset> entry : getPositions().entrySet()){
            Npc npc = entry.getKey().listCreate();
            entry.getValue().rotatePos(rotation);
            BlockPos building = entry.getValue().getBuilding().getPos();
            Vector3d rotationOffset = (entry.getValue().getPos()).add(building.getX(),building.getY(),building.getZ());
            npc.setHome(world,pos.add(rotationOffset.x(),rotationOffset.y(),rotationOffset.z()));
            npc.setDirty();
        }
    }

    public abstract static class Start extends StructureStart<NoFeatureConfig>{

        protected Heightmap.Type height;
        protected LocationStructure locationStructure;

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed, Heightmap.Type height) {
            super(structureIn, chunkX, chunkZ, boundingBox, reference, seed);
            if(structureIn instanceof LocationStructure) this.locationStructure = (LocationStructure) structureIn;
            this.height = height;
        }

        public Heightmap.Type getHeight(){return this.height;}

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {

            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int y = generator.getBaseHeight(x,z, this.height);
            BlockPos pos = new BlockPos(x,y, z);
            ServerWorld world = ModWorldEvents.getServer().overworld();
            for(Building building : locationStructure.buildings){
                BlockPos rotationOffset = building.getPos().rotate(building.getRotation()).offset(pos);
                BlockPos newPos = new BlockPos(rotationOffset.getX(),generator.getBaseHeight(rotationOffset.getX(),rotationOffset.getZ(),this.height)-1, rotationOffset.getZ());
                StructureGen.placeManually(newPos,world,building.getPiece(),building.getRotation());
            }


            if(this.getFeature()instanceof UniqueFeature) {
                world.getCapability(UniqueFeaturesCapabilityProvider.FEATURES_CAP).resolve().get().addFeature(((UniqueFeature<?>) this.getFeature()).getName(), pos);
                }

            if(this.getFeature()instanceof LocationStructure){
                Map<NpcType, NpcOffset> map = ((LocationStructure) this.getFeature()).getPositions();
                for (Map.Entry<NpcType, NpcOffset> entry : map.entrySet()) {
                    Npc npc = entry.getKey().listCreate();
                    BlockPos rotationOffset = entry.getValue().getBuilding().getPos().rotate(entry.getValue().getBuilding().getRotation()).offset(pos);
                    BlockPos newPos = new BlockPos(rotationOffset.getX(),generator.getBaseHeight(rotationOffset.getX(),rotationOffset.getZ(),this.height), rotationOffset.getZ());
                    entry.getValue().rotatePos(entry.getValue().getBuilding().getRotation());
                    npc.setHome(world,entry.getValue().getPos().add(newPos.getX(),newPos.getY(),newPos.getZ()));
                    npc.setDirty(true);
                }
            }
        }
        public LocationStructure getStructure(){return locationStructure;}
    }
}
