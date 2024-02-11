package net.baloby.mcrpg.setup.Items;

import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SkillCardItem extends Item {
    private MoveType move;
    public SkillCardItem(MoveType move, Properties p_i48487_1_) {
        super(p_i48487_1_);
        this.move = move;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return super.hasContainerItem(stack);
    }
}
