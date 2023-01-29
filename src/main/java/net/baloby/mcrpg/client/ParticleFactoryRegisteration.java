package net.baloby.mcrpg.client;

import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModParticles;
import net.baloby.mcrpg.setup.Particles.ElectricParicle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)

public class ParticleFactoryRegisteration {

    @SubscribeEvent
    public static void particleFactoryRegister(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticles.ELECTRIC.get(), ElectricParicle.Factory::new);
    }
}
