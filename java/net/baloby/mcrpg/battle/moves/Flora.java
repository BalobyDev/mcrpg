package net.baloby.mcrpg.battle.moves;

import net.minecraft.block.Blocks;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class Flora extends SpellAttack{

    public Flora() {
        super(8, Element.LIFE,new StringTextComponent("Flora"), new StringTextComponent("Deals a bit of life damage to one enemy"), new BlockParticleData(ParticleTypes.BLOCK, Blocks.OAK_LEAVES.defaultBlockState()), SoundEvents.GRASS_BREAK);
    }
}
