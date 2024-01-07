package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.entities.CorpseEntity;
import net.baloby.mcrpg.entities.HumanoidPiglinEntity;
import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.entities.custom.enemies.*;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntities {

    public static final RegistryObject<EntityType<CorpseEntity>> CORPSE = Registration.ENTITY_TYPES.register("corpse",() -> EntityType.Builder.<CorpseEntity>of
                    (CorpseEntity::new, EntityClassification.AMBIENT)
            .sized(0.6f,1.8f)
            .build("corpse"));

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


    public static final RegistryObject<EntityType<CultistEntity>> CULTIST = Registration.ENTITY_TYPES.register("cultist",() -> EntityType.Builder.of
                    (CultistEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("cultist"));


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

    public static final RegistryObject<EntityType<WoodlandPixieEntity>> WOODLAND_FAIRY = Registration.ENTITY_TYPES.register("woodland_fairy", ()-> EntityType.Builder.of
            (WoodlandPixieEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("woodland_fairy"));

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return setCustomAttributes(3,20);

    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(double atk, double health){
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE,atk)
                .add(Attributes.MAX_HEALTH,health);
    }


    static void register(){}

}

