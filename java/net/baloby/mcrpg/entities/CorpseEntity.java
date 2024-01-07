package net.baloby.mcrpg.entities;

import net.baloby.mcrpg.data.CorpseContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class CorpseEntity extends LivingEntity implements IItemHandlerModifiable, INamedContainerProvider {

    protected ArrayList<ItemStack> stacks = new ArrayList<>();

    public CorpseEntity(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
        for (int i = 0; i < 36; i++) {
            stacks.add(ItemStack.EMPTY);
        }
    }

    public int getContainerSize(){return 36;}


    @Override
    public ActionResultType interact(PlayerEntity player, Hand hand) {
        player.openMenu(this);
        return super.interact(player, hand);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source.equals(DamageSource.DROWN);
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        return super.hurt(p_70097_1_, p_70097_2_);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return new ArrayList<>();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlotType p_184582_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {
    }

    @Override
    public void playerTouch(PlayerEntity p_70100_1_) {
        super.playerTouch(p_70100_1_);
    }

    @Override
    public HandSide getMainArm() {
        return HandSide.RIGHT;
    }

    @Nullable
    @Override
    public CorpseContainer createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new CorpseContainer(p_createMenu_1_,p_createMenu_2_,this);
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        this.stacks.set(slot, stack);
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return stacks.set(slot,stack);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
//        ItemStack old = stacks.get(slot);
//        old.setCount(old.getCount()-amount);
//        ItemStack stack = new ItemStack(old.getItem(),amount);
        if (amount == 0) return ItemStack.EMPTY;

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
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
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    @Override
    public int getSlotLimit(int slot) {
        return 36;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return !stack.isEmpty();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        CompoundNBT inventory = new CompoundNBT();
        for (int i = 0; i < 36; i++) {
            if(!stacks.get(i).equals(ItemStack.EMPTY)) {
                inventory.put(i + "", stacks.get(i).serializeNBT());
            }
        }
        nbt.put("inventory", inventory);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if(!nbt.contains("inventory"))return;
        CompoundNBT inventory = nbt.getCompound("inventory");
        for (int i = 0; i < 36; i++) {
            if(inventory.contains(i + "")){
                stacks.set(i,ItemStack.of(inventory.getCompound(i + "")));
            }
        }
    }
}
