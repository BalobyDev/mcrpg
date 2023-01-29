package net.baloby.mcrpg.data.characters;

import jdk.nashorn.internal.ir.Block;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.ICharProfile;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleNpc extends Npc {

    public int MAXHP = 20,HP, MAXMP = 20, MP = 20, ATK = 20, DEF = 20, SPD = 20;
    public ArrayList<MoveType> moveSet = new ArrayList<MoveType>();
    public ICharProfile profile;


    public BattleNpc() {
        super();
    }

    public BattleNpc(NpcType<?> type, String name, ResourceLocation skin, boolean slim, Item item, int HP, int MP, MoveType... moves){
        super(type, name,slim,skin);
        this.MAXHP = HP;
        this.HP = MAXHP;
        this.MAXMP = MP;
        this.MP = MAXMP;
        this.item = item;
        this.moveSet = new ArrayList<MoveType>(Arrays.asList(moves));
    }

    public void addMove(MoveType move){
        moveSet.add(move);
    }



    @Override
    public CompoundNBT save(){
        CompoundNBT nbt = super.save();
        nbt.putInt("maxHp", this.MAXHP);
        nbt.putInt("hp", this.HP);
        nbt.putInt("maxMp", this.MAXMP);
        nbt.putInt("mp",this.MP);
        nbt.putInt("atk", this.ATK);
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
        super.load(nbt);
        this.MAXMP = nbt.getInt("maxHp");
        this.HP = nbt.getInt("hp");
        this.MAXMP = nbt.getInt("maxMp");
        this.MP = nbt.getInt("mp");
        this.ATK = nbt.getInt("atk");
        this.DEF = nbt.getInt("def");
        this.SPD = nbt.getInt("spd");
        CompoundNBT moves = nbt.getCompound("moveset");
        ArrayList<MoveType> movelist = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if(moves.contains(""+i)){
                movelist.add(Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(mcrpg.MODID,moves.getString(""+i))));
            }
        }
        this.moveSet = movelist;
    }


    public Unit unit(){
        return null;
    }

}
