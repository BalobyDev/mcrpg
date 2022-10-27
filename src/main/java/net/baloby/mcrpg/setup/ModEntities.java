package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.entities.custom.enemies.IceologerEntity;
import net.baloby.mcrpg.entities.HumanoidEntity;
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
            .setCustomClientFactory((spawnEntity, world) -> new HumanoidEntity(world))
            .build("humanoid"));

    public static final RegistryObject<EntityType<IceologerEntity>> ICEOLOGER = Registration.ENTITY_TYPES.register("iceologer", () -> EntityType.Builder.of
                    (IceologerEntity::new, EntityClassification.MONSTER)
            .sized(0.6f,1.8f)
            .build("iceologer"));



    static void register(){}

}

