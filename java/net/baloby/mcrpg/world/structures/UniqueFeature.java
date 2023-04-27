package net.baloby.mcrpg.world.structures;

import com.mojang.serialization.Codec;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
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

import java.util.HashMap;

public abstract class UniqueFeature<C extends IFeatureConfig> extends Structure<C> {


    private HashMap<BlockPos, NpcType> npcMap = new HashMap<>();

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
        ServerWorld world = Minecraft.getInstance().getSingleplayerServer().overworld();
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

    public HashMap getNpcMap(){return npcMap;}

    public void addNpc(BlockPos pos, ServerWorld world, NpcType type){
        npcMap.put(pos, type);
        Npc npc = type.listCreate();
        npc.setHome(world, pos);
        npc.setDirty();
    }

    public static class Start extends StructureStart<NoFeatureConfig>{
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn, String name, HashMap<BlockPos, NpcType> npcMap){
            super(structureIn,chunkX,chunkZ,mutableBoundingBox,referenceIn,seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator,
                                   TemplateManager manager, int chunkX, int chunkZ,
                                   Biome biomeIn, NoFeatureConfig config) {
            ServerWorld world = Minecraft.getInstance().getSingleplayerServer().overworld();
        }
    }
}
