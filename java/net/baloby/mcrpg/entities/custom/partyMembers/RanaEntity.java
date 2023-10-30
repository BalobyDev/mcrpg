package net.baloby.mcrpg.entities.custom.partyMembers;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class RanaEntity extends MonsterEntity {


    public RanaEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        GoalSelector gs = this.goalSelector;
        gs.addGoal(1, new SwimGoal(this));
        gs.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        gs.addGoal(3, new LookRandomlyGoal(this));
    }


}

