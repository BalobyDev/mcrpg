package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.world.World;

public class NewStrayEntity extends StrayEntity {
    public NewStrayEntity(EntityType<? extends StrayEntity> p_i50191_1_, World p_i50191_2_) {
        super(p_i50191_1_, p_i50191_2_);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }
}
