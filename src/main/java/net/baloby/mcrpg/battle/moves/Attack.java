package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Dice;
import org.apache.logging.log4j.Level;

public class Attack extends Move {

    public float dmg;
    public float spd = 100;

    public Attack(){
        super("Attack");
        this.dmg = 5;
        this.type = Element.PHYSICAL;
    }

    public Attack(float dmg, Element type, String name){
        super(name);
        this.dmg = dmg;
        this.type = type;
        this.name = name;
    }
    public void tryAttack(Unit user, Unit target,float dmg){
        if(Dice.roll(100)+1<=10) {
            target.miss();
            return;
        }
        target.takeDamage(dmg);
    }

    public void execute(Unit user, Unit target){
        super.execute(user,target);
        //local variable
        float d = dmg;
        if (target.affinities.get(type).equals(Affinity.WEAK))d*=1.5;
        else if (target.affinities.get(type).equals(Affinity.STRONG))d*=0.5;
        else if (target.affinities.get(type).equals(Affinity.BLOCK))d=0;
        AnimationSequence sequence = AnimationSequence.basicAtkSequence(this,user,target,d);
        sequence.start();
        mcrpg.LOGGER.log(Level.DEBUG, user.name + " Attacks!");
    }
}
