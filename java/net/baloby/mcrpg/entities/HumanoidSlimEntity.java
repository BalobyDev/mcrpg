package net.baloby.mcrpg.entities;

import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class HumanoidSlimEntity extends HumanoidEntity{
    public HumanoidSlimEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(spawnEntity, world);
    }

    public HumanoidSlimEntity(EntityType<? extends HumanoidSlimEntity> entityEntityType, World world) {
        super(ModEntities.HUMANOID_SLIM.get(), world);
    }

    public HumanoidSlimEntity(EntityType<? extends HumanoidEntity> p_i48553_1_, World world, Npc character) {
        super(p_i48553_1_, world, character);
    }

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
