package net.baloby.mcrpg.data.characters.shop;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public enum CostType{
    EXP(new StringTextComponent("Exp"),new ResourceLocation(mcrpg.MODID,"textures/entity/experience_orb.png")){
        @Override
        public int getHeld(ServerPlayerEntity player){
            return player.experienceLevel;
        }
    },
    EMERALDS(new StringTextComponent("Emeralds"),new ResourceLocation("textures/item/emerald.png"))
            {
                @Override
                public int getHeld(ServerPlayerEntity player){
                    int count = 0;
                    for (int i = 0; i < player.inventory.getContainerSize(); i++) {
                        ItemStack stack = player.inventory.getItem(i);
                        if(stack.getItem().equals(Items.EMERALD)){
                            count+=stack.getCount();
                        }
                    }
                    return count;
                }
            },
    GOLD(new StringTextComponent("Gold"), new ResourceLocation("textures/item/gold_ingot.png"))
            {
                @Override
                public int getHeld(ServerPlayerEntity player){
                    int count = 0;
                    for (int i = 0; i < player.inventory.getContainerSize(); i++) {
                        ItemStack stack = player.inventory.getItem(i);
                        if(stack.getItem().equals(Items.GOLD_INGOT)){
                            count+=stack.getCount();
                        }
                    }
                    return count;
                }
            };

    ITextComponent name;
    ResourceLocation icon;

    CostType(ITextComponent name, ResourceLocation icon){
        this.name = name;
        this.icon = icon;
    }

    public ITextComponent getName() {
        return name;
    }

    public ResourceLocation getIcon(){
        return icon;
    }

    public abstract int getHeld(ServerPlayerEntity player);
}

