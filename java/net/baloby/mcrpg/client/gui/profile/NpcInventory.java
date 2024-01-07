package net.baloby.mcrpg.client.gui.profile;

import com.google.common.collect.ImmutableList;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class NpcInventory implements IItemHandlerModifiable {

    public final NonNullList<ItemStack> armor = NonNullList.withSize(4, ItemStack.EMPTY);
    public final NonNullList<ItemStack> mainHand = NonNullList.withSize(1, ItemStack.EMPTY);
    public final NonNullList<ItemStack> offhand = NonNullList.withSize(1, ItemStack.EMPTY);
    private final List<NonNullList<ItemStack>> compartments = ImmutableList.of(this.armor, this.mainHand, this.offhand);
    private final ArrayList<ItemStack> stacks = new ArrayList<>();

    private final BattleNpc npc;


    public NpcInventory(BattleNpc npc){
        this.npc = npc;
        this.mainHand.set(0,npc.item);
        this.offhand.set(0,npc.offhandItem);
        this.armor.set(0,npc.headItem);
        this.armor.set(1,npc.chestItem);
        this.armor.set(2,npc.legsItem);
        this.armor.set(3,npc.feetItem);
        for (NonNullList<ItemStack> compartment : compartments){
            for (ItemStack item : compartment){
                stacks.add(item);
            }
        }
    }

    public void update(){
        this.npc.headItem = stacks.get(0);
        this.npc.chestItem = stacks.get(1);
        this.npc.legsItem = stacks.get(2);
        this.npc.feetItem = stacks.get(3);
        this.npc.item = stacks.get(4);
        this.npc.offhandItem = stacks.get(5);
        this.mainHand.set(0,npc.item);
        this.offhand.set(0,npc.offhandItem);
        this.armor.set(0,npc.headItem);
        this.armor.set(1,npc.chestItem);
        this.armor.set(2,npc.legsItem);
        this.armor.set(3,npc.feetItem);
    }

//
//    @Override
//    public int getContainerSize() {
//        return 6;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public ItemStack getItem(int i) {
//        int j = 0;
//        for (NonNullList<ItemStack> compartment : compartments) {
//
//        }
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public ItemStack removeItem(int i, int i1) {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public ItemStack removeItemNoUpdate(int i) {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public void setItem(int i, ItemStack itemStack) {
//        int j = 0;
//        for (NonNullList<ItemStack> compartment : compartments) {
//            for(ItemStack item : compartment){
//                if(j==i)item = itemStack;
//                j++;
//            }
//        }
//    }


    public BattleNpc getNpc() {
        return npc;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        stacks.set(slot,stack);
        this.update();
    }



    @Override
    public int getSlots() {
        return 6;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        stacks.set(slot,stack);
        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack stack = stacks.get(slot).copy();
        return stack;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 6;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }
}
