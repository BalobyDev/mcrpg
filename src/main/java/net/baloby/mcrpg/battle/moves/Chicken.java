package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;

public class Chicken extends Move{

    public Chicken() {
        super("Chicken", "Turns the target into a chicken!");
        this.cost = 5;
    }

    @Override
    public void execute(Unit user, Unit target){
        super.execute(user, target);
        target.spawn(EntityType.CHICKEN);
    }
}
