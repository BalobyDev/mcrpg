package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.mcrpg;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ModDimensions {

    public static final RegistryKey<DimensionType> STAGE_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(mcrpg.MODID,"stage"));
    public static final RegistryKey<DimensionType> ARENA_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(mcrpg.MODID,"arena"));
    public static final RegistryKey<DimensionType> OVERWORLD_ARENA_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(mcrpg.MODID,"overworld_arena"));

    public static final RegistryKey<World> OVERWORLD_STAGE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID,"overworld_stage"));
    public static final RegistryKey<World> DESERT_STAGE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID, "desert_stage"));
    public static final RegistryKey<World> END_STAGE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID, "end_stage"));
    public static final RegistryKey<World> FORREST_STAGE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID, "forrest_stage"));
    public static final RegistryKey<World> NETHER_STAGE = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID, "nether_stage"));



}