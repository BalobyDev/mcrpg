package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;

public class Gaurd extends Move{

    public Gaurd() {
        super("Gaurd");
    }

    public void execute(Unit user, Unit target){
        target.gaurding = true;
        target.battle.camera.setFacing(target);
    }
}
