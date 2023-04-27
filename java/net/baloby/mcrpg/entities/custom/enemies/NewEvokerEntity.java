package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.world.World;

public class NewEvokerEntity extends EvokerEntity {
    public NewEvokerEntity(EntityType<? extends EvokerEntity> type, World p_i50207_2_) {
        super(EntityType.EVOKER, p_i50207_2_);
    }
}
