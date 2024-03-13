package net.baloby.mcrpg.world;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.structures.ModStructureGeneration;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.ServerLifecycleEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = mcrpg.MODID)
public class ModWorldEvents {

    private static MinecraftServer server;


    @SubscribeEvent
    public static void biomeLoadEvent(final BiomeLoadingEvent event){
        ModStructureGeneration.generateStructures(event);
//       List<Supplier<StructureFeature<?, ?>>> toRemove = new ArrayList<>();
//        for(Supplier<StructureFeature<?, ?>> feature:event.getGeneration().getStructures()){
//            if(feature.get().feature.getRegistryName().equals(StructureFeatures.STRONGHOLD.feature.getRegistryName())) {
//                toRemove.add(feature);
//            }
//        }
//
//        event.getGeneration().getStructures().removeAll(toRemove);

    }

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event){
        if (event.getWorld() instanceof ServerWorld){
            ServerWorld world = (ServerWorld)event.getWorld();

            if(world.getChunkSource().generator instanceof StageChunkGenerator){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap =
                    new HashMap<>(world.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.STEVE_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.STEVE_HOUSE.get()));
            tempMap.putIfAbsent(ModStructures.MONASTERY.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.MONASTERY.get()));
            tempMap.putIfAbsent(ModStructures.SUNROOT.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.SUNROOT.get()));
            world.getChunkSource().generator.getSettings().structureConfig = tempMap;

        }
    }


    @SubscribeEvent
    public static void onServerLifecycle(ServerLifecycleEvent event) {
        if (event.getServer()!=null) {
            server = event.getServer();
        }
    }

    @SubscribeEvent
    public static void worldLoadEvent(WorldEvent.Load event){
        if(event.getWorld() instanceof ServerWorld) {
            server = ((ServerWorld) event.getWorld()).getServer();

        }
    }

    @SubscribeEvent
    public static void worldUnload(WorldEvent.Unload event){
        if(event.getWorld() instanceof ServerWorld) {
            server = ((ServerWorld) event.getWorld()).getServer();
        }
        Battle.isActive = false;
        Battle.instance = null;
    }

    @SubscribeEvent
    public static void despawnEvent(EntityLeaveWorldEvent event){
        if(event.getEntity() instanceof HumanoidEntity){
            Npc npc = ((HumanoidEntity) event.getEntity()).character;
            npc.isAddedToWorld = false;
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        if(event.player instanceof ServerPlayerEntity){
            for (Npc npc : Npc.allNpcs){
                if(npc.getHomePos()!=null && !npc.isAddedToWorld()){
                    if(Math.abs((int) event.player.position().x-npc.getHomePos().x())<32&&Math.abs((int) event.player.position().z-npc.getHomePos().z())<32) {
                        ((ServerPlayerEntity) event.player).getLevel().getServer().submitAsync(()->{npc.spawnAtHome(((ServerPlayerEntity) event.player).getLevel());});
                        }
                    }
                }
            }
        }
    public static MinecraftServer getServer(){return server;}
}
