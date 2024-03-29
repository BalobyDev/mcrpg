package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class NpcStorage implements Capability.IStorage<INpcData> {

    @Override
    @Nullable
    public CompoundNBT writeNBT(Capability<INpcData> capability, INpcData instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
//        nbt.put("npcs",instance.saveNpcs());
        return instance.saveNpcs();
    }

    @Override
    public void readNBT(Capability<INpcData> capability, INpcData instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
            instance.setNbts((CompoundNBT) nbt);
        }
    }
}
