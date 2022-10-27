package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;

public class SpiderUnit extends Unit {
    public SpiderUnit(){
        super(EntityType.SPIDER);
        this.SPD = 50;
        this.XP = 8;
        addAffinity(Element.ELECTRIC, Affinity.WEAK);
        removeMoves();
        addMove(new BasicAttack("Bite"));
        removeJockey();
        addSummonable(EntityType.SPIDER);
        addSummonable(EntityType.ZOMBIE);
    }

    public void removeJockey(){
        entity.getPassengers().forEach(Entity::remove);
    }
}
