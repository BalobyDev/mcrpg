package net.baloby.mcrpg.data.characters.shop;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class Shop {

    private ArrayList<ShopItem> shopItems = new ArrayList<>();

    public Shop(){

    }

    public void addItem(ShopItem item){
        this.shopItems.add(item);
    }

    public Shop addItem(Item item, int cost, int quantity, ShopItem.CostType type){
        this.shopItems.add(new ShopItem(item,cost,quantity,type));
        return this;
    }


    public void buy(ShopItem item){

    }
}
