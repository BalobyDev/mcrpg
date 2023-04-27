package net.baloby.mcrpg.entities;

import com.google.common.collect.Maps;
import net.baloby.mcrpg.data.ModDataSerializers;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSounds;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.piglin.PiglinAction;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;

import javax.annotation.Nullable;
import java.util.Map;

public class HumanoidEntity extends MobEntity {
    private static final DataParameter<Npc> NPC_DATA = EntityDataManager.defineId(HumanoidEntity.class, ModDataSerializers.NPC_DATA);
    public int num;
    public Npc character;
    private ResourceLocation skin;


    public HumanoidEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world){
        this(ModEntities.HUMANOID.get(), world);
    }
    public HumanoidEntity(EntityType<? extends HumanoidEntity> entityEntityType, World world) {
        this(entityEntityType,world,new Npc());
    }

    public HumanoidEntity(EntityType<? extends HumanoidEntity> p_i48553_1_, World world, Npc character){
        super(p_i48553_1_, world);
        this.character = character;
        this.skin = character.getSkin();
    }

    public void aiStep() {
        this.updateSwingTime();
        super.aiStep();
    }

    public Npc getCharacter(){return entityData.get(NPC_DATA);}

    public void setCharacter(Npc character){
        this.entityData.set(NPC_DATA, character);
    }

    protected void defineSynchedData(){
        super.defineSynchedData();
        this.entityData.define(NPC_DATA, new Npc());
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

    @Override
    public SoundEvent getDeathSound(){
        if(character!=null)return character.getHurtSound();
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source){
        return getDeathSound();
    }

    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_){
        return super.finalizeSpawn(world,p_213386_2_,p_213386_3_,p_213386_4_,p_213386_5_);
    }

    public float getSize(){



        if(character != null){
            return getCharacter().getSize();
        }
        else {
            return 0.9375F;
        }
    }

    public ResourceLocation getResourceLocation() {
        if(character != null){
            return getCharacter().getSkin();
        }
        else {
            return new ResourceLocation("textures/entity/alex.png");
        }
    }

    @Override
    public void tick(){
        super.tick();

//        if(!Npc.allNpcs.contains(this)) {
//            this.remove();
//        }

        if(character!=null)character.tick();

    }
}
