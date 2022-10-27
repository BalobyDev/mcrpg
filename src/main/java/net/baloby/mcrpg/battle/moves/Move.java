package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvents;

public abstract class Move {
    public String name;
    public String desc;
    public Element type;
    public Unit user;
    public Unit target;
    public int spd;
    public float cost = 0;
    public enum costType{HP, MP}

    public Move(String name){
        this.name = name;
    }

    public Move(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    public void register(){Moves.moveList.add(this);}



    public void deduct(Unit user){
        if(cost>user.MP){
            Minecraft.getInstance().player.playSound(SoundEvents.CAT_DEATH,1,1);
            return;
        }
        user.MP -= cost;
    }
    public void execute(Unit user, Unit target){
        this.deduct(user);
    }
}
