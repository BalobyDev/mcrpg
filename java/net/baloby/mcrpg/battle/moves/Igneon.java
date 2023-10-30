package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Igneon extends AOESpell{
    public Igneon() {
        super(8, Element.FIRE, new StringTextComponent("Igneon"), new StringTextComponent("Deals light fire damage to all enemies"),
                ParticleTypes.FLAME, SoundEvents.FIRECHARGE_USE);
        this.cost = 3;
    }
}
