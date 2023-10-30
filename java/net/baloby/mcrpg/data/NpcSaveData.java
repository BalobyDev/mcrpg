package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.WorldSavedData;

import java.util.List;
import java.util.ListIterator;

public class NpcSaveData extends WorldSavedData {

    public NpcSaveData(String id) {
        super(id);
    }

    @Override
    public void load(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        CompoundNBT saveData = new CompoundNBT();
        //for(ListIterator<StorageObject>)
        return saveData;
    }

    static class StorageObject{
//        private final ResourceLocation skin;
//        private final
    }
}
