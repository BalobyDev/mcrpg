package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.tools.Animation;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Heal extends SpellMove implements INonBattleMove{

    public float hp;
    public IParticleData particle = ParticleTypes.COMPOSTER;
    public SoundEvent sound = SoundEvents.EXPERIENCE_ORB_PICKUP;


    public Heal() {
        this(new StringTextComponent("Heal"),new StringTextComponent("Restores a bit of hp to one ally"),20);
        this.cost = 1;
        this.type = Element.SUPPORT;
    }

    public Heal(ITextComponent name, ITextComponent desc, float hp){
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

    @Override
    public void nonBattleExecute(Profile user, Profile target) {
        target.addHp(20);
        user.addMp(-1);
        if(user.name == target.name){
            user.addHp(20);
        }
        Animation.sound(sound);
        target.save();

    }
}
