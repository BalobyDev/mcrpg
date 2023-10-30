package net.baloby.mcrpg.network;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class RpgNetwork {
    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID(){

        return ID++;
    }

    public static void registerMessages(){
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(mcrpg.MODID,"mcrpg"),()->"1.0",
                s -> true,
                s -> true);

        INSTANCE.messageBuilder(PacketBattle.class, nextID())
                .encoder((packetBattle, packetBuffer) -> {})
                .decoder(buf -> new PacketBattle())
                .consumer(PacketBattle::handle)
                .add();
        INSTANCE.messageBuilder(PacketTest.class, nextID())
                .encoder((packetTest, packetBuffer) -> {})
                .decoder(buf -> new PacketTest())
                .consumer(PacketTest::handle)
                .add();
        INSTANCE.messageBuilder(PacketSendCharacter.class, nextID())
                .encoder((packetSendCharacter, packetBuffer) -> {})
                .decoder(buf ->new PacketSendCharacter(buf))
                .consumer(PacketSendCharacter::handle)
                .add();
        INSTANCE.messageBuilder(PlayerSyncPacket.class, nextID())
                .encoder((playerSyncPacket, buffer) -> {})
                .decoder(buffer -> new PlayerSyncPacket(buffer))
                .consumer(PlayerSyncPacket::handle)
                .add();

    }
    public static void sendToClient(Object packet, ServerPlayerEntity player){
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);

    }

    public static void sendToServer(Object packet){
        INSTANCE.sendToServer(packet);

    }


}
