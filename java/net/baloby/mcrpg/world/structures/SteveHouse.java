package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.Level;

public class SteveHouse extends UniqueFeature {


    public SteveHouse() {
        super(NoFeatureConfig.CODEC);
        this.PIECE = new ResourceLocation(mcrpg.MODID,"steve_house");
        this.addNpc(Npcs.STEVE.get(), new Vector3d(7,1,5));
    }


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return (structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn) -> new Start((UniqueFeature) structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }


    @Override
    public String getName() {
        return "steve_house";
    }


    public static class Start extends UniqueFeature.Start{
        public Start(UniqueFeature structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn){
            super(structureIn,chunkX,chunkZ,mutableBoundingBox,referenceIn,seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator,
                                   TemplateManager manager, int chunkX, int chunkZ,
                                   Biome biomeIn, NoFeatureConfig config) {
            super.generatePieces(dynamicRegistries, generator, manager, chunkX, chunkZ, biomeIn, config);

        }
    }
}
