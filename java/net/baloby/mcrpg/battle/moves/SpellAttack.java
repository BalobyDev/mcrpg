package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;

public class SpellAttack extends AttackMove {

    public IParticleData particle;
    public SoundEvent sound;

    public SpellAttack(float dmg, Element type, ITextComponent name, ITextComponent desc, IParticleData particle, SoundEvent sound){
        super(dmg,type,name,desc);
        this.particle = particle;
        this.sound = sound;
        this.ranged = true;
        this.cost = 1;
    }


    public void tryAttack(Unit user, Unit target, float dmg){
        super.tryAttack(user, target, dmg);
        Animation.particles(target, particle, 10,sound);
    }

    public void execute(Unit user, Unit target){
        super.execute(user,target,false);
    }

    @Override
    public SoundEvent getSound(){return sound;}
}
