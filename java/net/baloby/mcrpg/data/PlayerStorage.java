package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerStorage implements Capability.IStorage<IPlayerData> {


    @Override
    @Nullable
    public INBT writeNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("mp",instance.getMp());
        nbt.putInt("maxmp",instance.getMaxMp());
        nbt.put("party", instance.getPartyMembers());
        nbt.put("moves", instance.getMoves());
        nbt.put("allmoves", instance.getAllMoves());
        nbt.put("pronouns",instance.getPronouns());
        nbt.put("quests",instance.getQuests());
        nbt.put("send_back",instance.getSendBack());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
            instance.setMp(((CompoundNBT) nbt).getInt("mp"));
            instance.setMaxMp(((CompoundNBT) nbt).getInt("maxmp"));
            instance.setPartyMembers((CompoundNBT) ((CompoundNBT) nbt).get("party"));
            instance.setMoves((CompoundNBT) ((CompoundNBT) nbt).get("moves"));
            instance.setAllMoves((CompoundNBT) ((CompoundNBT) nbt).get("allmoves"));
            instance.setPronouns((CompoundNBT) ((CompoundNBT) nbt).get("pronouns"));
            instance.setQuests((CompoundNBT) ((CompoundNBT) nbt).get("quests"));
            instance.setSendBack(((CompoundNBT) nbt).getCompound("send_back"));
        }
    }
}
