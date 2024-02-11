package net.baloby.mcrpg.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.baloby.mcrpg.entities.render.*;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE )

public class ClientEventBusSubscriber {

    public static NewFPRenderer fpRenderer = new NewFPRenderer(Minecraft.getInstance());
    public static void init(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CORPSE.get(), CorpseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CULTIST.get(), CultistRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.WOODLAND_FAIRY.get(), FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.KNIGHT.get(), KnightRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANOID.get(), manager -> {
            return new HumanoidRenderer(manager, false);
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANOID_SLIM.get(), manager -> {
            return new HumanoidRenderer(manager, true);
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANOID_PIGLIN.get(), manager -> {
            return new HumanoidPiglinRenderer(manager, false);
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ICEOLOGER.get(), IceologerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.INFERNO.get(), InfernoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NEW_ENDERMAN.get(), EndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.VILER_WITCH.get(), VilerWitchRenderer::new);
        MinecraftForge.EVENT_BUS.register(new OverlayGui());




    }

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event){
        event.setCanceled(true);
        if(event.getPlayer() instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) event.getPlayer();
            EntityRenderer renderer = event.getRenderer();
            MatrixStack matrixStack = event.getMatrixStack();
                new NewPlayerRenderer(renderer.getDispatcher()).render(player, event.getPlayer().rotA, event.getPartialRenderTick(), matrixStack, event.getBuffers(), event.getLight());

            }
        }

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event){
        if(Battle.isActive){
            event.setCanceled(true);
            return;
        }
        if(event.getItemStack().isEmpty()) {
//            event.setCanceled(true);
//            fpRenderer.renderHandsWithItems(event.getEquipProgress(), event.getMatrixStack(), (IRenderTypeBuffer.Impl) event.getBuffers(), Minecraft.getInstance().player, event.getLight());
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        if(fpRenderer!=null&&event.player instanceof ClientPlayerEntity&&event.player.getMainHandItem().isEmpty()) fpRenderer.tick();

    }
}
