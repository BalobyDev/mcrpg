package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class Endra extends SpellAttack{

    public Endra() {
        super(8,Element.ENDER,new StringTextComponent("Endra"), new StringTextComponent("Deals a bit of ender damage to one enemy"), ParticleTypes.DRAGON_BREATH, SoundEvents.ENDERMAN_TELEPORT);
    }
}
