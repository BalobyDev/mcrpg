package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.ChickenAilment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.text.StringTextComponent;

public class Chicken extends AilmentInflictingMove{

    public Chicken() {
        super(new StringTextComponent("Chicken"), new StringTextComponent("Turns one target into a chicken!"),33);
        this.cost = 4;
    }

    @Override
    protected Ailment createAilment() {
        return new ChickenAilment();
    }

    @Override
    public void execute(Unit user, Unit target){
        super.execute(user, target);
        target.spawn(EntityType.CHICKEN);
    }
}
