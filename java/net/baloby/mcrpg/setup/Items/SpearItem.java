package net.baloby.mcrpg.setup.Items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.TieredItem;

public class SpearItem extends TieredItem implements IWeightedItem{


    public SpearItem(IItemTier p_i48459_1_, Properties p_i48459_2_) {
        super(p_i48459_1_, p_i48459_2_);
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
