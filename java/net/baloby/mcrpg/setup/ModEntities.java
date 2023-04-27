package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.entities.HumanoidPiglinEntity;
import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.entities.custom.enemies.*;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.SteveEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntities {

    public static final RegistryObject<EntityType<HumanoidEntity>> HUMANOID = Registration.ENTITY_TYPES.register("humanoid",() -> EntityType.Builder.<HumanoidEntity>of
                    (HumanoidEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .noSave()
            .setCustomClientFactory(HumanoidEntity::new)
            .build("humanoid"));

    public static final RegistryObject<EntityType<HumanoidPiglinEntity>> HUMANOID_PIGLIN = Registration.ENTITY_TYPES.register("humanoid_piglin",() -> EntityType.Builder.<HumanoidPiglinEntity>of
                    (HumanoidPiglinEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .noSave()
            .setCustomClientFactory(HumanoidPiglinEntity::new)
            .build("humanoid_piglin"));


    public static final RegistryObject<EntityType<HumanoidSlimEntity>> HUMANOID_SLIM = Registration.ENTITY_TYPES.register("humanoid_slim",() -> EntityType.Builder.<HumanoidSlimEntity>of
                    (HumanoidSlimEntity::new, EntityClassification.MISC)
            .sized(0.6f,1.8f)
            .noSave()
            .setCustomClientFactory(HumanoidSlimEntity::new)
            .build("humanoid_slim"));



    public static final RegistryObject<EntityType<IceologerEntity>> ICEOLOGER = Registration.ENTITY_TYPES.register("iceologer", () -> EntityType.Builder.of
                    (IceologerEntity::new, EntityClassification.MISC)
            .sized(0.6f,1.8f)
            .build("iceologer"));

    public static final RegistryObject<EntityType<InfernoEntity>> INFERNO = Registration.ENTITY_TYPES.register("inferno",()-> EntityType.Builder.of
                    (InfernoEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("inferno"));

    public static final RegistryObject<EntityType<NewEndermanEntity>> NEW_ENDERMAN = Registration.ENTITY_TYPES.register("new_enderman", () -> EntityType.Builder.of
                    (NewEndermanEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("new_enderman"));

    public static final RegistryObject<EntityType<VilerWitchEntity>> VILER_WITCH = Registration.ENTITY_TYPES.register("viler_witch", ()-> EntityType.Builder.of
            (VilerWitchEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("viler_witch"));

    public static final RegistryObject<EntityType<WoodlandPixieEntity>> WOODLAND_PIXIE = Registration.ENTITY_TYPES.register("woodland_pixie", ()-> EntityType.Builder.of
            (WoodlandPixieEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("woodland_pixie"));




    static void register(){}

}

