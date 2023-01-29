package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Igni extends SpellAttack{
    public Igni() {
        super(8, Element.FIRE,"Igni", ParticleTypes.FLAME, SoundEvents.FIRECHARGE_USE);
    }
}
