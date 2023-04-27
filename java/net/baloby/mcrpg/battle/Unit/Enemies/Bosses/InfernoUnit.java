package net.baloby.mcrpg.battle.Unit.Enemies.Bosses;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Igneon;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.setup.ModEntities;

public class InfernoUnit extends BossUnit {
    public InfernoUnit() {
        super(ModEntities.INFERNO.get());
        addAffinity(Element.FIRE, Affinity.DRAIN);
        addAffinity(Element.ICE, Affinity.WEAK);
        addMove(Moves.IGNEON.get().create());
        this.MAX_HP = 150;
        this.HP = MAX_HP;
    }
}
