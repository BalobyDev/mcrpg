package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.minecraft.entity.EntityType;

public class EndermanUnit extends Unit {
    public EndermanUnit(){
        super(EntityType.ENDERMAN);
        this.SPD = 60;
        addMove(Moves.ENDRA);
        addAffinity(Element.ICE, Affinity.WEAK);
        addAffinity(Element.ENDER, Affinity.BLOCK);
        addSummonable(EntityType.SKELETON);
        addSummonable(EntityType.SPIDER);
        addSummonable(EntityType.ZOMBIE);
        setNoAi(true);


    }
}
