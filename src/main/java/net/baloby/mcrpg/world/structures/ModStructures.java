package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.baloby.mcrpg.setup.Registration;
import net.baloby.mcrpg.world.structures.CaveArena;
import net.baloby.mcrpg.world.structures.steve_house;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModStructures {

    public static IStructurePieceType CAP = CaveArenaPieces.Piece::new;

    public static final RegistryObject<Structure<NoFeatureConfig>> CAVE_ARENA = Registration.STRUCTURES.register("cave_arena", CaveArena::new);


    public static void setupStructures(){
        setupMapSpacingAndLand(CAVE_ARENA.get(),
                new StructureSeparationSettings(100,50,1234567890),
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

    }



    public static void register() {


    }
}
