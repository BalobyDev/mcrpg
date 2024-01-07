package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class Igni extends SpellAttack{
    public Igni() {
        super(8, Element.FIRE,new TranslationTextComponent("move.mcrpg.igni"), new StringTextComponent("Deals a bit of fire damage to one enemy"), ParticleTypes.FLAME, SoundEvents.FIRECHARGE_USE);
    }
}
