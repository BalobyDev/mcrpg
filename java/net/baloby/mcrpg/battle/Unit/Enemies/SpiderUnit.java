package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.system.CallbackI;

public class SpiderUnit extends Unit {
    public SpiderUnit(){
        super(EntityType.SPIDER);
        this.SPD = 50;
        this.XP = 8;
        addAffinity(Element.ELECTRIC, Affinity.WEAK);
        removeMoves();
        addMove(new BasicAttack(new StringTextComponent("Bite")));
        removeJockey();
        addSummonable(EntityType.ZOMBIE,2);
    }

    public void removeJockey(){
        entity.getPassengers().forEach(Entity::remove);
    }
}
