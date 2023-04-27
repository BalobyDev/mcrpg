package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.quest.Status;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPlayerData {

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

    void setPartyMembers(HashMap<Integer, Profile> partyMembers);

    CompoundNBT getMoves();

    void setMoves(CompoundNBT nbt);

    CompoundNBT getPronouns();

    CompoundNBT getAllMoves();

    CompoundNBT getQuests();

    void setAllMoves(CompoundNBT nbt);

    void setQuests(CompoundNBT nbt);

    void addMove(MoveType type);

    void addMove(int num, MoveType type);

    void addAvailableMove(MoveType type);

    void updateQuest(Quest quest, Status status);
    void setPronouns(CompoundNBT nbt);

    HashMap<Integer, MoveType> moveList();

    void setMoves(HashMap<Integer, MoveType> moves);

    HashMap<Integer, MoveType> availableMoveList();

    void setSendBack(CompoundNBT nbt);

    CompoundNBT getSendBack();
}
