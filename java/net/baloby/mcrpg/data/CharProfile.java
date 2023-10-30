package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.Move;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;

public class CharProfile implements ICharProfile {


    private int MAX_HP = 20;
    private int MAX_MP = 20;
    private int MP = MAX_MP;
    private ArrayList<Move> moveset = new ArrayList<Move>();


    @Override
    public void setMp(int mp) {
        this.MP = mp;
        if(MP > MAX_MP){
            MP = MAX_MP;
        }
    }
    @Override
    public int getMp() {
        return MP;
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
    public int getMaxMp() {return MAX_MP;}

    @Override
    public int getMaxHp() {return MAX_HP;}


    public ArrayList<Move> getMoves(){return moveset;}

    public void saveNBTData(CompoundNBT nbt){
        nbt.putInt("mp",MP);
        nbt.putInt("maxmp",MAX_MP);
        nbt.putInt("maxhp",MAX_HP);

    }
    public void loadNBTData(CompoundNBT nbt){
        MP = nbt.getInt("mp");
        MAX_MP = nbt.getInt("maxmp");
        MAX_HP = nbt.getInt("maxhp");
    }
}
