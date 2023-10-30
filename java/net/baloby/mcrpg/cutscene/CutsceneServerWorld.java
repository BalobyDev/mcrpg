package net.baloby.mcrpg.cutscene;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.datafix.codec.DatapackCodec;
import net.minecraft.world.*;
import net.minecraft.world.chunk.listener.IChunkStatusListener;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.ISpecialSpawner;
import net.minecraft.world.storage.IServerWorldInfo;
import net.minecraft.world.storage.SaveFormat;

import java.util.List;
import java.util.concurrent.Executor;

public class CutsceneServerWorld extends ServerWorld {

    public static WorldSettings CUTSCENE_SETTINGS = new WorldSettings("Cutscene World", GameType.NOT_SET,false, Difficulty.EASY, true, new GameRules(), DatapackCodec.DEFAULT);

    public CutsceneServerWorld(MinecraftServer p_i241885_1_, Executor p_i241885_2_, SaveFormat.LevelSave p_i241885_3_, IServerWorldInfo p_i241885_4_, RegistryKey<World> p_i241885_5_, DimensionType p_i241885_6_, IChunkStatusListener p_i241885_7_, ChunkGenerator p_i241885_8_, boolean p_i241885_9_, long p_i241885_10_, List<ISpecialSpawner> p_i241885_12_, boolean p_i241885_13_) {
        super(p_i241885_1_, p_i241885_2_, p_i241885_3_, p_i241885_4_, p_i241885_5_, p_i241885_6_, p_i241885_7_, p_i241885_8_, p_i241885_9_, p_i241885_10_, p_i241885_12_, p_i241885_13_);
    }
}
