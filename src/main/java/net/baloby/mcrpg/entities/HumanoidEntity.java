package net.baloby.mcrpg.entities;

import com.google.common.collect.Maps;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;
import java.util.Map;

public class HumanoidEntity extends MonsterEntity implements IEntityAdditionalSpawnData {
    public int num;
    public Npc character;
    private ResourceLocation skin;


    public HumanoidEntity(World world){
        super(ModEntities.HUMANOID.get(), world);
    }
    public HumanoidEntity(EntityType<? extends HumanoidEntity> entityEntityType, World world) {
        this(entityEntityType,world,Npcs.ALEX);
    }

    public HumanoidEntity(EntityType<? extends HumanoidEntity> p_i48553_1_, World world, Npc character){
        super(p_i48553_1_, world);
        this.character = character;
        this.skin = character.getSkin();
    }


    public static final Map<Integer, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (skin) -> {
        skin.put(0,new ResourceLocation("textures/entity/alex.png"));
        skin.put(1,new ResourceLocation(mcrpg.MODID,"textures/entity/rana.png"));
        skin.put(2,new ResourceLocation(mcrpg.MODID,"textures/entity/cam.png"));
    });

    public void setCharacter(Npc character){
        this.character = character;
        this.skin = character.getSkin();
    }

    public ResourceLocation getResourceLocation(){
        return character != null ? character.getSkin() : TEXTURE_BY_TYPE.get(num);
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

    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_){
        return super.finalizeSpawn(world,p_213386_2_,p_213386_3_,p_213386_4_,p_213386_5_);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeByte(Npcs.npcs.indexOf(character));

    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        this.character = Npcs.npcs.get(additionalData.readByte());
    }
}
