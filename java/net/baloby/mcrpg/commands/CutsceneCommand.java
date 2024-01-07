package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CutsceneCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("cutscene").requires((source)->{
            return source.hasPermission(2);
        }));
    }
}
