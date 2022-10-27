package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.SoundEvents;

public class WolfUnit extends Unit {
    public WolfUnit(){
        super(EntityType.WOLF);
        removeMoves();
        addMove(new BasicAttack("Bite"));
        setTame();
    }

    public void call(){
        Animation.sound(SoundEvents.WOLF_HOWL);
    }


    public void setTame(){
        if(!playerControl){
            return;
        }
        if(entity instanceof WolfEntity){
            ((WolfEntity) entity).setTame(true);
        }
    }
}
