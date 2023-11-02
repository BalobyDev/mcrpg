package net.baloby.mcrpg.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.client.gui.PartyManagerScreen;
import net.baloby.mcrpg.cutscene.Cutscene;
import net.baloby.mcrpg.entities.custom.enemies.NewEndermanEntity;
import net.baloby.mcrpg.setup.ModDimensions;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

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
        Cutscene cutscene = new Cutscene(new CompoundNBT(), player.getServer().overworld().dimensionType(),ModDimensions.ARENA);
        cutscene.loadCutscene(player);
        return 0;
    }
}