package net.baloby.mcrpg.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
public class CharacterCommand implements Command<CommandSource> {

    private static final CharacterCommand CMD = new CharacterCommand();
    public static final ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("character")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);
    }
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        Entity e = Npcs.RANA.spawn(player.getLevel(),player.position());
        e.moveTo(player.position());
        return 0;
    }
}