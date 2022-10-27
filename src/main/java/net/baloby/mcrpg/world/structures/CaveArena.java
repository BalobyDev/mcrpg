package net.baloby.mcrpg.world.structures;

import com.mojang.serialization.Codec;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.Level;

public class CaveArena extends Structure<NoFeatureConfig> {

    public CaveArena() {super(NoFeatureConfig.CODEC);}

    private static final ResourceLocation ARENA = new ResourceLocation(mcrpg.MODID, "cave_arena");


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return CaveArena.Start::new;
    }

    @Override
    public GenerationStage.Decoration step(){return GenerationStage.Decoration.SURFACE_STRUCTURES;}

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom,
                                     int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {

        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
    }



    public static class Start extends StructureStart<NoFeatureConfig>{
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }
        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator,
                                   TemplateManager templateManagerIn, int chunkX, int chunkZ,
                                   Biome biomeIn, NoFeatureConfig config) {

            int x = chunkX*16;
            int z = chunkZ*16;
            BlockPos pos = new BlockPos(x,5,z);

            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            CaveArenaPieces.start(templateManagerIn, pos, rotation, this.pieces, this.random);

            JigsawManager.addPieces(dynamicRegistries,
            new VillageConfig(()->dynamicRegistries.registry(Registry.TEMPLATE_POOL_REGISTRY)
            .get().get(new ResourceLocation(mcrpg.MODID, "cave_arena/start_pool")),
                    5), AbstractVillagePiece::new,chunkGenerator,templateManagerIn,
            pos,this.pieces,this.random,false,true);





            this.calculateBoundingBox();

            mcrpg.LOGGER.log(Level.DEBUG,"Cave arena spawned!");



        }
    }


}
