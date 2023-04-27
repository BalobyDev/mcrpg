package net.baloby.mcrpg.data.characters.shop;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class Shop{

    private ArrayList<ShopElement> shopItems = new ArrayList<>();

    public Shop(ShopElement... items){
        for(ShopElement element : items){
            addItem(element);
        }
    }

    public void addItem(ShopElement item){
        this.shopItems.add(item);
    }

    public void addItem(Item item, int cost, int quantity, CostType type, ServerPlayerEntity player){
        this.shopItems.add(new ShopElement(cost,quantity,type));
    }

    public void buy(ShopElement item,ServerPlayerEntity player){
        if(item.isAffordable(player)){
            item.purchase(player);
        }
    }

    public ArrayList<ShopElement> getShopItems(){return shopItems;}
}
