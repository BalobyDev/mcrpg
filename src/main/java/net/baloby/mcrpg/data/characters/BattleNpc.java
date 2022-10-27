package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.data.ICharProfile;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class BattleNpc extends Npc {

    public int MAXHP = 20,HP, MAXMP =20, MP, ATK = 20, DEF = 20, SPD = 20;
    public ArrayList<Move> moveSet = new ArrayList<Move>();
    public ICharProfile profile;

    public BattleNpc() {
        super();
    }

    public BattleNpc(String name, ResourceLocation skin, boolean slim, Item item, int HP, int MP){
        super(name,skin,slim);
        this.MAXHP = HP;
        this.HP = MAXHP;
        this.MAXMP = MP;
        this.MP = MAXMP;
    }

    public void addMove(Move move){
        moveSet.add(move);
    }

    public Unit unit(){
        return null;
    }

}
