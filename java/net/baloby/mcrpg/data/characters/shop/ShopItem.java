package net.baloby.mcrpg.data.characters.shop;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ShopItem extends ShopElement{

    private Item item;

    public ShopItem(Item item, int cost, int quantity, CostType type) {
        super(cost, quantity, type);
        this.item = item;
        this.icon = new ResourceLocation(item.getRegistryName().getNamespace(), "textures/item/"+item.getRegistryName().getPath()+".png");
        this.name = new TranslationTextComponent("item."+item.getRegistryName().getNamespace()+"."+item.getRegistryName().getPath());
        this.description = item.getDescription();

    }

    @Override
    public void purchase(ServerPlayerEntity player){
        if(player.inventory.getFreeSlot()>-1)return;
        super.purchase(player);
        player.addItem(new ItemStack(item));
    }
}
