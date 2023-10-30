package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;

public class PigmanUnit extends Unit {

    public PigmanUnit(){
        super(EntityType.PIGLIN);
    }


    @Override
    public MobEntity spawn(EntityType type){
        PiglinEntity e = (PiglinEntity) super.spawn(type);
        if(e.isBaby()){
            return spawn(type);
        }
        return e;
    }
}
