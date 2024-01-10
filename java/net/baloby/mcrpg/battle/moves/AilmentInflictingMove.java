package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.PoisonAilment;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.util.text.ITextComponent;

import java.util.Random;

public class AilmentInflictingMove extends Move{

    private int chance;

    public AilmentInflictingMove(ITextComponent name, ITextComponent desc,int chance) {
        super(name, desc);
        this.chance = chance;
        this.type = Element.AILMENT;
    }
    protected Ailment createAilment(){return new PoisonAilment();}

    public void trySetAilment(Unit target, Ailment ailment){
        Random random = new Random();
        if(random.nextInt(100)<chance||target.getAilment()!=null){
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
