package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.tools.Animation;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Heal extends SpellMove{

    public float hp;
    public IParticleData particle = ParticleTypes.COMPOSTER;
    public SoundEvent sound = SoundEvents.EXPERIENCE_ORB_PICKUP;


    public Heal() {
        super("Heal","Restores a bit of hp to a single party member");
        this.hp = 20;
        this.cost = 2;
        this.type = Element.SUPPORT;
    }

    public Heal(String name, String desc,float hp){
        super(name,desc);
        this.hp = hp;
        this.type = Element.SUPPORT;
    }

    public void execute(Unit user, Unit target){
        super.execute(user,target);
        user.battle.camera.setFacing(target);
        AnimationSequence sequence = AnimationSequence.basicAllySequence(this,user,target,hp);
        sequence.start();
    }
}
