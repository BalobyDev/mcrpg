package net.baloby.mcrpg.world.structures;

import com.mojang.serialization.Codec;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueStructuresCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

public abstract class UniqueStructure<C extends IFeatureConfig> extends Structure<C> {


    public static Codec<UniqueStructure> CODEC;


    protected ResourceLocation location = new ResourceLocation("");

    @Override
    public GenerationStage.Decoration step(){
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public UniqueStructure(Codec codec) {
        super(codec);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom,
                                     int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, IFeatureConfig featureConfig) {
        ServerWorld world = ModWorldEvents.getServer().overworld();
        if (world.getCapability(UniqueStructuresCapabilityProvider.STRUCTURES_CAP).resolve()
                .get().getStructures().contains(this.getName())) return false;
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG);
        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
        return topBlock.getFluidState().isEmpty();
    }

    public String getName(){
        return location.toString();
    }


    public static class Start extends StructureStart<NoFeatureConfig>{
        private UniqueStructure unique;
        public Start(UniqueStructure structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn){
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
            BlockPos pos = new BlockPos(x,y,z);
            JigsawManager.addPieces(
                    dynamicRegistries,new VillageConfig(()-> dynamicRegistries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(new ResourceLocation(unique.location.getNamespace(),unique.location.getPath()+"/start_pool")),
                            10), AbstractVillagePiece::new,generator,manager,pos,this.pieces,this.random,false,false
            );
            this.calculateBoundingBox();
            ServerWorld world = ModWorldEvents.getServer().overworld();
            world.getCapability(UniqueStructuresCapabilityProvider.STRUCTURES_CAP).resolve().get().addStructure(unique.location.toString(),pos);
            mcrpg.LOGGER.log(Level.DEBUG,unique.location.toString()+"  spawned at "+pos.getX()+", "+pos.getY()+", "+pos.getZ());

        }
    }
}
