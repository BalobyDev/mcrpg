package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

public abstract class SpellMove extends Move{



    public SpellMove(ITextComponent name, ITextComponent desc) {
        super(name,desc);
    }

    public void execute(Unit user, Unit target) {
        this.deduct(user);
    }
}
