package net.baloby.mcrpg.tools;

import net.minecraft.util.concurrent.ThreadTaskExecutor;
import net.minecraftforge.fml.LogicalSidedProvider;

public class Future {

    public static void wait(Runnable runnable){
        //ThreadTaskExecutor<?> executor = LogicalSidedProvider.WORKQUEUE.get(getDirection().getReceptionSide())
        runnable.run();
    }
}
