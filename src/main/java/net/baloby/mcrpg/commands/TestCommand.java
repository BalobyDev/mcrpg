package net.baloby.mcrpg.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
public class TestCommand implements Command<CommandSource> {

    private static final TestCommand CMD = new TestCommand();
    public static final ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("test")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);
    }
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        double a = (double) Dice.roll(10)/10;
        player.sendMessage(new StringTextComponent(a+""),null);
        return 0;
    }
}