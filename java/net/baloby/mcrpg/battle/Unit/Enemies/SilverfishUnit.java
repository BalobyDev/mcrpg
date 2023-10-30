package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class SilverfishUnit extends Unit {

    public SilverfishUnit(){
        super(EntityType.SKELETON);
        this.BASE_STR = 8;
        this.SPD = 30;
        this.XP = 10;
        addAffinity(Element.ELECTRIC, Affinity.WEAK);
        addSummonable(EntityType.ZOMBIE,2);
        addSummonable(EntityType.SPIDER,2);
    }
}
