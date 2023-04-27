package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ConfigStructures {

    public static StructureFeature<?,?> CONFIGURED_CAVE_ARENA = ModStructures.CAVE_ARENA.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures(){
        Registry<StructureFeature<?,?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(mcrpg.MODID, "configured_cave_arena"), CONFIGURED_CAVE_ARENA);

        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.CAVE_ARENA.get(),CONFIGURED_CAVE_ARENA);
    }

}