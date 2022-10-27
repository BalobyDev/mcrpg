package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;

public class Summon extends Move{
    private EntityType type;

    public Summon(EntityType type) {
        super("Summon");
        this.type = type;
    }

    public void execute(Unit user, Unit target){

    }
}
