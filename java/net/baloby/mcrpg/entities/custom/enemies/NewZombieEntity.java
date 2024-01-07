package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.World;

public class NewZombieEntity extends ZombieEntity {

    public NewZombieEntity(World p_i1745_1_) {
        super(p_i1745_1_);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }
}
