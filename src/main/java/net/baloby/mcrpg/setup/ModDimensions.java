package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.mcrpg;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ModDimensions {

    public static final RegistryKey<DimensionType> ARENA_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(mcrpg.MODID,"arena"));
    public static final RegistryKey<World> ARENA = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID,"arena"));
    public static final RegistryKey<DimensionType> STAGE_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(mcrpg.MODID,"stage"));
    public static final RegistryKey<World> STAGE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID,"stage"));



}