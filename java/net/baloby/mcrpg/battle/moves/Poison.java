package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.PoisonAilment;
import net.baloby.mcrpg.tools.Animation;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class Poison extends AilmentInflictingMove{

    public Poison() {
        super(new StringTextComponent("Poison"), new StringTextComponent("Poisons one target."),50);
        this.cost = 2;
    }



    @Override
    public void execute(Unit user, Unit target){
        super.execute(user, target);
    }

    @Override
    public SoundEvent getSound(){
        return SoundEvents.GLASS_BREAK;
    }
}
