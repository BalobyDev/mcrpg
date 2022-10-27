package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class WitherSkeletonUnit extends Unit {
    public WitherSkeletonUnit(){
        super(EntityType.WITHER_SKELETON);
        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.WITHER, Affinity.STRONG);
        addSummonable(EntityType.BLAZE);
        addSummonable(EntityType.SKELETON);
        addSummonable(EntityType.WITHER_SKELETON);

    }
}
