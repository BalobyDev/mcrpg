package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.setup.ModEntities;

public class IceologerUnit extends Unit {
    public IceologerUnit(){
        super(ModEntities.ICEOLOGER.get());
        this.addMove(Moves.AQUA.get().create());
        addAffinity(Element.FIRE, Affinity.WEAK);
        addAffinity(Element.ICE, Affinity.BLOCK);
    }
}
