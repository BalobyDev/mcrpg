package net.baloby.mcrpg.client.gui;

import com.mojang.datafixers.util.Pair;
import net.baloby.mcrpg.setup.ModContainers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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


    public NpcContainer(int id, PlayerInventory inventory){
        super(ModContainers.NPC_CONTAINER.get(),id);
        this.inventory = inventory;
//        this.addSlot(new CraftingResultSlot(inventory.player, this.craftSlots, this.resultSlots, 0, 154, 28));
//        for(int l = 0; l < 3; ++l) {
//            for(int j1 = 0; j1 < 9; ++j1) {
//                this.addSlot(new Slot(inventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
//            }
//        }
//        for(int i1 = 0; i1 < 9; ++i1) {
//            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
//        }
    }

    public NpcContainer(int id, PlayerInventory inventory, IInventory container) {
        super(ModContainers.NPC_CONTAINER.get(), id);
        this.inventory = inventory;

        checkContainerSize(container,6);
        container.startOpen(inventory.player);

        this.addSlot(new CraftingResultSlot(inventory.player, this.craftSlots, this.resultSlots, 0, 154, 28));

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 2, 98 + j * 18, 18 + i * 18));
            }
        }

        this.addArmor(inventory,39);

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(inventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
            }
        }
        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
        }
        this.addSlot(new Slot(inventory, 40, -10000, -10000));
        this.addSlots(container);
    }

    private void addSlots(IInventory container){
        this.addArmor(container,0);
        this.addSlot(new Slot(container, 4, 77, 62) {
            @OnlyIn(Dist.CLIENT)
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(PlayerContainer.BLOCK_ATLAS, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);
            }
        });
        this.addSlot(new Slot(container, 5, 77, 44));

    }


    private void addArmor(IInventory inventory,int offset){
        for(int k = 0; k < 4; ++k) {
            final EquipmentSlotType equipmentslottype = SLOT_IDS[k];
            this.addSlot(new Slot(inventory, offset-k, 8-offset*50, 8 + k * 18) {
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

    public void removed(PlayerEntity p_75134_1_) {
        super.removed(p_75134_1_);
        this.resultSlots.clearContent();
        if (!p_75134_1_.level.isClientSide) {
            this.clearContainer(p_75134_1_, p_75134_1_.level, this.craftSlots);
        }
    }


    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return true;
    }
}
