package net.baloby.mcrpg.battle.ailments;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.baloby.mcrpg.tools.Dice;

public class ActionPreventAilment extends Ailment{

    public int chance;

    public ActionPreventAilment(String name, int chance) {
        super(name);
        this.chance = chance;
    }

    @Override
    public void execute(Unit unit){
        if(Dice.roll()<chance){
            MoveTextGui.open(this);
        }
        else{
            unit.takeTurn();
        }
    }
}
