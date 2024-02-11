package net.baloby.mcrpg.setup.Items;

import net.baloby.mcrpg.data.characters.BattleNpc;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.HashMap;

public class LoadCalculator {

    public static HashMap<Item,Integer> loadMap = calculateMap();


    public static HashMap<Item,Integer> calculateMap(){
        HashMap<Item,Integer> map = new HashMap<>();
        map.put(Items.SHIELD,8);
        map.put(Items.IRON_SWORD,3);
        map.put(Items.IRON_AXE,3);
        map.put(Items.IRON_CHESTPLATE,6);
        map.put(Items.IRON_LEGGINGS,5);
        map.put(Items.IRON_HELMET,3);
        map.put(Items.IRON_BOOTS,3);
        return map;
    }

    public enum Load {LIGHT,MEDIUM,HEAVY,OVERLOADED}

    public static int calculateWeight(BattleNpc npc){
        int weight = 0;
        weight += calculateItemWeight(npc.item.getItem());
        weight += calculateItemWeight(npc.offhandItem.getItem());
        weight += calculateItemWeight(npc.headItem.getItem());
        weight += calculateItemWeight(npc.chestItem.getItem());
        weight += calculateItemWeight(npc.legsItem.getItem());
        weight += calculateItemWeight(npc.feetItem.getItem());
        return weight;

    }

    public static int calculateItemWeight(Item item){
        int weight = 0;
        if(loadMap.containsKey(item)){
            weight=loadMap.get(item);
        }
        if(item instanceof IWeightedItem){
            weight = ((IWeightedItem) item).getWeight();
        }
        return weight;
    }

    public static Load calculateLoad(BattleNpc npc){
        int weight = calculateWeight(npc);
        int maxLoad = npc.ENDURANCE*2;
        if(weight<maxLoad/3) return Load.LIGHT;
        if(weight>maxLoad/3*2) return Load.HEAVY;
        if(weight>maxLoad) return Load.OVERLOADED;
        return Load.MEDIUM;
    }
}
