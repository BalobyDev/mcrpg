package net.baloby.mcrpg.setup;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;

public class ModParticles {
    public static final RegistryObject<ParticleType<BasicParticleType>> ELECTRIC = Registration.PARTICLES.register("electric", ()-> new BasicParticleType(false));

    static void register(){

    }
}
