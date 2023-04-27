package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.data.INpcData;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class BattleNpc extends Npc {

    public int MAXHP = 20,HP, MAXMP = 20, MP, STR = 20, MAG = 20, DEF = 20, SPD = 20;
    public HashMap<Integer,MoveType> moveSet = new HashMap<>();
    public INpcData profile;


    public BattleNpc() {
        super();
    }

    public BattleNpc(NpcType<?> type, String name, ResourceLocation skin, EntityType entityType, Item item, int HP, int MP, MoveType... moves){
        super(type, name,entityType,skin);
        this.MAXHP = HP;
        this.HP = MAXHP;
        this.MAXMP = MP;
        this.MP = MAXMP;
        this.item = item;
        this.moveSet = new HashMap<>();
        for(MoveType move : moves){
            this.addMove(move);
        }
    }

    public void addMove(MoveType move){
        for (int i = 0; i < 8; i++) {
            if(!moveSet.containsKey(i)) {
                moveSet.put(i, move);
                return;
            }

        }
    }

    public void giveMp(int num){
        this.MP += num;
        if(MP>MAXMP){
            this.MP = MAXMP;
        }
        if(MP<0){
            this.MP = 0;
        }
    }

    public void giveHp(int num){
        this.HP += num;
        if(HP>MAXHP){
            this.HP = MAXHP;
        }
        if(HP<0){
            this.HP = 0;
        }
    }



    @Override
    public CompoundNBT save(){
        CompoundNBT nbt = super.save();
        nbt.putInt("maxHp", this.MAXHP);
        nbt.putInt("hp", this.HP);
        nbt.putInt("maxMp", this.MAXMP);
        nbt.putInt("mp",this.MP);
        nbt.putInt("str", this.STR);
        nbt.putInt("mag", this.MAG);
        nbt.putInt("def", this.DEF);
        nbt.putInt("spd", this.SPD);
        CompoundNBT moves = new CompoundNBT();
        for (int i = 0; i < moveSet.size(); i++) {
            if(moveSet.get(i)!=null) {
                moves.putString("" + i, Registration.MOVE_REGISTRY.get().getKey(moveSet.get(i)).getPath());
            }
        }
        nbt.put("moveset",moves);
        return nbt;
    }


    @Override
    public void load(CompoundNBT nbt){
        if(nbt==null||!nbt.contains("moveset"))return;
        super.load(nbt);
        this.MAXMP = nbt.getInt("maxHp");
        this.HP = nbt.getInt("hp");
        this.MAXMP = nbt.getInt("maxMp");
        this.MP = nbt.getInt("mp");
        this.STR = nbt.getInt("str");
        this.MAG = nbt.getInt("mag");
        this.DEF = nbt.getInt("def");
        this.SPD = nbt.getInt("spd");
        CompoundNBT moves = nbt.getCompound("moveset");
        HashMap<Integer,MoveType> movelist = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            if(moves.contains(""+i)){
                movelist.put(i,Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(mcrpg.MODID,moves.getString(""+i))));
            }
        }
        this.moveSet = movelist;
    }


    public Unit unit(){
        return null;
    }

}
