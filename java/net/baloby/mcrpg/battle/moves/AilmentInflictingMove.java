package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.PoisonAilment;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.util.text.ITextComponent;

public class AilmentInflictingMove extends Move{

    public AilmentInflictingMove(ITextComponent name, ITextComponent desc) {
        super(name, desc);
    }



    protected Ailment createAilment(){return new PoisonAilment();}

    public void trySetAilment(Unit target, Ailment ailment){
        if(target.getAilment()!=null){
            target.miss();
            return;
        }
        target.setAilment(ailment);
    }
    @Override
    public void execute(Unit user, Unit target){
        AnimationSequence sequence = AnimationSequence.ailmentSequence(this,user,target,createAilment());
        sequence.start();
    }
}
