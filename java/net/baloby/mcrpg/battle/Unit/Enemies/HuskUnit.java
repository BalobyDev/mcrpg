package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class HuskUnit extends Unit {
    public HuskUnit(){
        super(EntityType.HUSK);
        addAffinity(Element.FIRE, Affinity.WEAK);

    }
}
