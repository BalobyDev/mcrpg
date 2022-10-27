package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.misc.IdleGoal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class Npc {
    public String name;
    public Entity entity;
    public boolean slim = false;
    private ResourceLocation skin;

    public Npc(){
        this.name = "Alex";
        this.skin = new ResourceLocation(mcrpg.MODID, "textures/entity/alex.png");
    }

    public Npc(String name, ResourceLocation skin, boolean slim){
        this.name = name;
        this.skin = skin;
        this.slim = slim;
    }




    public Entity spawn(ServerWorld world, Vector3d pos){
        HumanoidEntity entity = new HumanoidEntity(ModEntities.HUMANOID.get(),world,this);
        entity.moveTo(pos.x,pos.y,pos.z);
        entity.finalizeSpawn(world, world.getCurrentDifficultyAt(new BlockPos(pos)),SpawnReason.SPAWN_EGG, (ILivingEntityData)null, (CompoundNBT)null);
        world.addFreshEntity(entity);
        return entity;
    }

    public void write(CompoundNBT nbt){

    }

    public CompoundNBT read(){
        return new CompoundNBT();
    }


    public ResourceLocation getSkin(){return skin;}

    public void setSkin(ResourceLocation skin){this.skin = skin;}
}
