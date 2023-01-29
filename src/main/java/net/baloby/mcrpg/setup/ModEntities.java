package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.entities.custom.enemies.NewEndermanEntity;
import net.baloby.mcrpg.entities.custom.enemies.IceologerEntity;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.custom.enemies.VilerWitchEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.SteveEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntities {

    public static final RegistryObject<EntityType<RanaEntity>> RANA = Registration.ENTITY_TYPES.register("rana",()-> EntityType.Builder.of
                    (RanaEntity::new, EntityClassification.MONSTER)
    .sized(0.6f,1.8f)
    .build(new ResourceLocation(mcrpg.MODID, "rana").toString()));

    public static final RegistryObject<EntityType<SteveEntity>> STEVE = Registration.ENTITY_TYPES.register("steve",()-> EntityType.Builder.of
                    (SteveEntity::new, EntityClassification.MONSTER)
    .sized(0.6f,1.8f)
     .build("steve"));

    public static final RegistryObject<EntityType<HumanoidEntity>> HUMANOID = Registration.ENTITY_TYPES.register("humanoid",() -> EntityType.Builder.<HumanoidEntity>of
                    (HumanoidEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .noSave()
            .setCustomClientFactory(HumanoidEntity::new)
            .build("humanoid"));

    public static final RegistryObject<EntityType<HumanoidSlimEntity>> HUMANOID_SLIM = Registration.ENTITY_TYPES.register("humanoid_slim",() -> EntityType.Builder.<HumanoidSlimEntity>of
                    (HumanoidSlimEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .noSave()
            .setCustomClientFactory(HumanoidSlimEntity::new)
            .build("humanoid_slim"));

    public static final RegistryObject<EntityType<IceologerEntity>> ICEOLOGER = Registration.ENTITY_TYPES.register("iceologer", () -> EntityType.Builder.of
                    (IceologerEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("iceologer"));

    public static final RegistryObject<EntityType<NewEndermanEntity>> NEW_ENDERMAN = Registration.ENTITY_TYPES.register("new_enderman", () -> EntityType.Builder.of
                    (NewEndermanEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("new_enderman"));

    public static final RegistryObject<EntityType<VilerWitchEntity>> VILER_WITCH = Registration.ENTITY_TYPES.register("viler_witch", ()-> EntityType.Builder.of
            (VilerWitchEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("viler_witch"));


    static void register(){}

}

