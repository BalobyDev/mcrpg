package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.setup.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class IceologerEntity extends AbstractIllagerEntity {
    public IceologerEntity(EntityType<? extends IceologerEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source){return SoundEvents.PILLAGER_HURT;}

    @Override
    protected SoundEvent getDeathSound(){return SoundEvents.PILLAGER_DEATH;}

    @Override
    protected void registerGoals(){
        super.registerGoals();
        GoalSelector gs = this.goalSelector;
        gs.addGoal(1, new SwimGoal(this));
        gs.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        gs.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE,3.0)
                .add(Attributes.MAX_HEALTH,20.0);
    }
}
