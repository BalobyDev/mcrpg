package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class Aqua extends SpellAttack{
    public Aqua() {
        super(8, Element.ICE, new TranslationTextComponent("move.mcrpg.aqua"), new StringTextComponent("Deals a bit of ice damage to one enemy"),
                ParticleTypes.FISHING, SoundEvents.GLASS_BREAK);
    }
}
