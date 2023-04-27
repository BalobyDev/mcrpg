package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.item.Item;
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
    }

    public void execute(Unit user, Unit target){
        target.gaurding = true;
        if(target.entity.getItemInHand(Hand.OFF_HAND).getItem() instanceof ShieldItem){
        }
        target.battle.camera.setFacing(target);
    }
}
