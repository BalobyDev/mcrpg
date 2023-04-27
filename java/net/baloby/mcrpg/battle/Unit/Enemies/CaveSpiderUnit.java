package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.Moves;
import net.minecraft.entity.EntityType;
import net.minecraft.util.text.StringTextComponent;

public class CaveSpiderUnit extends Unit {
    public CaveSpiderUnit(){
        super(EntityType.CAVE_SPIDER);
        removeMoves();
        addMove(new BasicAttack(new StringTextComponent("Bite")));
        addMove(Moves.POISON.get().create());
        addSummonable(EntityType.SPIDER, 2);

    }
}
