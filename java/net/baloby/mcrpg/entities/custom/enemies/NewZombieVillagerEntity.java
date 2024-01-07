package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.world.World;

public class NewZombieVillagerEntity extends ZombieVillagerEntity {

    public NewZombieVillagerEntity(World p_i50186_2_) {
        super(EntityType.ZOMBIE_VILLAGER, p_i50186_2_);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }
}
