package net.baloby.mcrpg.network;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.NpcData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class NpcSavePacket {

    public BattleNpc npc;


    public NpcSavePacket(PacketBuffer buffer) {
    }

    public NpcSavePacket(BattleNpc npc){
        this.npc = npc;
    }

    public void encode(){}

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
             ctx.get().getSender().getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
        });
        return true;
    }
}
