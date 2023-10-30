package net.baloby.mcrpg.data.characters.shop;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashSet;
import java.util.Set;

public class ShopElement {
    private int cost;
    private int quantity;
    public boolean infinite = true;
    public CostType type;
    protected ResourceLocation id;
    public ResourceLocation icon;
    protected ITextComponent name;
    protected ITextComponent description;


    public ShopElement(int cost, int quantity, CostType type){
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
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

    public ITextComponent getName() {
        return name;
    }

    public ITextComponent getDescription(){
        return description != null ? description : name;
    }

    public ResourceLocation getId() {
        return id;
    }


    public boolean isAffordable(ServerPlayerEntity player){
        if(type.equals(CostType.EMERALDS)){
            Set<Item> itemSet = new HashSet<>();
            itemSet.add(Items.EMERALD);
            return player.inventory.hasAnyOf(itemSet);
        }

        else if (type.equals(CostType.GOLD)) {
            Set<Item> itemSet = new HashSet<>();
            itemSet.add(Items.GOLD_INGOT);
            return player.inventory.hasAnyOf(itemSet);
        }

        else {
            return cost <= player.experienceLevel;
        }


    }


    public void purchase(ServerPlayerEntity player){
        if(type.equals(CostType.EMERALDS)){
        }
        else if(type.equals(CostType.GOLD)){

        }
        else {
            player.setExperienceLevels(player.experienceLevel-cost);
        }
        if(!infinite){
            this.quantity -= 1;
        }
    }
}
