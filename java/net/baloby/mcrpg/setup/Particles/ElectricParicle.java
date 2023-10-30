package net.baloby.mcrpg.setup.Particles;

import com.mojang.serialization.Codec;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;


public class ElectricParicle extends SpriteTexturedParticle {


    protected ElectricParicle(ClientWorld p_i232447_1_, double p_i232447_2_, double p_i232447_4_, double p_i232447_6_) {
        super(p_i232447_1_, p_i232447_2_, p_i232447_4_, p_i232447_6_);
        this.lifetime = 10;
    }

    @Override
    public IParticleRenderType getRenderType() {return IParticleRenderType.PARTICLE_SHEET_OPAQUE;}

    public void tick(){
        if(this.age++ >= this.lifetime){
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite p_i50607_1_) {
            this.sprite = p_i50607_1_;
        }

        public Particle createParticle(BasicParticleType type, ClientWorld world, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            ElectricParicle electricParicle = new ElectricParicle(world,p_199234_3_,p_199234_5_,p_199234_7_);
            electricParicle.pickSprite(this.sprite);
            return electricParicle;
        }
    }
}
