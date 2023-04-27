package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class StrayUnit extends Unit {
    public StrayUnit(){
        super(EntityType.STRAY);

        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.ICE, Affinity.STRONG);

    }
}
