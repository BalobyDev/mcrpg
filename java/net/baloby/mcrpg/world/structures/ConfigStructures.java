package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class ConfigStructures {
    public static StructureFeature<?,?> CONFIGURED_MONASTERY = ModStructures.MONASTERY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?,?> CONFIGURED_STEVE_HOUSE = ModStructures.STEVE_HOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?,?> CONFIGURED_SUNROOT = ModStructures.SUNROOT.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures(){
        Registry<StructureFeature<?,?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(mcrpg.MODID, "configured_monastery"), CONFIGURED_MONASTERY);
        Registry.register(registry, new ResourceLocation(mcrpg.MODID, "configured_steve_house"), CONFIGURED_STEVE_HOUSE);
        Registry.register(registry, new ResourceLocation(mcrpg.MODID, "configured_sunroot"), CONFIGURED_SUNROOT);

    }

}
