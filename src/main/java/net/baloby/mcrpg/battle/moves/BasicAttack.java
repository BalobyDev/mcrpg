package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.item.*;
import net.minecraft.util.Hand;

public class BasicAttack extends Attack{

    public ItemStack stack;

    public BasicAttack(String name){
        super();
        this.name = name;
    }

    public BasicAttack(){
        super(5.0f,Element.PHYSICAL,"Attack");
    }


    public void execute(Unit user, Unit target){
        Item held = user.getHeldItem().getItem();
        if(held instanceof SwordItem){this.dmg = ((SwordItem) held).getDamage()+1;}
        else if(held instanceof AxeItem){this.dmg = ((AxeItem) held).getAttackDamage()-3;}
        else if (held instanceof BowItem){this.dmg= Dice.roll(4)+5;}
        else if (held instanceof TridentItem){this.dmg =9;}
        else {this.dmg = user.ATK/10;}
        super.execute(user,target);
    }
}
