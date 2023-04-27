package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class VindicatorUnit extends Unit {
    public VindicatorUnit(){
        super(EntityType.VINDICATOR);
        this.BASE_STR = 50;
        this.STR = 50;
        addAffinity(Element.PHYSICAL, Affinity.STRONG);
        setStack(new ItemStack(Items.IRON_AXE));
    }
}
