package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Melody extends SpellMove{

    public Melody() {
        super(new StringTextComponent("Mind Melody"), new StringTextComponent("Cures an ally of any mental status ailment"));
        this.cost = 2;
        this.type = Element.SUPPORT;
    }

    public void execute(Unit user, Unit target){
        super.execute(user, target);
        this.target.cureAilment();
        AnimationSequence sequence = AnimationSequence.basicAllySequence(this,user,target,0);
        sequence.start();
    }
}
