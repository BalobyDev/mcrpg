package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Attack;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class SkeletonUnit extends Unit {
    public SkeletonUnit(){
        super(EntityType.SKELETON);
        this.ATK = 40;
        this.SPD = 15;
        this.XP = 10;
        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.WITHER, Affinity.STRONG);
        addSummonable(EntityType.ZOMBIE);
        addSummonable(EntityType.SPIDER);
    }
}
