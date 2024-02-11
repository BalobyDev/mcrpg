package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.ShortNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Random;

public class BasicAttack extends AttackMove {

    public ItemStack stack;

    public BasicAttack(ITextComponent name){
        super();
        this.name = name;
    }

    public BasicAttack(){
        super(5.0f,Element.PHYSICAL,new StringTextComponent("Attack"),new StringTextComponent("Deals damage to one enemy using equipped weapon"));
    }

    public void execute(Unit user, Unit target){
        Random random = new Random();
        Item held = user.getHeldItem().getItem();
        if(held instanceof SwordItem){this.dmg = ((SwordItem) held).getDamage()+ random.nextInt(3);}
        else if(held instanceof AxeItem){this.dmg = ((AxeItem) held).getAttackDamage()-3;}
        else if (held instanceof BowItem){this.dmg= Dice.roll(4)+5;}
        else if (held instanceof TridentItem){this.dmg =9;}
        else {this.dmg = 2f+user.STR/10;}
        boolean crit = false;
        if(random.nextInt(20)==0){
            this.dmg*=1.5;
            crit = true;
        }
        ListNBT enchants = user.getHeldItem().getEnchantmentTags();
        for(INBT enchant : enchants){
            assert enchant instanceof CompoundNBT;
            if(((CompoundNBT) enchant).getString("id").equals("minecraft:sharpness")){
                assert ((CompoundNBT) enchant).get("lvl") instanceof ShortNBT;
                dmg+= ((ShortNBT) ((CompoundNBT) enchant).get("lvl")).getAsInt();
            }
        }
        super.execute(user,target,crit);
    }
}
