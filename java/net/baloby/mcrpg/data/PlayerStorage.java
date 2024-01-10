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
        nbt.putInt("vigor",instance.getVigor());
        nbt.putInt("mind",instance.getMind());
        nbt.putInt("endurance",instance.getEndurance());
        nbt.putInt("str",instance.getStr());
        nbt.putInt("mag",instance.getMag());
        nbt.putInt("def",instance.getDef());
        nbt.put("party", instance.getPartyMembers());
        nbt.put("moves", instance.getMoves());
        nbt.put("all_moves", instance.getAllMoves());
        nbt.put("pronouns",instance.getPronouns());
        nbt.put("quests",instance.getQuests());
        nbt.put("send_back",instance.getSendBack());
        nbt.put("back_item", instance.getBackItem());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side, INBT nbt) {
        if(nbt instanceof CompoundNBT){
            instance.setMp(((CompoundNBT) nbt).getInt("mp"));
            instance.setVigor(((CompoundNBT) nbt).getInt("vigor"));
            instance.setMind(((CompoundNBT) nbt).getInt("mind"));
            instance.setEndurance(((CompoundNBT) nbt).getInt("endurance"));
            instance.setStr(((CompoundNBT) nbt).getInt("str"));
            instance.setMag(((CompoundNBT) nbt).getInt("mag"));
            instance.setDef(((CompoundNBT) nbt).getInt("def"));
            instance.setPartyMembers((CompoundNBT) ((CompoundNBT) nbt).get("party"));
            instance.setMoves((CompoundNBT) ((CompoundNBT) nbt).get("moves"));
            instance.setAllMoves((CompoundNBT) ((CompoundNBT) nbt).get("all_moves"));
            instance.setPronouns((CompoundNBT) ((CompoundNBT) nbt).get("pronouns"));
            instance.setQuests((CompoundNBT) ((CompoundNBT) nbt).get("quests"));
            instance.setSendBack(((CompoundNBT) nbt).getCompound("send_back"));
            instance.setBackItem(((CompoundNBT) nbt).getCompound("back_item"));
        }
    }
}
