package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class RanaUnit extends Unit {
    public RanaUnit(){
        super(ModEntities.RANA.get());
        this.MP = 20;
        this.favoriteFood = "cookies and milk";
        addAffinity(Element.ICE, Affinity.STRONG);
        addAffinity(Element.FIRE, Affinity.WEAK);
        removeMoves();
        entity.setItemInHand(Hand.MAIN_HAND, new ItemStack(Items.TRIDENT));
        addMove(Moves.FLORA);
        addMove(Moves.HEAL);
    }
}
