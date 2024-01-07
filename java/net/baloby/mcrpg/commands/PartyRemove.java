package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.commands.arguments.NpcArgument;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class PartyRemove {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("party").requires((source)->{
                    return source.hasPermission(2);
                }).then(Commands.literal("remove").then(Commands.argument("npc", NpcArgument.id()).executes((context) -> {
                    return removeNpc(context.getSource(),NpcArgument.getSummonableNpc(context,"npc"));
                })))
                );
    }

    private static int removeNpc(CommandSource source, ResourceLocation type) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        NpcType npc = Registration.NPC_REGISTRY.get().getValue(type);
        CompoundNBT party = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().getPartyMembers();
        String num = "";
        for (String index : party.getAllKeys()){
            if(party.getString(index).equals(type.toString())){
                num = index;
            }
        }
        if(party.contains(num)){
            party.remove(num);
        }

        player.sendMessage(new StringTextComponent(npc.create().getName()+" has left your party!"),player.getUUID());
        return 0;
    }
}
