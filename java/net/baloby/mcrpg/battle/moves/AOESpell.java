package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Animation;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;

public class AOESpell extends SpellAttack{
    public AOESpell(float dmg, Element type, ITextComponent name, ITextComponent desc, IParticleData particle, SoundEvent sound) {
        super(dmg, type, name, desc, particle, sound);
    }

    public void tryAttack(Unit user, Unit target, float dmg){
        super.tryAttack(user,target,dmg);
    }

    public void execute(Unit user, Unit target){
        deduct(user);
        AnimationSequence sequence = AnimationSequence.aoeAttackSequence(this,user,target.party);
        sequence.start();
    }
}
