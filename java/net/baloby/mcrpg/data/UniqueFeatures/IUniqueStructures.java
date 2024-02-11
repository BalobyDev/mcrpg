package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public interface IUniqueStructures {

    CompoundNBT getStructures();

    void setStructures(CompoundNBT nbt);

    void addStructure(String string, BlockPos pos);
}
