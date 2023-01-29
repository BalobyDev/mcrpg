package net.baloby.mcrpg.battle.moves;

import net.minecraft.block.Blocks;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class Flora extends SpellAttack{

    public Flora() {
        super(8, Element.LIFE,"Flora", new BlockParticleData(ParticleTypes.BLOCK, Blocks.OAK_LEAVES.defaultBlockState()), SoundEvents.GRASS_BREAK);
    }
}
