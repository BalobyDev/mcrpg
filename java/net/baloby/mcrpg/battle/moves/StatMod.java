package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.util.text.ITextComponent;

public class StatMod extends Move{

    public enum stat{ATK,DEF,SPD}
    public enum direction{UP,DOWN}

    public StatMod(ITextComponent name, ITextComponent desc) {
        super(name, desc);
        this.cost = 2;
    }

    public void execute(Unit user, Unit target){
        super.execute(user, target);
        
    }
}
