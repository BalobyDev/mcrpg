package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.VilerWitchUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class VilerWitchEntity extends WitchEntity implements ICustomBattleEntity {
    public VilerWitchEntity(EntityType<? extends WitchEntity> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }


    @Override
    public Unit unit() {
        return new VilerWitchUnit();
    }
}
