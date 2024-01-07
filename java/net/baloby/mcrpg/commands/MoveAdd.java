package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.commands.arguments.MoveArgument;
import net.baloby.mcrpg.commands.arguments.NpcArgument;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class MoveAdd {
    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("move").requires((source)->{
                    return source.hasPermission(2);
                }).then(Commands.literal("learn").then(Commands.argument("move", MoveArgument.id()).executes((context) -> {
                    return addMove(context.getSource(),MoveArgument.getMove(context,"move"));
                })))
        );
    }

    private static int addMove(CommandSource source, ResourceLocation type) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        MoveType move = Registration.MOVE_REGISTRY.get().getValue(type);
        player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().addMove(move);
        player.sendMessage(new StringTextComponent("Learned "+move.create().getName().getString()+"!"),player.getUUID());
        return 0;
    }
}
