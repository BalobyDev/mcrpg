package net.baloby.mcrpg.world.structures;

import com.ibm.icu.impl.Pair;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;
import java.util.Map;

public abstract class LocationStructure extends UniqueFeature{

    private Map<NpcType, NpcOffset> positions = new HashMap<>();

    public LocationStructure() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public IStartFactory getStartFactory() {
        return null;
    }

    public Map<NpcType, NpcOffset> getPositions(){return positions;}

    public abstract static class Start extends StructureStart<NoFeatureConfig>{

        private Heightmap.Type height;
        private LocationStructure locationStructure;

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

            ServerWorld world = Minecraft.getInstance().getSingleplayerServer().overworld();
            if(this.getFeature()instanceof UniqueFeature) {

                world.getCapability(UniqueFeaturesCapabilityProvider.FEATURES_CAP).resolve().get().addFeature(((UniqueFeature<?>) this.getFeature()).getName(), pos);

                }
            this.calculateBoundingBox();

            if(this.getFeature()instanceof LocationStructure){
                Map<NpcType, NpcOffset> map = ((LocationStructure) this.getFeature()).getPositions();
                for (Map.Entry<NpcType, NpcOffset> entry : map.entrySet()) {
                    Npc npc = entry.getKey().listCreate();
                    npc.setHome(world,entry.getValue().getStructurePos().offset(entry.getValue().getPos().rotate(entry.getValue().getRotation())));
                    npc.setDirty(true);
                }
            }
        }
        public LocationStructure getStructure(){return locationStructure;}
    }
}
