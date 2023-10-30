package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class Aqua extends SpellAttack{
    public Aqua() {
        super(8, Element.ICE, new StringTextComponent("Aqua"), new StringTextComponent("Deals a bit of ice damage to one enemy"),
                ParticleTypes.FISHING, SoundEvents.GLASS_BREAK);
    }
}
