package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class UniqueFeaturesStorage implements Capability.IStorage<IUniqueFeatures> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IUniqueFeatures> capability, IUniqueFeatures instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("features",instance.getFeatures());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IUniqueFeatures> capability, IUniqueFeatures instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
        instance.setFeatures(((CompoundNBT) nbt).getCompound("features"));
        }

    }
}
