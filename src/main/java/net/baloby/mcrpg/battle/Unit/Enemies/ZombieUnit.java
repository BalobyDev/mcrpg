package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Move;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.Hand;

public class ZombieUnit extends Unit {
    public ZombieUnit() {
        super(EntityType.ZOMBIE);
       this.SPD = 15;
        this.XP = 8;
        addAffinity(Element.FIRE, Affinity.WEAK);
        addSummonable(EntityType.SPIDER);
    }

    public void action(Move move, Unit target){
        super.action(move,target);
    }

}
