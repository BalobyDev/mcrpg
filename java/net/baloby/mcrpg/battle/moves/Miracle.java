package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.util.text.StringTextComponent;

public class Miracle extends SpellMove{

    public Miracle() {
        super(new StringTextComponent("Miracle"), new StringTextComponent("Cures one ally of any physical ailment"));
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
