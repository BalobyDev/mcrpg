package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Aqua extends SpellAttack{
    public Aqua() {
        super(8, Element.ICE, "Aqua", ParticleTypes.FISHING, SoundEvents.GLASS_BREAK);
    }
}
