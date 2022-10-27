package net.baloby.mcrpg.setup;

import com.mojang.serialization.Lifecycle;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.baloby.mcrpg.commands.ModCommands;
import net.baloby.mcrpg.mcrpg;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {


    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        ModCommands.register(event.getDispatcher());
    }
}
