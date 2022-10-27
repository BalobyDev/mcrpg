package net.baloby.mcrpg.battle.moves;


import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.setup.Items.EtherItem;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class  UseItem extends Move{

    private ItemStack stack;
    private Item item;


    public UseItem(ItemStack stack){
        super(stack.getDisplayName().toString());
        this.stack = stack;
        this.item = stack.getItem();
        this.name = stack.getDisplayName().getString();
    }

    public void execute(Unit user, Unit target){
        stack.setCount(stack.getCount()-1);
        if(item.getFoodProperties() != null){
            new Heal(name,desc,item.getFoodProperties().getNutrition()).execute(user,target);
        }
        if(item instanceof EtherItem){

        }

    }
}
