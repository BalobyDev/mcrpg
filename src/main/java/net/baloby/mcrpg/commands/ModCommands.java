package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> cmdRpg = dispatcher.register(
                Commands.literal(mcrpg.MODID)
                        .then(TestCommand.register(dispatcher))
                        .then(CharacterCommand.register(dispatcher))
        );
        dispatcher.register(Commands.literal("rpg").redirect(cmdRpg));
    }

}
