package net.baloby.mcrpg.world.structures;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event){

        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

        if(types.contains(BiomeDictionary.Type.FOREST)) {
            structures.add(() -> ModStructures.STEVE_HOUSE.get().configured(IFeatureConfig.NONE));
        }

        if(types.contains(BiomeDictionary.Type.PLAINS)){
            structures.add(() -> ModStructures.SUNROOT.get().configured(IFeatureConfig.NONE));
        }

        structures.add(() -> ModStructures.MONASTERY.get().configured(IFeatureConfig.NONE));
    }
}
