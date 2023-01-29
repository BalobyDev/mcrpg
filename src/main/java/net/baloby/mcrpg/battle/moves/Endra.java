package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Endra extends SpellAttack{


    public Endra() {
        super(8,Element.ENDER,"Endra", ParticleTypes.DRAGON_BREATH, SoundEvents.ENDERMAN_TELEPORT);
    }
}
