package net.baloby.mcrpg.setup.Particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArrowParticle extends RisingParticle {

    protected ArrowParticle(ClientWorld p_i232425_1_, double p_i232345_2_, double p_i232345_4_, double p_i232345_6_, float p_i232345_8_, float p_i232345_10_, double p_i232345_11_, float p_i232345_17_, IAnimatedSprite p_i232345_18_) {
        super(p_i232425_1_, p_i232345_2_, p_i232345_4_, p_i232345_6_, 0.1F, 0.1F, 0.1F, p_i232345_8_, p_i232345_10_, p_i232345_11_, p_i232345_17_, p_i232345_18_, 0.3F, 8, 0.004D, true);
        this.lifetime = 20;
    }

    @Override
    public IParticleRenderType getRenderType() {return IParticleRenderType.PARTICLE_SHEET_LIT;}


    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite p_i50607_1_) {
            this.sprite = p_i50607_1_;
        }

        public Particle createParticle(BasicParticleType type, ClientWorld world, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            ArrowParticle arrowParticle = new ArrowParticle(world,p_199234_3_,p_199234_5_,p_199234_7_, (float) p_199234_9_, (float) p_199234_11_,p_199234_13_,1.0f, this.sprite);
            arrowParticle.pickSprite(this.sprite);
            return arrowParticle;
        }
    }
}
