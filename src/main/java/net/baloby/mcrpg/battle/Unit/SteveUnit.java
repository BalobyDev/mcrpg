package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class SteveUnit extends Unit{
    public SteveUnit(){
        super(ModEntities.STEVE.get());
        this.MP = 20;
        this.favoriteFood = "cooked porkchops";
        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.ICE, Affinity.STRONG);
        entity.setItemInHand(Hand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
        removeMoves();
        addMove(Moves.AQUA);
    }
}
