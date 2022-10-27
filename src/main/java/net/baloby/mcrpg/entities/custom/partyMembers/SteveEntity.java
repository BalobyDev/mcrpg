package net.baloby.mcrpg.entities.custom.partyMembers;

import net.baloby.mcrpg.setup.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SteveEntity extends MonsterEntity {

    public SteveEntity(EntityType<? extends MonsterEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource source){return ModSounds.OOH.get();}

    @Override
    protected SoundEvent getDeathSound(){return ModSounds.OOH.get();}

    @Override
    protected void registerGoals(){
        super.registerGoals();
        GoalSelector gs = this.goalSelector;
        gs.addGoal(1, new SwimGoal(this));
        gs.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        gs.addGoal(3, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE,3.0)
                .add(Attributes.MAX_HEALTH,20.0);
    }
}

