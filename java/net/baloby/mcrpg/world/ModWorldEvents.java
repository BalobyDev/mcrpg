package net.baloby.mcrpg.world;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.baloby.mcrpg.world.structures.ModStructureGeneration;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = mcrpg.MODID)
public class ModWorldEvents {


    @SubscribeEvent
    public static void biomeLoadEvent(final BiomeLoadingEvent event){
        ModStructureGeneration.generateStructures(event);

    }

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event){
        if (event.getWorld() instanceof ServerWorld){
            ServerWorld world = (ServerWorld)event.getWorld();

            if(world.getChunkSource().generator instanceof ArenaChunkGenerator){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap =
                    new HashMap<>(world.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.STEVE_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.STEVE_HOUSE.get()));
//            tempMap.putIfAbsent(ModStructures.FELINA_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.FELINA_HOUSE.get()));
//            tempMap.putIfAbsent(ModStructures.SMITH.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.SMITH.get()));
            tempMap.putIfAbsent(ModStructures.SUNROOT.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.SUNROOT.get()));
            world.getChunkSource().generator.getSettings().structureConfig = tempMap;

        }
    }


    @SubscribeEvent
    public static void worldLoadEvent(WorldEvent.Load event){
        if(event.getWorld() instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) event.getWorld();
            for(RegistryObject<NpcType<?>> type : Registration.NPC_TYPES.getEntries()){
//                type.get().listCreate();
            }
        }
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
                if(npc.getHomePos()!=null && !npc.isAddedToWorld){
                    if(Math.abs((int) event.player.position().x-npc.getHomePos().getX())<32&&Math.abs((int) event.player.position().z-npc.getHomePos().getZ())<32) {
                        ((ServerPlayerEntity) event.player).getLevel().getServer().submitAsync(()->{npc.spawnAtHome(((ServerPlayerEntity) event.player).getLevel());});


                        }
                    }
                }
            }
        }
}
