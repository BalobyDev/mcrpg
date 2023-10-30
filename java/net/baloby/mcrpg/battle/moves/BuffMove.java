package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.StatMod.Stat;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.util.text.ITextComponent;

public class BuffMove extends StatModMove{
    public BuffMove(ITextComponent name, ITextComponent desc, Stat stat, int direction) {
        super(name, desc, stat, direction);
        this.friendly = true;
    }

    @Override
    public void execute(Unit user, Unit target) {
        super.execute(user, target);
        AnimationSequence sequence = AnimationSequence.buffSequence(this,user,target);
        sequence.start();
    }
}
