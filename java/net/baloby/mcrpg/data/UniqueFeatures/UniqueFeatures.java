package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class UniqueFeatures implements IUniqueFeatures{

    private CompoundNBT features = new CompoundNBT();

    @Override
    public CompoundNBT getFeatures() {
        return features;
    }

    @Override
    public void setFeatures(CompoundNBT nbt) {
        features = nbt;
    }

    @Override
    public void addFeature(String string, BlockPos pos){
        features.putIntArray(string,new int[]{pos.getX(),pos.getY(),pos.getZ()});
    }
}
