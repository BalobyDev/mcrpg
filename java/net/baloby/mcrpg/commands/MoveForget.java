package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.commands.arguments.MoveArgument;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.PlayerData;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class MoveForget {
    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("move").requires((source)->{
                    return source.hasPermission(2);
                }).then(Commands.literal("forget").then(Commands.argument("move", MoveArgument.id()).executes((context) -> {
                    return forget(context.getSource(),MoveArgument.getMove(context,"move"));
                })))
        );
    }

    private static int forget(CommandSource source, ResourceLocation type) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        IPlayerData data = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        if(type.getPath().equals("all")){
            data.setMoves(new CompoundNBT());
            player.sendMessage(new StringTextComponent("Forgot all moves!"),player.getUUID());
            return 0;
        }
        MoveType move = Registration.MOVE_REGISTRY.get().getValue(type);
        String toRemove = "";
        for (String index : data.getMoves().getAllKeys()){
            if(data.getMoves().getString(index).equals(type.toString())) {
                toRemove = index;
            }
        }
        if (toRemove != "") {
            data.getMoves().remove(toRemove);
        }
        player.sendMessage(new StringTextComponent("Forgot "+move.create().getName().getString()+"!"),player.getUUID());
        return 0;
    }

}
