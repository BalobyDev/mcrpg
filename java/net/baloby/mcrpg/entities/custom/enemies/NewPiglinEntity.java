package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.piglin.AbstractPiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.world.World;

public class NewPiglinEntity extends PiglinEntity {
    public NewPiglinEntity(EntityType<? extends AbstractPiglinEntity> p_i231570_1_, World p_i231570_2_) {
        super(EntityType.PIGLIN, p_i231570_2_);
    }
}
