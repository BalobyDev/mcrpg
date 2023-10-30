package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.Bosses.BossUnit;
import net.baloby.mcrpg.battle.Unit.Enemies.Bosses.InfernoUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.setup.ModDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class InfernoEntity extends MonsterEntity implements ICustomBossEntity{
    public InfernoEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.BLAZE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    public Unit unit() {
        return new InfernoUnit();
    }

    @Override
    public ServerWorld getArena() {
        return getServer().getLevel(ModDimensions.NETHER_ARENA);
    }

    @Override
    public BossUnit boss() {
        return new InfernoUnit();
    }
}
