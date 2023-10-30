package net.baloby.mcrpg.client.gui.profile;

import com.google.common.collect.ImmutableList;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;

public class NpcInventory implements IInventory {

    public final NonNullList<ItemStack> armor = NonNullList.withSize(4, ItemStack.EMPTY);
    public final NonNullList<ItemStack> mainHand = NonNullList.withSize(1, ItemStack.EMPTY);
    public final NonNullList<ItemStack> offhand = NonNullList.withSize(1, ItemStack.EMPTY);
    private final List<NonNullList<ItemStack>> compartments = ImmutableList.of(this.armor, this.mainHand, this.offhand);


    public NpcInventory(BattleNpc npc){
        this.mainHand.set(0,new ItemStack(npc.item));
    }


    @Override
    public int getContainerSize() {
        return 6;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int i, int i1) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        if(i==5);

    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
