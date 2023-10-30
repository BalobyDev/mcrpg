package net.baloby.mcrpg.battle.Unit.Enemies.Bosses;

import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.setup.ModEntities;

public class InfernoUnit extends BossUnit {
    public InfernoUnit() {
        super(ModEntities.INFERNO.get());
        addAffinity(Element.FIRE, Affinity.DRAIN);
        addAffinity(Element.ICE, Affinity.WEAK);
        addMove(Moves.IGNEON.get().create());
        this.XP = 200;
        this.BASE_STR = 50;
        this.STR = BASE_STR;
        this.addMoveReward(Moves.IGNEON.get());
        this.MAX_HP = 150;
        this.HP = MAX_HP;
    }
}
