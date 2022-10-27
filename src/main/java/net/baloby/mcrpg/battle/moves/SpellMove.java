package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvents;

public abstract class SpellMove extends Move{



    public SpellMove(String name, String desc) {
        super(name,desc);
    }

    public void execute(Unit user, Unit target) {
        this.deduct(user);
    }
}
