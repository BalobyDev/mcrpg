package net.baloby.mcrpg.setup.Particles;

import com.mojang.serialization.Codec;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class IndicatorParticle extends ParticleType implements IParticleData{

    public String text;


    public IndicatorParticle(IDeserializer p_i50792_2_) {
        super(false, p_i50792_2_);
    }

    @Override
    public Codec<?> codec() {return null;}

    @Override
    public ParticleType<?> getType() {
        return null;
    }

    @Override
    public void writeToNetwork(PacketBuffer p_197553_1_) {

    }

    @Override
    public String writeToString() {
        return null;
    }
}
