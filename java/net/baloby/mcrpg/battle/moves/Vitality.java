package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.StatMod.Stat;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.util.text.StringTextComponent;

public class Vitality extends BuffMove{

    public Vitality() {
        super(new StringTextComponent("Vitality"), new StringTextComponent("Increases the attack of one ally."), Stat.ATK, 1);
    }

    @Override
    public void execute(Unit user, Unit target) {
        super.execute(user, target);
        AnimationSequence sequence = AnimationSequence.buffSequence(this,user,target);
        sequence.start();
    }
}
