package net.baloby.mcrpg.data;

import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.List;

public interface IPlayerProfile {

    void setMp(int mp);

    void setMaxMp(int maxMp);

    void setMaxHp(int maxHp);

    int getMp();

    int getMaxMp();

    int getMaxHp();

    NpcType getPartyMember(int num);

    CompoundNBT getPartyMembers();

    void addPartyMember(int num, NpcType type);

    void addPartyMember(NpcType type);

    void setPartyMembers(CompoundNBT npcTypes);

//    List<Integer> getMoves();
//
//    void setMoves(int[] integers);

}
