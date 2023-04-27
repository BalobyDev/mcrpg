package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public interface IUniqueFeatures {

    CompoundNBT getFeatures();

    void setFeatures(CompoundNBT nbt);

    void addFeature(String string, BlockPos pos);
}
