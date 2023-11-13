package net.baloby.mcrpg.cutscene;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Lifecycle;
import net.baloby.mcrpg.setup.ModDimensions;
import net.baloby.mcrpg.tools.Teleport;
import net.baloby.mcrpg.world.ArenaChunkGenerator;
import net.baloby.mcrpg.world.CutsceneChunkGenerator;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.command.impl.LocateBiomeCommand;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.ServerPropertiesProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.listener.TrackingChunkStatusListener;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DebugChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.ServerWorldInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cutscene {

    public ServerWorld world;
    public ClientWorld clientWorld;
    public DimensionType dimType;
    public ChunkGenerator chunkGen;
    public RegistryKey<World> regKey;

    public Cutscene(CompoundNBT nbt, DimensionType dimType, RegistryKey<World> regKey){
        this.dimType = dimType;
        this.regKey = regKey;
//        this.chunkGen = new CutsceneChunkGenerator(new SingleBiomeProvider(new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.FOREST)
//                .depth(0).scale(0).temperature(0).downfall(0)
//                .specialEffects(new BiomeAmbience.Builder().fogColor(0).waterColor(0)
//                        .waterFogColor(0).skyColor(0).foliageColorOverride(0)
//                        .grassColorOverride(0).grassColorModifier(BiomeAmbience.GrassColorModifier.NONE)
//                        .grassColorModifier(BiomeAmbience.GrassColorModifier.NONE)
//                        .build()).generationSettings(BiomeGenerationSettings.EMPTY)
//                .mobSpawnSettings(MobSpawnInfo.EMPTY).build()));
    }

    public void loadCutscene(ServerPlayerEntity player) throws CommandSyntaxException {
        Minecraft minecraft = Minecraft.getInstance();
        SaveFormat format = new SaveFormat(minecraft.gameDirectory.toPath().resolve("saves"), minecraft.gameDirectory.toPath().resolve("backups"), DataFixesManager.getDataFixer());

        this.chunkGen = new DebugChunkGenerator(DynamicRegistries.builtin().registryOrThrow(Registry.BIOME_REGISTRY));
        this.clientWorld = new ClientWorld(minecraft.getConnection(),minecraft.level.getLevelData(),null,this.dimType,0,()->minecraft.getProfiler(),minecraft.levelRenderer,false,0);

        ServerWorldInfo info = new ServerWorldInfo(CutsceneServerWorld.CUTSCENE_SETTINGS,new ServerPropertiesProvider(DynamicRegistries.builtin(), Paths.get("server.properties")).getProperties().worldGenSettings, Lifecycle.stable());
        try {
            this.world = new ServerWorld(player.getServer(), Util.backgroundExecutor(), format.createAccess(""),info,null, this.dimType,new TrackingChunkStatusListener(0), player.getServer().overworld().getChunkSource().getGenerator(), false,0,new ArrayList<>(),false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        Teleport.teleport(player,world,new BlockPos(0,0,0));
    }
}
