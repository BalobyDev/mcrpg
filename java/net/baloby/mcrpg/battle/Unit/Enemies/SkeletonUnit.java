package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Attack;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class SkeletonUnit extends Unit {
    public SkeletonUnit(){
        super(EntityType.SKELETON);
        this.BASE_STR = 40;
        this.SPD = 10;
        this.XP = 10;
        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.WITHER, Affinity.STRONG);
        addSummonable(EntityType.ZOMBIE,2);
        addSummonable(EntityType.SPIDER,2);
    }
}
