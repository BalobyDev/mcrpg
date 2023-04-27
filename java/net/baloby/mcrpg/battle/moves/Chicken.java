package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.text.StringTextComponent;

public class Chicken extends Move{

    public Chicken() {
        super(new StringTextComponent("Chicken"), new StringTextComponent("Turns the target into a chicken!"));
        this.cost = 4;
    }

    @Override
    public void execute(Unit user, Unit target){
        super.execute(user, target);
        target.spawn(EntityType.CHICKEN);
    }
}
