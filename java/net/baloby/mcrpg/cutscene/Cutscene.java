package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Lifecycle;
import net.baloby.mcrpg.setup.ModDimensions;
import net.baloby.mcrpg.tools.Teleport;
import net.baloby.mcrpg.world.ArenaChunkGenerator;
import net.baloby.mcrpg.world.CutsceneChunkGenerator;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.ServerPropertiesProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.listener.TrackingChunkStatusListener;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.ServerWorldInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cutscene {

    public ServerWorld world;
    public DimensionType dimType;
    public CutsceneChunkGenerator chunkGen;
    public RegistryKey<World> regKey;

    public Cutscene(CompoundNBT nbt, DimensionType dimType, RegistryKey<World> regKey){
        this.dimType = dimType;
    }

    public void loadCutscene(ServerPlayerEntity player, RegistryKey<World> regKey){

        Minecraft minecraft = Minecraft.getInstance();
        SaveFormat format = new SaveFormat(minecraft.gameDirectory.toPath().resolve("saves"), minecraft.gameDirectory.toPath().resolve("backups"), DataFixesManager.getDataFixer());

        ServerWorldInfo info = new ServerWorldInfo(CutsceneServerWorld.CUTSCENE_SETTINGS,new ServerPropertiesProvider(DynamicRegistries.builtin(), Paths.get("server.properties")).getProperties().worldGenSettings, Lifecycle.stable());
        try {
            world = new CutsceneServerWorld(player.getServer(), Util.backgroundExecutor(), format.createAccess(""),info,regKey, this.dimType,new TrackingChunkStatusListener(0), chunkGen,false,0,new ArrayList<>(),false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Teleport.teleport(player,world,new BlockPos(0,0,0));
    }
}
