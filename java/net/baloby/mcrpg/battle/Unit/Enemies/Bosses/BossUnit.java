package net.baloby.mcrpg.battle.Unit.Enemies.Bosses;

import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;

public abstract class BossUnit extends Unit {

    public BossUnit(EntityType type){
        super(type);
        this.setStation();
    }

    @Override
    public ArrayList<EntityType> getSummonable() {
        return new ArrayList<>();
    }
}
