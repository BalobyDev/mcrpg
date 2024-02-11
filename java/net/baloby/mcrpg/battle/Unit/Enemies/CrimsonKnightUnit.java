package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CrimsonKnightUnit extends Unit {

    public CrimsonKnightUnit(){
        super(ModEntities.KNIGHT.get());
        this.setStack(new ItemStack(Items.IRON_SWORD));
    }
}
