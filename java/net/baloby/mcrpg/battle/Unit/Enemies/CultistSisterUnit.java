package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CultistSisterUnit extends Unit {

    public CultistSisterUnit(){
        super(ModEntities.CULTIST.get());
        this.setStack(new ItemStack(Items.IRON_AXE));
    }
}
