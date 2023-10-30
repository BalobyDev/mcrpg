package net.baloby.mcrpg.battle.ailments;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.baloby.mcrpg.tools.Dice;

import java.util.Random;

public class ActionPreventAilment extends Ailment{

    public int chance;

    public ActionPreventAilment(String name, int chance) {
        super(name);
        this.chance = chance;
    }

    @Override
    public void execute(Unit unit){
        Random random = new Random();
        if(random.nextInt()+1<chance){
            MoveTextGui.open(this);
        }
        else{
            unit.takeTurn();
        }
    }
}
