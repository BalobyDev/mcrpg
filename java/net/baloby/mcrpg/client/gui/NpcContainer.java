package net.baloby.mcrpg.client.gui;

import com.mojang.datafixers.util.Pair;
import net.baloby.mcrpg.client.gui.profile.NpcInventory;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.setup.ModContainers;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Optional;

public class NpcContainer extends Container {

    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = new ResourceLocation("item/empty_armor_slot_shield");
    private static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};
    private static final EquipmentSlotType[] SLOT_IDS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
    private final CraftingInventory craftSlots = new CraftingInventory(this, 2, 2);
    private final CraftResultInventory resultSlots = new CraftResultInventory();
    private final PlayerInventory inventory;
    private final int[] data;
    private NpcType npc;

    public NpcContainer(int id, PlayerInventory inventory){
        this(id,inventory, new ItemStackHandler(6));

    }


    public NpcContainer(int id, PlayerInventory inventory, IItemHandlerModifiable handler) {
        super(ModContainers.NPC_CONTAINER.get(), id);
        this.inventory = inventory;
        this.data = new int[1];
        int i = 0;
        if(handler instanceof NpcInventory) this.npc = ((NpcInventory) handler).getNpc().getType();
        for (ResourceLocation location : Registration.NPC_REGISTRY.get().getKeys()) {
            if(this.npc!=null&&location.equals(this.npc.getRegistryName())){
                data[0]=i;
            }
            i++;
        }

        this.addSlots(handler);

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                int slot = j1 + l * 9 + 9;
                this.addSlot(new Slot(inventory, slot, 8 + j1 * 18, 84 + l * 18));
            }
        }


        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
        }
        this.addDataSlot(IntReferenceHolder.shared(this.data,0));
    }


    private void addSlots(IItemHandlerModifiable handler){
        this.addArmor(handler,0);
        this.addSlot(new SlotItemHandler(handler, 4, 77, 44));

        this.addSlot(new SlotItemHandler(handler, 5, 77, 62) {
            @OnlyIn(Dist.CLIENT)
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(PlayerContainer.BLOCK_ATLAS, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);
            }
        });
    }

    @Override
    public void setSynched(PlayerEntity p_75128_1_, boolean p_75128_2_) {
        super.setSynched(p_75128_1_, p_75128_2_);
    }

    private void addArmor(IItemHandlerModifiable handler, int offset){
        for(int k = 0; k < 4; k++) {
            final EquipmentSlotType equipmentslottype = SLOT_IDS[k];
            this.addSlot(new SlotItemHandler(handler, k, 8-offset, 8 + k * 18) {
                public int getMaxStackSize() {
                    return 1;
                }

                public boolean mayPlace(ItemStack p_75214_1_) {
                    return equipmentslottype.equals(p_75214_1_.getEquipmentSlot());
                }

                public boolean mayPickup(PlayerEntity p_82869_1_) {
                    ItemStack itemstack = this.getItem();
                    return !itemstack.isEmpty() && !p_82869_1_.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.mayPickup(p_82869_1_);
                }

                @OnlyIn(Dist.CLIENT)
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(PlayerContainer.BLOCK_ATLAS, NpcContainer.TEXTURE_EMPTY_SLOTS[equipmentslottype.getIndex()]);
                }
            });
        }
    }

    public ItemStack quickMoveStack(PlayerEntity player, int index){
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 36) {
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

    public void removed(PlayerEntity p_75134_1_) {
        super.removed(p_75134_1_);
        this.resultSlots.clearContent();
        if (!p_75134_1_.level.isClientSide) {
            this.clearContainer(p_75134_1_, p_75134_1_.level, this.craftSlots);
        }
    }

    public NpcType getNpc() {
        NpcType npc = null;
        int i = 0;
        for (ResourceLocation location : Registration.NPC_REGISTRY.get().getKeys()){
            if(data[0]==i){npc=Registration.NPC_REGISTRY.get().getValue(location);}
            i++;
        }
        return npc;
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return true;
    }
}
