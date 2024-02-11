package net.baloby.mcrpg.client.gui.profile;

import com.google.common.collect.ImmutableList;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class NpcInventory implements IItemHandlerModifiable {

    public final NonNullList<ItemStack> armor = NonNullList.withSize(4, ItemStack.EMPTY);
    public final NonNullList<ItemStack> mainHand = NonNullList.withSize(1, ItemStack.EMPTY);
    public final NonNullList<ItemStack> offhand = NonNullList.withSize(1, ItemStack.EMPTY);
    private final List<NonNullList<ItemStack>> compartments = ImmutableList.of(this.armor, this.mainHand, this.offhand);
    private final ArrayList<ItemStack> stacks = new ArrayList<>();
    private final PlayerEntity player;

    private final BattleNpc npc;


    public NpcInventory(BattleNpc npc, PlayerEntity player){
        this.npc = npc;
        this.player = player;
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
        ItemStack itemStack = stacks.set(slot,stack);
        this.updateNpc();
        return itemStack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0) {
            return ItemStack.EMPTY;
        }

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                this.updateNpc();
                return existing;
            }
            else
            {
                return existing.copy();
            }
        }
        else
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                this.updateNpc();
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    public void updateNpc(){
        if(!(player instanceof ServerPlayerEntity))return;
        this.update();
        this.npc.item = this.mainHand.get(0);
        this.npc.offhandItem = this.offhand.get(0);
        this.npc.headItem = armor.get(0);
        this.npc.chestItem = armor.get(1);
        this.npc.legsItem = armor.get(2);
        this.npc.feetItem = armor.get(3);
        CompoundNBT nbt = ((ServerPlayerEntity) player).getLevel().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts();
        CompoundNBT charNbt = nbt.getCompound(npc.getType().getRegistryName().toString());
        charNbt.put("item",npc.item.serializeNBT());
        charNbt.put("offhand_item",npc.offhandItem.serializeNBT());
        charNbt.put("head_item",npc.headItem.serializeNBT());
        charNbt.put("chest_item",npc.chestItem.serializeNBT());
        charNbt.put("legs_item",npc.legsItem.serializeNBT());
        charNbt.put("feet_item",npc.feetItem.serializeNBT());

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
