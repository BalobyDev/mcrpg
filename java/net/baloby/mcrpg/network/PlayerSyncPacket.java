package net.baloby.mcrpg.network;

import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerSyncPacket {


    public PlayerSyncPacket(PacketBuffer buffer) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().getSender().getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        return true;
    }
}
