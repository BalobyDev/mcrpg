package net.baloby.mcrpg.tools;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class Clock {

    public static int ticks = 0;

    public static int waitTime = 0;

    public static void setWaitTime(int wait){waitTime = wait;}

    private static Runnable runnable;

    public static void waitFor(int seconds, Runnable run) {
        setWaitTime(seconds);
        runnable = run;
        ticks = 0;
    }

    @SubscribeEvent
    public static void tickEvent(TickEvent.ServerTickEvent event){
        ticks++;
        if(!(Battle.isActive))return;
        Battle.getInstance().tick();
        if(waitTime*20 == ticks) {
            runnable.run();
        }
    }
}
