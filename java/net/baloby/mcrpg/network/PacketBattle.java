package net.baloby.mcrpg.network;

import  net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


public class PacketBattle {


    public boolean handle(Supplier<NetworkEvent.Context> ctx){
      ctx.get().enqueueWork(() -> {
          PlayerEntity player = Minecraft.getInstance().player;
          player.yRot = 0;
          player.xRot = 0;
      });
      return true;
    }



}
