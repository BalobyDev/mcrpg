package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class UniqueFeatures implements IUniqueStructures {

    private CompoundNBT features = new CompoundNBT();

    @Override
    public CompoundNBT getStructures() {
        return features;
    }

    @Override
    public void setStructures(CompoundNBT nbt) {
        features = nbt;
    }

    @Override
    public void addStructure(String string, BlockPos pos){
        features.putIntArray(string,new int[]{pos.getX(),pos.getY(),pos.getZ()});
    }
}
