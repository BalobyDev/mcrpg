package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.world.World;

public class NewSkeletonEntity extends SkeletonEntity {
    public NewSkeletonEntity(EntityType<? extends SkeletonEntity> p_i50194_1_, World p_i50194_2_) {
        super(p_i50194_1_, p_i50194_2_);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }
}
