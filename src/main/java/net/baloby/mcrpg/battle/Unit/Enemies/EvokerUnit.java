package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.misc.IdleGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;

public class EvokerUnit extends Unit {

    public EvokerUnit(){
        super(EntityType.EVOKER);
    }

    @Override
    public void tick() {
        super.tick();
        if(entity instanceof MobEntity){
            ((MobEntity) entity).goalSelector.addGoal(0,new IdleGoal());
        }
    }
}
