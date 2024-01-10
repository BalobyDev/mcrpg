package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.NpcProfile;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.quest.Status;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class PlayerData implements IPlayerData {

    private int LVL = 1;
    private int VIGOR = 10;
    private int MIND = 10;
    private int MP = MIND*2;
    private int ENDURANCE = 10;
    private int STR = 10;
    private int MAG = 10;
    private int DEF = 10;
    private CompoundNBT backItem = new CompoundNBT();
    private CompoundNBT moveSet = getPlayerStartingMoveSet();
    private CompoundNBT availableMoves = new CompoundNBT();
    private CompoundNBT partyMembers = new CompoundNBT();
    private CompoundNBT pronouns = new CompoundNBT();
    private CompoundNBT quests = new CompoundNBT();
    private CompoundNBT sendBack = new CompoundNBT();


    @Override
    public void setLvl(int lvl) {
        this.LVL = lvl;
    }

    @Override
    public int getLvl() {
        return LVL;
    }

    @Override
    public void setMp(int mp) {
        this.MP = mp;
    }

    @Override
    public void setVigor(int vigor) {
        this.VIGOR = vigor;
    }

    @Override
    public void setEndurance(int endurance) {
        this.ENDURANCE = endurance;
    }

    @Override
    public void setMind(int mind) {
        this.MIND = mind;
    }

    @Override
    public int getMp() {
        return MP;
    }

    @Override
    public int getMind() {
        return MIND;
    }

    @Override
    public int getVigor() {
        return VIGOR;
    }

    @Override
    public int getEndurance(){return ENDURANCE;}

    public NpcType getPartyMember(int num){
        return Registration.NPC_REGISTRY.get().getValue(new ResourceLocation(partyMembers.getString(""+num)));
    }

    @Override
    public CompoundNBT getPartyMembers() {
        return partyMembers;
    }

    public void addPartyMember(int num, NpcType type){
        if(num>0&&num<8&&type!=null)
        partyMembers.putString(""+num,type.getRegistryName().toString());
    }

    public void addPartyMember(NpcType type){
        for (int i = 1; i < 8; i++) {
            if(partyMembers.contains(""+i)){
                if(partyMembers.getString(""+i).equalsIgnoreCase(type.getRegistryName().toString()))return;
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
        availableMoves.putString(i+"",type.getRegistryName().toString());
    }

    @Override
    public void addMove(MoveType type) {
        for (int i = 0; i < 8; i++) {
            if(moveSet.contains(""+i)){
                if(moveSet.getString(""+i).equalsIgnoreCase(type.getRegistryName().toString()))return;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (moveSet.contains("" + i)) {
                if (moveSet.getString("" + i).equalsIgnoreCase("-")) {
                    addMove(i, type);
                    return;
                }
            }
            else {
                addMove(i,type);
                return;
            }
        }
    }

    @Override
    public void addMove(int num, MoveType type){
        if(num>-1&&num<8&&type!=null)
            moveSet.putString(""+num,type.getRegistryName().toString());
    }

    @Override
    public void setPartyMembers(CompoundNBT npcTypes) {
        this.partyMembers = npcTypes;
    }

    @Override
    public void setPartyMembers(HashMap<Integer, Profile> partyMembers) {
        for (Map.Entry<Integer, Profile> entry:partyMembers.entrySet()){
            if(entry.getValue()instanceof NpcProfile){
                this.partyMembers.putString(entry.getKey()+"",((NpcProfile) entry.getValue()).npc.getType().getRegistryName().toString());
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
                moveTypes.put(i,Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(moves.getString(""+i))));
            }
        }
        return moveTypes;
    }

    @Override
    public void setMoves(HashMap<Integer, MoveType> moves) {
        for(Map.Entry<Integer, MoveType> entry : moves.entrySet()){
            this.moveSet.putString(""+entry.getKey(),entry.getValue().getRegistryName().toString());
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
                moveTypes.put(i,Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(moves.getString(""+i))));
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

    @Override
    public CompoundNBT getBackItem() {
        return backItem;
    }

    public void setBackItem(CompoundNBT item) {
        this.backItem = item;
    }

    public static CompoundNBT getPlayerStartingMoveSet(){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("0","mcrpg:heal");
        return nbt;
    }
}
