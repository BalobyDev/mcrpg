package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundEvent;

public class SpellAttack extends Attack{

    public IParticleData particle;
    public SoundEvent sound;

    public SpellAttack(float dmg, Element type, String name, IParticleData particle, SoundEvent sound){
        super(dmg,type,name);
        this.particle = particle;
        this.sound = sound;
        this.cost = 1;
    }

    public void execute(Unit user, Unit target){
        super.execute(user,target);

    }
}
