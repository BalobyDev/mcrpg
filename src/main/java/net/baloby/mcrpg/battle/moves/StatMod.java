package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;

public class StatMod extends Move{

    public enum stat{ATK,DEF,SPD}
    public enum direction{UP,DOWN}

    public StatMod(String name, String desc) {
        super(name, desc);
        this.cost = 2;
    }

    public void execute(Unit user, Unit target){
        super.execute(user, target);
        
    }
}
