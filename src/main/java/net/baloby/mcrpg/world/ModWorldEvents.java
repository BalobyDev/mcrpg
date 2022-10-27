package net.baloby.mcrpg.world;

import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.structures.ModStructureGeneration;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

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

            if(!(world.getChunkSource().generator instanceof ArenaChunkGenerator)){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap =
                    new HashMap<>(world.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.CAVE_ARENA.get(),
                    DimensionStructuresSettings.DEFAULTS.get(ModStructures.CAVE_ARENA.get()));
            world.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
