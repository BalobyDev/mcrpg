package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModStructures {

    public static final RegistryObject<Structure<NoFeatureConfig>> STEVE_HOUSE = Registration.STRUCTURES.register("steve_house", SteveHouse::new);

    public static final RegistryObject<Structure<NoFeatureConfig>> SUNROOT = Registration.STRUCTURES.register("sunroot",SunRootStructure::new);

    public static final RegistryObject<Structure<NoFeatureConfig>> MONASTERY = Registration.STRUCTURES.register("dragon_monastery", DragonMonasteryStructure::new);


    public static void setupStructures(){

        setupMapSpacingAndLand(STEVE_HOUSE.get(),
                new StructureSeparationSettings(4,2,1234567890),
                true);

        setupMapSpacingAndLand(SUNROOT.get(),
                new StructureSeparationSettings(6,3,1234567890),
                true);

        setupMapSpacingAndLand(MONASTERY.get(),
                new StructureSeparationSettings(1, 0, 0),
                true);
    }


    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand) {

        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            Structure.NOISE_AFFECTING_FEATURES =
            ImmutableList.<Structure<?>>builder()
                    .addAll(Structure.NOISE_AFFECTING_FEATURES)
                    .add(structure)
                    .build();
        }

        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                .putAll(DimensionStructuresSettings.DEFAULTS)
                .put(structure, structureSeparationSettings)
                .build();

        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap =
                    settings.getValue().structureSettings().structureConfig;

            if(structureMap instanceof ImmutableMap){
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().getConfig(structure);
            }
            else {
                structureMap.put(structure,structureSeparationSettings);
            }
        });
    }



    public static void register() {


    }
}
