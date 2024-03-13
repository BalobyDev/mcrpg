package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.INpcData;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class FullRecover {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("recover").requires((source)->{
            return source.hasPermission(2);
        }).then(Commands.literal("full").executes((context)->{
            return recover(context.getSource());
        })));
    }

    public static int recover(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        player.sendMessage(new StringTextComponent("Party fully recovered!"),player.getUUID());

        IPlayerData playerProfile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        playerProfile.setMp(20);
        INpcData charProfile = player.getLevel().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
        for(String str : playerProfile.getPartyMembers().getAllKeys()){
            String name = playerProfile.getPartyMembers().getString(str);
            CompoundNBT nbt = charProfile.getNbts().getCompound(name);
            nbt.putInt("hp", nbt.getInt("vigor")*2);
            nbt.putInt("mp", nbt.getInt("mind")*2);
        }
        Animation.sound(SoundEvents.EXPERIENCE_ORB_PICKUP);
        return 0;
    }
}
