package net.baloby.mcrpg.network;

import net.baloby.mcrpg.data.characters.Npc;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.swing.text.html.parser.Entity;
import java.util.function.Supplier;

public class PacketSendCharacter {

    private Entity entity;
    private Npc npc;
    private IEntityAdditionalSpawnData spawnData;


    public PacketSendCharacter(PacketBuffer buffer){
    }

    public PacketSendCharacter(Entity entity, Npc npc, IEntityAdditionalSpawnData spawnData){
        this.entity = entity;
        this.npc = npc;
        this.spawnData = spawnData;
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{

        });
        return true;
    }

    public static void encode(PacketSendCharacter character){

    }

}
