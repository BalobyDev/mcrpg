package net.baloby.mcrpg.network;

import net.baloby.mcrpg.setup.ModDimensions;
import net.baloby.mcrpg.world.StructureGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketTest {


    public boolean handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{


        });
        return true;
    }
}
