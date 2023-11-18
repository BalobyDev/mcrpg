package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.setup.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;
import java.util.Map;

public class Gaurd extends Move{

    public static Map<Item,Integer> map = new HashMap<>();

    public static void addItem(Item item, int num){
        map.put(item, num);
    }

    public Gaurd() {
        super(new StringTextComponent("Gaurd"));
        addItem(Items.SHIELD,20);
        addItem(ModItems.IRON_SHIELD.get(),50);
    }

    public void execute(Unit user, Unit target){
        target.gaurding = true;
        if(map.containsKey(target.entity.getItemInHand(Hand.OFF_HAND).getItem())){
        }
        target.battle.camera.setFacing(target);
    }
}
