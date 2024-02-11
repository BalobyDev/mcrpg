package net.baloby.mcrpg.setup.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class YoYoItem extends TieredItem implements IWeightedItem {


    public YoYoItem(IItemTier p_i48459_1_, Properties p_i48459_2_) {
        super(p_i48459_1_, p_i48459_2_);
    }


    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        return super.use(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @Override
    public int getWeight() {
        return 1;
    }
}
