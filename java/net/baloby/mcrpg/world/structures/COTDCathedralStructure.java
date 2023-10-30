package net.baloby.mcrpg.world.structures;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class COTDCathedralStructure extends LocationStructure{

    public COTDCathedralStructure() {
        super();
    }

    @Override
    public String getName(){return "cathedral";}

    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, IFeatureConfig featureConfig) {
        return chunkGenerator.hasStronghold(chunkPos)&&super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
    }

    public static class Start extends LocationStructure.Start {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
            super(structureIn, chunkX, chunkZ, boundingBox, reference, seed, Heightmap.Type.WORLD_SURFACE);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int y = generator.getBaseHeight(x,z, this.getHeight());
            BlockPos pos = new BlockPos(x,y, z);
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            COTDCathedralPieces.start(this.getStructure(), manager,pos,rotation,this.pieces,random);
            super.generatePieces(dynamicRegistries,generator,manager,chunkX,chunkZ,biome,noFeatureConfig);

        }
    }
}
