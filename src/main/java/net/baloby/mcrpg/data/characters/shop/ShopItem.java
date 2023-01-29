package net.baloby.mcrpg.data.characters.shop;

import net.minecraft.item.Item;

public class ShopItem {
    private Item item;
    private int cost;
    private int quantity;
    public enum CostType{EXP,EMERALDS,GOLD}
    public CostType type;


    public ShopItem(Item item,int cost, int quantity, CostType type){
        this.item = item;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
    }

    public Item getItem(){
        return item;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity(){
        return quantity;
    }

    public CostType getCostType(){
        return type;
    }
}
