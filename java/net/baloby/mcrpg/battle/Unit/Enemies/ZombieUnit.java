package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.entities.custom.enemies.NewZombieEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.Hand;

public class ZombieUnit extends Unit {
    public ZombieUnit() {
        super(EntityType.ZOMBIE);
        this.SPD = 5;
        this.XP = 8;
        addAffinity(Element.FIRE, Affinity.WEAK);
        addSummonable(EntityType.SPIDER,2);
    }

    public MobEntity spawn(EntityType type){
        ZombieEntity zombie = new NewZombieEntity(arena);
        if(zombie.isBaby()){
            zombie.remove();
            return spawn(type);
        }
        return zombie;
    }

    public void action(Move move, Unit target){
        super.action(move,target);
    }

}
