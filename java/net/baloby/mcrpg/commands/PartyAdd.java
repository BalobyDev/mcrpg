package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.commands.arguments.NpcArgument;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class PartyAdd {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("party").requires((source)->{
                    return source.hasPermission(2);
                }).then(Commands.literal("add").then(Commands.argument("npc",NpcArgument.id()).executes((context) -> {
                    return addNpc(context.getSource(),NpcArgument.getSummonableNpc(context,"npc"));
                })))
                );
    }

    private static int addNpc(CommandSource source, ResourceLocation type) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        NpcType npc = Registration.NPC_REGISTRY.get().getValue(type);
        player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().addPartyMember(npc);
        player.sendMessage(new StringTextComponent(npc.create().getName().getString()+" has joined your party!"),player.getUUID());
        return 0;
    }
}
