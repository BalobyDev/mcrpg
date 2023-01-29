package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.setup.ModParticles;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Voltage extends SpellAttack{
    public Voltage() {
        super(8, Element.ELECTRIC,"Voltage", (IParticleData) ModParticles.ELECTRIC.get(), SoundEvents.NOTE_BLOCK_BIT);
    }
}
