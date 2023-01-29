package net.baloby.mcrpg.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.baloby.mcrpg.entities.render.*;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModParticles;
import net.baloby.mcrpg.setup.Particles.ElectricParicle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE )

public class ClientEventBusSubscriber {
    public static void init(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANOID.get(), manager -> {
            return new HumanoidRenderer(manager, false);
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANOID_SLIM.get(), manager -> {
            return new HumanoidRenderer(manager, true);
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ICEOLOGER.get(), IceologerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NEW_ENDERMAN.get(), EndermanRenderer::new);

        MinecraftForge.EVENT_BUS.register(new OverlayGui());


    }

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event){
        event.setCanceled(true);
        if(event.getPlayer() instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) event.getPlayer();
            EntityRenderer renderer = event.getRenderer();
            MatrixStack matrixStack = event.getMatrixStack();

                new NewPlayerRenderer(event.getRenderer().getDispatcher()).render(player, event.getPlayer().rotA, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());



            }
        }

}
