package net.baloby.mcrpg.client;

import net.baloby.mcrpg.client.gui.OverlayGui;
import net.baloby.mcrpg.entities.render.HumanoidRenderer;
import net.baloby.mcrpg.entities.render.IceologerRenderer;
import net.baloby.mcrpg.entities.render.RanaRenderer;
import net.baloby.mcrpg.entities.render.SteveRenderer;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE )

public class ClientEventBusSubscriber {
    public static void init(final FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANOID.get(), HumanoidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ICEOLOGER.get(), IceologerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.RANA.get(), RanaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.STEVE.get(), SteveRenderer::new);


        MinecraftForge.EVENT_BUS.register(new OverlayGui());

    }
}
