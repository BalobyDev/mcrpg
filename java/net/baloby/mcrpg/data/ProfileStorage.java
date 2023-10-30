package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ProfileStorage implements Capability.IStorage<ICharProfile> {


    @Override
    @Nullable
    public INBT writeNBT(Capability<ICharProfile> capability, ICharProfile instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("mp",instance.getMp());
        nbt.putInt("maxmp",instance.getMaxMp());
        return nbt;
    }

    @Override
    public void readNBT(Capability<ICharProfile> capability, ICharProfile instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
            instance.setMp(((CompoundNBT) nbt).getInt("mp"));
            instance.setMaxMp(((CompoundNBT) nbt).getInt("maxmp"));
        }



    }
}
