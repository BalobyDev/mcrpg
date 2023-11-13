package net.baloby.mcrpg.world;

import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.structures.ConfigStructures;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Random;
import java.util.Set;

public class StructureGen {

    //what if i made it jigsaw but override the method
    public static void placeManually(BlockPos pos, World world, ResourceLocation name, Rotation rotation){
        world.getServer().submitAsync(()->{ServerWorld serverWorld = (ServerWorld) world;
            TemplateManager manager = serverWorld.getStructureManager();
            ResourceLocation loc = name;
            Template template = manager.get(loc);

            if(template != null){
                BlockState blockState = world.getBlockState(pos);
                world.setBlockAndUpdate(pos, blockState);
                PlacementSettings settings = (new PlacementSettings()).setMirror(Mirror.NONE)
                        .setRotation(rotation).setIgnoreEntities(true).setChunkPos((ChunkPos) null);
                template.placeInWorldChunk(serverWorld, pos, settings,new Random());};
        });
    }
}
