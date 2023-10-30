package net.baloby.mcrpg.world.structures;

import com.mojang.serialization.Codec;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.baloby.mcrpg.world.StructureGen;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class UniqueFeature<C extends IFeatureConfig> extends Structure<C> {


    private HashMap<NpcType, BlockPos> npcMap = new HashMap<>();
    protected ResourceLocation PIECE;

    @Override
    public GenerationStage.Decoration step(){
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public UniqueFeature(Codec codec) {
        super(codec);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom,
                                     int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, IFeatureConfig featureConfig) {
        ServerWorld world = ModWorldEvents.getServer().overworld();
        if (world.getCapability(UniqueFeaturesCapabilityProvider.FEATURES_CAP).resolve()
                .get().getFeatures().contains(this.getName())) return false;
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG);
        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
        return topBlock.getFluidState().isEmpty();
    }

    public String getName(){
        return "";
    }

    public HashMap<NpcType, BlockPos> getNpcMap(){return npcMap;}

    public void addNpc(NpcType type,BlockPos pos){
        npcMap.put(type, pos);
    }

    public void addNpcs(ServerWorld world,BlockPos pos,Rotation rotation){
        for (Map.Entry<NpcType,BlockPos> entry : getNpcMap().entrySet()){
            Npc npc = entry.getKey().listCreate();
            BlockPos rotationOffset = entry.getValue().rotate(rotation);
            npc.setHome(world,pos.offset(rotationOffset));
            npc.setDirty();
        }
    }

    public static class Start extends StructureStart<NoFeatureConfig>{
        private UniqueFeature unique;
        public Start(UniqueFeature structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn){
            super(structureIn,chunkX,chunkZ,mutableBoundingBox,referenceIn,seedIn);
            this.unique = structureIn;

        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator,
                                   TemplateManager manager, int chunkX, int chunkZ,
                                   Biome biomeIn, NoFeatureConfig config) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int y = generator.getBaseHeight(x,z, Heightmap.Type.WORLD_SURFACE);
            BlockPos pos = new BlockPos(x,y-2, z);
            ServerWorld world = ModWorldEvents.getServer().overworld();
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            StructureGen.placeManually(pos,world,unique.PIECE,rotation);
            world.getCapability(UniqueFeaturesCapabilityProvider.FEATURES_CAP).resolve().get().addFeature(unique.getName(), pos);
            unique.addNpcs(world,pos,rotation);
        }
    }
}
