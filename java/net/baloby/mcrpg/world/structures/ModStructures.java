package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModStructures {


    public static IStructurePieceType CAP = CaveArenaPieces.Piece::new;
    public static IStructurePieceType STEVE_HOUSE_PIECE = SteveHousePiece.Piece::new;
//    public static IStructurePieceType FELINA_HOUSE_PIECE = FelinaHousePiece.Piece::new;
    public static IStructurePieceType SUNROOT_PIECE = SunRootPieces.Piece::new;

    public static final RegistryObject<Structure<NoFeatureConfig>> STEVE_HOUSE = Registration.STRUCTURES.register("steve_house", SteveHouse::new);
//    public static final RegistryObject<Structure<NoFeatureConfig>> FELINA_HOUSE = Registration.STRUCTURES.register("felina_house", FelinaHouse::new);
//    public static final RegistryObject<Structure<NoFeatureConfig>> SMITH = Registration.STRUCTURES.register("smith",SmithShop::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> SUNROOT = Registration.STRUCTURES.register("sunroot",SunRootStructure::new);


    public static void setupStructures(){

        setupMapSpacingAndLand(STEVE_HOUSE.get(),
                new StructureSeparationSettings(8,4,1234567890),
                true);
//        setupMapSpacingAndLand(FELINA_HOUSE.get(),
//                new StructureSeparationSettings(8,4,1234567890),
//                true);
//        setupMapSpacingAndLand(SMITH.get(),
//                new StructureSeparationSettings(10,4,1234567890),
//                true);

        setupMapSpacingAndLand(SUNROOT.get(),
                new StructureSeparationSettings(10,5,1234567890),
                true);


        registerAllPieces();
    }


    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure, StructureSeparationSettings structureSeparationSettings,
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


    public static void registerAllPieces(){
        registerPiece(STEVE_HOUSE_PIECE,new ResourceLocation(mcrpg.MODID, "steve_house_piece"));
//        registerPiece(FELINA_HOUSE_PIECE,new ResourceLocation(mcrpg.MODID, "felina_house_piece"));
        registerPiece(SUNROOT_PIECE,new ResourceLocation(mcrpg.MODID, "sunroot_piece"));
    }

    public static void registerPiece(IStructurePieceType structurePiece, ResourceLocation rl){
        Registry.register(Registry.STRUCTURE_PIECE, rl, structurePiece);
    }



    public static void register() {


    }
}
