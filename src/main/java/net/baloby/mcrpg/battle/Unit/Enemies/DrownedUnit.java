package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.minecraft.entity.EntityType;

public class DrownedUnit extends Unit {

    public DrownedUnit(){
        super(EntityType.DROWNED);
        addAffinity(Element.ELECTRIC, Affinity.WEAK);
        addAffinity(Element.ICE, Affinity.STRONG);
        addMove(Moves.AQUA);

    }
}
