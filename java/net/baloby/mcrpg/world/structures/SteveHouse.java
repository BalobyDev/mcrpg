package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
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
    }


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }


    @Override
    public String getName() {
        return "steve_house";
    }


    public static class Start extends StructureStart<NoFeatureConfig>{
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn){
            super(structureIn,chunkX,chunkZ,mutableBoundingBox,referenceIn,seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator,
                                   TemplateManager manager, int chunkX, int chunkZ,
                                   Biome biomeIn, NoFeatureConfig config) {

            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int y = generator.getBaseHeight(x,z, Heightmap.Type.WORLD_SURFACE);
            BlockPos pos = new BlockPos(x,y, z);
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            SteveHousePiece.start(manager,pos,rotation,this.pieces,random);
            ServerWorld world = Minecraft.getInstance().getSingleplayerServer().overworld();
            world.getCapability(UniqueFeaturesCapabilityProvider.FEATURES_CAP).resolve().get().addFeature("steve_house",pos);
            this.calculateBoundingBox();

            Npc npc = Npcs.STEVE.get().listCreate();
            BlockPos rotationOffset = new BlockPos(7,1,5).rotate(rotation);
            npc.setHome(world,pos.offset(rotationOffset));
            npc.setDirty(true);
        }
    }
}
