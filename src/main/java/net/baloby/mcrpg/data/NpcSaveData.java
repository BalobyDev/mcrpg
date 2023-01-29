package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.List;

public class NpcSaveData extends WorldSavedData {

    private ArrayList<Npc> npcs;

    public NpcSaveData(ArrayList<Npc> npcs) {
        super("npcs");
        this.npcs = npcs;
    }

    @Override
    public void load(CompoundNBT nbt) {
        if(nbt.get("npcs") instanceof ListNBT){
            ListNBT listNBT = (ListNBT) nbt.get("npcs");

        }
    }

    protected void loadNpcs(ListNBT nbt){
        for (int i = 0; i < nbt.size(); i++) {
            CompoundNBT compoundNBT = nbt.getCompound(i);
                Npc npc = new Npc();

                if(nbt.contains("moveSet")) {
                    npc = new BattleNpc();
                }


                npc.item = Item.byId(compoundNBT.getInt("item"));
                if(npc instanceof BattleNpc) {
                    BattleNpc bNpc = (BattleNpc) npc;
                    bNpc.MAXMP = compoundNBT.getInt("maxHp");
                    bNpc.HP = compoundNBT.getInt("hp");
                    bNpc.MAXMP = compoundNBT.getInt("maxMp");
                    bNpc.MP = compoundNBT.getInt("mp");
                    bNpc.ATK = compoundNBT.getInt("atk");
                    bNpc.DEF = compoundNBT.getInt("def");
                    bNpc.SPD = compoundNBT.getInt("spd");
                    for (int num : compoundNBT.getIntArray("moveSet")) {
                    }
                }
            }
        }






    //Give ListNBT another try

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.put("Npcs",this.saveNpcs());
        return nbt;
    }

    protected ListNBT saveNpcs(){
        ListNBT listnbt = new ListNBT();
        for (Npc character : this.npcs){
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("item", Item.getId(character.item));
            if(character instanceof BattleNpc){
                BattleNpc npc = (BattleNpc) character;
                nbt.putInt("maxHp", npc.MAXHP);
                nbt.putInt("hp", npc.HP);
                nbt.putInt("maxMp", npc.MAXMP);
                nbt.putInt("mp",npc.MP);
                nbt.putInt("atk", npc.ATK);
                nbt.putInt("def", npc.DEF);
                nbt.putInt("spd", npc.SPD);
                List<Integer> moves = new ArrayList<Integer>();
                nbt.putIntArray("moveSet",moves);
                listnbt.add(nbt);
            }
        }
        return listnbt;
    }

    public void setDirty(){

    }
}
