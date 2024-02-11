package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class UniqueFeaturesStorage implements Capability.IStorage<IUniqueStructures> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IUniqueStructures> capability, IUniqueStructures instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("features",instance.getStructures());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IUniqueStructures> capability, IUniqueStructures instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
        instance.setStructures(((CompoundNBT) nbt).getCompound("features"));
        }

    }
}
