package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.apache.logging.log4j.Level;

public abstract class AttackMove extends Move {

    public float dmg;
    public float spd = 100;
    public boolean ranged;

    public AttackMove(){
        super(new StringTextComponent("AttackMove"));
        this.dmg = 5;
        this.type = Element.PHYSICAL;
    }

    public AttackMove(float dmg, Element type, ITextComponent name, ITextComponent desc){
        super(name,desc);
        this.dmg = dmg;
        this.type = type;
        this.name = name;
    }

    public void tryAttack(Unit user, Unit target,float dmg){
        if(Dice.roll(100)<10) {
            target.miss();
            return;
        }
        target.takeDamage(dmg,this.type,false);
    }

    public float calculateDmg(Unit user, Unit target){
        float d = dmg;
        if(target.affinities.get(type).equals(null))return d;
        if (target.affinities.get(type).equals(Affinity.WEAK))d*=1.5;
        else if (target.affinities.get(type).equals(Affinity.STRONG))d*=0.5;
        else if (target.affinities.get(type).equals(Affinity.BLOCK))d=0;
        float multiplier = (type.equals(Element.PHYSICAL) ? user.STR : user.MAG)/10;
        float power =((type.equals(Element.PHYSICAL) ? user.STR : user.MAG)/target.DEF);
        d = (d+power)*multiplier;
        if(d<0)d=0;
        return d;
    }


    public void execute(Unit user, Unit target, boolean crit){
        super.execute(user,target);
        AnimationSequence sequence = AnimationSequence.basicAtkSequence(this,user,target,calculateDmg(user, target),crit);
        sequence.start();
        mcrpg.LOGGER.log(Level.DEBUG, user.name + " Attacks!");
    }
}
