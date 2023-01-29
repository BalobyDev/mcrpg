package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class PlayerProfile implements IPlayerProfile{

    private int MAX_HP = 20;
    private int MAX_MP = 20;
    private int MP = MAX_MP;
    private CompoundNBT moveSet = new CompoundNBT();
    private CompoundNBT partyMembers = new CompoundNBT();


    @Override
    public void setMp(int mp) {
        this.MP = mp;
    }

    @Override
    public void setMaxMp(int maxMp) {
        this.MAX_MP = maxMp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.MAX_HP = maxHp;
    }

    @Override
    public int getMp() {
        return MP;
    }

    @Override
    public int getMaxMp() {
        return MAX_MP;
    }

    @Override
    public int getMaxHp() {
        return MAX_HP;
    }

    public NpcType getPartyMember(int num){
        return Registration.NPC_REGISTRY.get().getValue(new ResourceLocation(mcrpg.MODID,partyMembers.getString(""+num)));
    }

    @Override
    public CompoundNBT getPartyMembers() {
        return partyMembers;
    }

    public void addPartyMember(int num, NpcType type){
        if(num>0&&num<8&&type!=null)
        partyMembers.putString(""+num,Registration.NPC_REGISTRY.get().getKey(type).getPath());
    }

    public void addPartyMember(NpcType type){
        for (int i = 1; i < 8; i++) {
            if(partyMembers.contains(""+i)){
                if(partyMembers.getString(""+i).equalsIgnoreCase(Registration.NPC_REGISTRY.get().getKey(type).getPath()))return;
            }
        }
        for (int i = 1; i < 8; i++) {
            if(partyMembers.contains(""+i)){
                if(partyMembers.getString(""+i).equalsIgnoreCase("-")) {
                    addPartyMember(i, type);
                    return;
                }
            }
            else {
                addPartyMember(i,type);
                return;
            }
        }
    }

    @Override
    public void setPartyMembers(CompoundNBT npcTypes) {
        this.partyMembers = npcTypes;
    }

//    @Override
//    public List<Integer> getMoves() {
//
//    }
//
//    @Override
//    public void setMoves(int[] integers) {
//
//    }
}
