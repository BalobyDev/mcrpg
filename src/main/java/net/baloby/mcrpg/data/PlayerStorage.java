package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class PlayerStorage implements Capability.IStorage<IPlayerProfile> {


    @Override
    @Nullable
    public INBT writeNBT(Capability<IPlayerProfile> capability, IPlayerProfile instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("mp",instance.getMp());
        nbt.putInt("maxmp",instance.getMaxMp());
        nbt.put("party", instance.getPartyMembers());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerProfile> capability, IPlayerProfile instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
            instance.setMp(((CompoundNBT) nbt).getInt("mp"));
            instance.setMaxMp(((CompoundNBT) nbt).getInt("maxmp"));
            instance.setPartyMembers((CompoundNBT) ((CompoundNBT) nbt).get("party"));
        }
    }
}
