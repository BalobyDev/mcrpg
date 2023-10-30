package net.baloby.mcrpg.battle.StatMod;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;

import java.util.HashMap;

public class StatMod {

    public Unit unit;
    private Stat stat;
    private int turnsLeft;
    private int direction;

    public StatMod(Stat stat, int direction,Unit unit){
        this.turnsLeft = 3;
        this.stat = stat;
        this.direction = direction;
        this.unit = unit;
    }

    public int getDirection(){
        return direction;
    }

    public void takeEffect(){
        this.stat.takeEffect(unit,direction);
    }

    public void revert(){
        unit.statMods.put(stat,new StatMod(stat,0,unit));
    }

    public void takeTurn(){
        turnsLeft-=1;
        if(turnsLeft == 0){
            revert();
            AnimationSequence.revert(unit,stat);

        }
    }
}
