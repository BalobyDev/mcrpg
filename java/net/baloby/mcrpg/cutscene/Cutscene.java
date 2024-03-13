package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.tools.Teleport;
import net.baloby.mcrpg.world.StructureGen;
import net.baloby.mcrpg.world.structures.EmptyChunkGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.ServerPropertiesProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.listener.TrackingChunkStatusListener;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.ServerWorldInfo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Cutscene {

    public ServerPlayerEntity player;
    public ServerWorld world;
    public ClientWorld clientWorld;
    public DimensionType dimType;
    public ChunkGenerator chunkGen;
    public RegistryKey<World> regKey;
    public SaveFormat.LevelSave save;
    public List<Actor> npcs;
    public List<CutsceneInstance> list;
    public static HashMap<PlayerEntity, Cutscene> cutsceneMap = new HashMap<>();
    private final ResourceLocation stage;

    public static Codec<Cutscene> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("stage").forGetter(Cutscene::getStage),
            Actor.CODEC.listOf().fieldOf("npcs").forGetter(Cutscene::getNpcs),
            CutsceneInstance.CODEC.listOf().fieldOf("instances").forGetter(Cutscene::getList)
    ).apply(instance,Cutscene::new));


    public Cutscene(ResourceLocation stage,  List<Actor> npcs, List<CutsceneInstance> list){
        this.stage = stage;
        this.npcs = npcs;
        this.list = list;
        RegistryKey<World> key  = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("cutscene"));
        this.dimType = new CutsceneDimensionType(OptionalLong.empty(), true, false, false, true, 1.0D,
                false, false, true, false, true, 256, ColumnFuzzedBiomeMagnifier.INSTANCE,
                BlockTags.INFINIBURN_OVERWORLD.getName(), DimensionType.OVERWORLD_EFFECTS, 0.0F);


//        this.chunkGen = new CutsceneChunkGenerator(new SingleBiomeProvider(new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.FOREST)
//                .depth(0).scale(0).temperature(0).downfall(0)
//                .specialEffects(new BiomeAmbience.Builder().fogColor(0).waterColor(0)
//                        .waterFogColor(0).skyColor(0).foliageColorOverride(0)
//                        .grassColorOverride(0).grassColorModifier(BiomeAmbience.GrassColorModifier.NONE)
//                        .grassColorModifier(BiomeAmbience.GrassColorModifier.NONE)
//                        .build()).generationSettings(BiomeGenerationSettings.EMPTY)
//                .mobSpawnSettings(MobSpawnInfo.EMPTY).build()));
    }

    public List<Actor> getNpcs() {
        return npcs;
    }

    public List<CutsceneInstance> getList() {
       return list;
    }

    public void loadCutscene(ServerPlayerEntity player)  {
        Minecraft minecraft = Minecraft.getInstance();
        this.player = player;
//        cutsceneMap.put(player,this);
//        this.save = new SaveFormat(minecraft.gameDirectory.toPath().resolve("saves"), minecraft.gameDirectory.toPath().resolve("backups"), DataFixesManager.getDataFixer()).createAccess("cutscene_world");
//        this.chunkGen = new EmptyChunkGenerator(DynamicRegistries.builtin().registryOrThrow(Registry.BIOME_REGISTRY));
//        this.clientWorld = new ClientWorld(minecraft.getConnection(),minecraft.level.getLevelData(),null,this.dimType,16,()->minecraft.getProfiler(),minecraft.levelRenderer,false,0);
//
//        ServerWorldInfo info = new ServerWorldInfo(CutsceneServerWorld.CUTSCENE_SETTINGS,new ServerPropertiesProvider(DynamicRegistries.builtin(), Paths.get("server.properties")).getProperties().worldGenSettings, Lifecycle.stable());
//        this.world = new ServerWorld(player.getServer(), Util.backgroundExecutor(), this.save,info,RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID,"cutscene")), this.dimType,new TrackingChunkStatusListener(0), player.getServer().overworld().getChunkSource().getGenerator(), false,0,new ArrayList<>(),true);
//        StructureGen.placeManually(new BlockPos(-8,100,-8), this.world,this.stage, Rotation.NONE);
//        Teleport.teleport(player,world,new BlockPos(0,102,0));
    }

    public void endCutscene() throws IOException {
        BlockPos pos = player.getRespawnPosition() != null ? player.getRespawnPosition() : player.getLevel().getSharedSpawnPos();
        Teleport.teleport(player,player.getLevel().getServer().getLevel(player.getRespawnDimension()),pos);
        this.save.deleteLevel();
        this.save.close();
    }

    public ResourceLocation getStage() {
        return stage;
    }

}
