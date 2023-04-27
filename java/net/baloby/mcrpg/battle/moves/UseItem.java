package net.baloby.mcrpg.battle.moves;


import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.PoisonAilment;
import net.baloby.mcrpg.setup.Items.BattleItem;
import net.baloby.mcrpg.setup.Items.MagicMilkshakeItem;
import net.minecraft.item.*;
import net.minecraft.util.text.StringTextComponent;

public class  UseItem extends Move{

    private ItemStack stack;
    private Item item;


    public UseItem(ItemStack stack){
        super(new StringTextComponent(stack.getDisplayName().toString()));
        this.stack = stack;
        this.item = stack.getItem();

    }

    public void execute(Unit user, Unit target){
        stack.setCount(stack.getCount()-1);
        if(item instanceof BattleItem){
            ((BattleItem) item).use(user, target);
        }
        if(item.getFoodProperties() != null){
            new Heal(name,desc,item.getFoodProperties().getNutrition()).execute(user,target);
        }

        if(item.equals(Items.MILK_BUCKET)){
            if(target.getAilment() != null){
                if(target.getAilment() instanceof PoisonAilment){
                    target.setAilment(null);
                }
            }
        }
        if(item instanceof MagicMilkshakeItem){

        }

    }
}
