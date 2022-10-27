package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.minecraft.entity.EntityType;

public class CaveSpiderUnit extends Unit {
    public CaveSpiderUnit(){
        super(EntityType.CAVE_SPIDER);
        removeMoves();
        addMove(new BasicAttack("Bite"));

    }
}
