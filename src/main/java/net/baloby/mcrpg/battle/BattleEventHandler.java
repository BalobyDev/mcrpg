package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.mcrpg;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = mcrpg.MODID, bus = Bus.FORGE)
public class BattleEventHandler {

    @SubscribeEvent
    public void handRenderEvent(RenderHandEvent event){
        if(Battle.isActive = true){
            event.setCanceled(true);
        }
    }
}
