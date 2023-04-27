package net.baloby.mcrpg.setup;

import com.mojang.serialization.Lifecycle;
import net.baloby.mcrpg.commands.CharacterCommand;
import net.baloby.mcrpg.commands.arguments.NpcArgument;
import net.baloby.mcrpg.data.dialouge.DialogueManager;
import net.baloby.mcrpg.quest.QuestManager;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.server.command.ConfigCommand;
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

import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = mcrpg.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final DialogueManager DIALOGUE_MANAGER = new DialogueManager();
    public static final QuestManager QUEST_MANAGER = new QuestManager();


    @SubscribeEvent
    public static void onAddReloadListenerEvent(AddReloadListenerEvent event){
        event.addListener(DIALOGUE_MANAGER);
        event.addListener(QUEST_MANAGER);
    }

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        ModCommands.register(event.getDispatcher());
        CharacterCommand.register(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }


}
