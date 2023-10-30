package net.baloby.mcrpg.battle.ailments;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;

public class DamageAilment extends Ailment{
    public float dmg;

    public DamageAilment(String name, float dmg) {
        super(name);
        this.dmg = dmg;
    }

    @Override
    public void execute(Unit unit){
        super.execute(unit);
        MoveTextGui.open(this);
        unit.takeDamage(dmg,null,false);
    }
}
