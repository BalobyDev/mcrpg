package net.baloby.mcrpg.setup.Items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class GreatSwordItem extends SwordItem implements IWeightedItem {

    private int weight;

    public GreatSwordItem(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    public int getWeight() {
        return 10;
    }
}