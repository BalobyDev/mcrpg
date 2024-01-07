package net.baloby.mcrpg.data;


import net.baloby.mcrpg.setup.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class CorpseContainer extends Container {
    
    private final IItemHandlerModifiable handler;


    public CorpseContainer(int id, PlayerInventory playerInv) {
        this(id,playerInv, new ItemStackHandler(36));
    }

    public CorpseContainer(int id, PlayerInventory playerInv, IItemHandlerModifiable handler) {
        super(ModContainers.CORPSE_CONTAINER.get(), id);
        this.handler = handler;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new SlotItemHandler(handler,j+i*9,8+j*18, 18 + i *18 ));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInv,j+i*9+9,8+j*18,103+i*18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInv,i, 8 + i * 18, 161));
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_82846_2_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_82846_2_ < 36) {
                if (!this.moveItemStackTo(itemstack1, 36, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public IItemHandlerModifiable getContainer(){return handler;}

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return true;
    }
}
