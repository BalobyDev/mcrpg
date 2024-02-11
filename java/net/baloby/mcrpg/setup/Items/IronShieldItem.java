package net.baloby.mcrpg.setup.Items;

import net.minecraft.item.ShieldItem;

public class IronShieldItem extends ShieldItem implements IWeightedItem{

    public IronShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getWeight() {
        return 5;
    }
}
