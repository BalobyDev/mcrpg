package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.minecraft.entity.EntityType;

public class BlazeUnit extends Unit {
    public BlazeUnit(){
        super(EntityType.BLAZE);
        this.MP = 20;
        addAffinity(Element.FIRE, Affinity.DRAIN);
        addAffinity(Element.ICE, Affinity.WEAK);
        addMove(Moves.IGNI.get().create());
        addSummonable(EntityType.SKELETON,2);
        addSummonable(EntityType.WITHER_SKELETON,2);
        setNoAi(true);
    }
}
