package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.NpcContainer;
import net.baloby.mcrpg.client.gui.profile.NpcInventory;
import net.baloby.mcrpg.data.INpcData;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.HashMap;

public class BattleNpc extends Npc implements INamedContainerProvider {

    public int LVL = 1, VIGOR = 10, HP, MIND = 10, MP, STR = 10, MAG = 10, DEF = 10, ENDURANCE = 10, EXP = 0;
    public HashMap<Integer,MoveType> moveSet = new HashMap<>();
    public INpcData profile;

    @Override
    public ITextComponent getDisplayName() {
        return this.name;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {

        return new NpcContainer(id,playerInventory, new NpcInventory(this));
    }

    public enum WeaponType{SWORD, BOW, SPEAR, AXE, GREATSWORD, YOYO}
    public WeaponType weaponType;


    public BattleNpc() {
        super();
    }

    public BattleNpc(NpcType<?> type, ITextComponent name, ResourceLocation skin, EntityType entityType, Item item, int vigor, int mind, MoveType... moves){
        super(type, name,entityType,skin);
        this.VIGOR = vigor;
        this.HP = VIGOR*2;
        this.MIND = mind;
        this.MP = MIND*2;
        this.item = new ItemStack(item);
        this.moveSet = new HashMap<>();
        this.weaponType = WeaponType.SWORD;
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
        if(MP> MIND){
            this.MP = MIND;
        }
        if(MP<0){
            this.MP = 0;
        }
    }

    public void giveHp(int num){
        this.HP += num;
        if(HP> VIGOR){
            this.HP = VIGOR;
        }
        if(HP<0){
            this.HP = 0;
        }
    }



    @Override
    public CompoundNBT save(){
        CompoundNBT nbt = super.save();
        nbt.putInt("lvl", this.LVL);
        nbt.putInt("exp",this.EXP);
        nbt.putInt("vigor", this.VIGOR);
        nbt.putInt("hp", this.HP);
        nbt.putInt("mind", this.MIND);
        nbt.putInt("mp",this.MP);
        nbt.putInt("str", this.STR);
        nbt.putInt("mag", this.MAG);
        nbt.putInt("def", this.DEF);
        nbt.putInt("endurance", this.ENDURANCE);
        CompoundNBT moves = new CompoundNBT();
        for (int i = 0; i < moveSet.size(); i++) {
            if(moveSet.get(i)!=null) {
                moves.putString("" + i, moveSet.get(i).getRegistryName().toString());
            }
        }
        nbt.put("moveset",moves);
        return nbt;
    }


    @Override
    public void load(CompoundNBT nbt){
        if(nbt==null||!nbt.contains("moveset"))return;
        super.load(nbt);
        this.LVL = nbt.getInt("lvl");
        this.EXP = nbt.getInt("exp");
        this.MIND = nbt.getInt("vigor");
        this.HP = nbt.getInt("hp");
        this.MIND = nbt.getInt("mind");
        this.MP = nbt.getInt("mp");
        this.STR = nbt.getInt("str");
        this.MAG = nbt.getInt("mag");
        this.DEF = nbt.getInt("def");
        this.ENDURANCE = nbt.getInt("endurance");
        CompoundNBT moves = nbt.getCompound("moveset");
        HashMap<Integer,MoveType> movelist = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            if(moves.contains(""+i)){
                movelist.put(i,Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(moves.getString(""+i))));
            }
        }
        this.moveSet = movelist;
    }


    public Unit unit(){
        return null;
    }

}
