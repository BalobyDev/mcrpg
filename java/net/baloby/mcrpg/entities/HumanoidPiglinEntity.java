package net.baloby.mcrpg.entities;

import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class HumanoidPiglinEntity extends HumanoidEntity{


    public HumanoidPiglinEntity(EntityType<? extends HumanoidPiglinEntity> entityEntityType, World world) {
        this(entityEntityType,world,new Npc());
    }

    public HumanoidPiglinEntity(EntityType<? extends HumanoidEntity> p_i48553_1_, World world, Npc character){
        super(p_i48553_1_, world,character);

    }

    public HumanoidPiglinEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(spawnEntity, world);
    }

    @Override
    public SoundEvent getDeathSound(){
        return SoundEvents.PIGLIN_HURT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source){
        return SoundEvents.PIGLIN_DEATH;
    }

}
