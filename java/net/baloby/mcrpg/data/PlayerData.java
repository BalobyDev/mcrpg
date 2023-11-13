package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.NpcProfile;
import net.baloby.mcrpg.client.gui.profile.PlayerProfile;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.quest.Status;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerData implements IPlayerData {

    private int MAX_HP = 20;
    private int MAX_MP = 20;
    private int MP = MAX_MP;
    private int STR = 10;
    private int MAG = 10;
    private int DEF = 10;
    private int SPD = 10;
    private CompoundNBT backItem = new CompoundNBT();
    private CompoundNBT moveSet = new CompoundNBT();
    private CompoundNBT availableMoves = new CompoundNBT();
    private CompoundNBT partyMembers = new CompoundNBT();
    private CompoundNBT pronouns = new CompoundNBT();
    private CompoundNBT quests = new CompoundNBT();
    private CompoundNBT sendBack = new CompoundNBT();


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
    public void addAvailableMove(MoveType type){
        int i = 0;
        for(String key : availableMoves.getAllKeys()){
            i++;
        }
        availableMoves.putString(i+"",type.getRegistryName().getPath());
    }

    @Override
    public void addMove(MoveType type) {
        for (int i = 1; i < 8; i++) {
            if(moveSet.contains(""+i)){
                if(moveSet.getString(""+i).equalsIgnoreCase(Registration.MOVE_REGISTRY.get().getKey(type).getPath()))return;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (moveSet.contains("" + i)) {
                if (moveSet.getString("" + i).equalsIgnoreCase("-")) {
                    addMove(i, type);
                    return;
                }
            }
            else {
                addMove(i,type);
            }
        }
    }

    @Override
    public void addMove(int num, MoveType type){
        if(num>0&&num<8&&type!=null)
            moveSet.putString(""+num,Registration.MOVE_REGISTRY.get().getKey(type).getPath());
    }

    @Override
    public void setPartyMembers(CompoundNBT npcTypes) {
        this.partyMembers = npcTypes;
    }

    @Override
    public void setPartyMembers(HashMap<Integer, Profile> partyMembers) {
        for (Map.Entry<Integer, Profile> entry:partyMembers.entrySet()){
            if(entry.getValue()instanceof NpcProfile){
                this.partyMembers.putString(entry.getKey()+"",Registration.NPC_REGISTRY.get().getKey(((NpcProfile) entry.getValue()).type).getPath());
            }
        }
    }


    @Override
    public CompoundNBT getMoves() {
        return moveSet;
    }

    @Override
    public void setMoves(CompoundNBT nbt) {
        this.moveSet = nbt;
    }

    @Override
    public CompoundNBT getAllMoves() {
        return availableMoves;
    }

    @Override
    public CompoundNBT getQuests() {
        return quests;
    }

    @Override
    public void setAllMoves(CompoundNBT nbt) {
        this.availableMoves = nbt;
    }

    @Override
    public void setQuests(CompoundNBT nbt) {
        this.quests = nbt;

    }

    @Override
    public CompoundNBT getPronouns(){return pronouns;}

    @Override
    public void setPronouns(CompoundNBT nbt){
        this.pronouns = nbt;
    }


    @Override
    public HashMap<Integer,MoveType> moveList(){
        CompoundNBT moves = this.getMoves();
        HashMap<Integer,MoveType> moveTypes = new HashMap<Integer, MoveType>();
        for (int i = 0; i < 8; i++) {
            if(moves.contains(""+i)){
                moveTypes.put(i,Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(mcrpg.MODID,moves.getString(""+i))));
            }
        }
        return moveTypes;
    }

    @Override
    public void setMoves(HashMap<Integer, MoveType> moves) {
        for(Map.Entry<Integer, MoveType> entry : moves.entrySet()){
            this.moveSet.putString(""+entry.getKey(),entry.getValue().getRegistryName().getPath());
        }
    }

    @Override
    public void updateQuest(Quest quest, Status status) {
//        if(quests.contains(quest.getId()))return;
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("status",status.getText());
        nbt.putString("resource_location",quest.getResourceLocation().toString());
        this.quests.put(quest.getId(), nbt);
    }

    @Override
    public HashMap<Integer,MoveType> availableMoveList(){
        CompoundNBT moves = this.getAllMoves();
        HashMap<Integer,MoveType> moveTypes = new HashMap<Integer, MoveType>();
        for (int i = 0; i < 8; i++) {
            if(moves.contains(""+i)){
                moveTypes.put(i,Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(mcrpg.MODID,moves.getString(""+i))));
            }
        }
        return moveTypes;
    }

    public CompoundNBT getSendBack(){
        return this.sendBack;
    }

    public void setSendBack(CompoundNBT nbt){
        this.sendBack = nbt;
    }

    public int getStr() {
        return STR;
    }

    @Override
    public void setStr(int str) {
        this.STR = str;
    }

    public int getMag() {
        return MAG;
    }

    @Override
    public void setMag(int mag) {
        this.MAG = mag;
    }

    public int getDef() {
        return DEF;
    }

    public void setDef(int DEF) {
        this.DEF = DEF;
    }

    public int getSpd() {
        return SPD;
    }

    public void setSpd(int SPD) {
        this.SPD = SPD;
    }

    @Override
    public CompoundNBT getBackItem() {
        return backItem;
    }

    public void setBackItem(CompoundNBT item) {
        this.backItem = item;
    }
}
