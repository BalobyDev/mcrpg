package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.ChickenAilment;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class ChickenMove extends AilmentInflictingMove{

    public ChickenMove() {
        super(new StringTextComponent("ChickenMove"), new StringTextComponent("Turns one target into a chicken!"),33);
        this.cost = 4;
    }

    @Override
    protected Ailment createAilment() {
        return new ChickenAilment();
    }

    @Override
    public void execute(Unit user, Unit target){
        super.execute(user, target);
        Animation.sound(SoundEvents.CHICKEN_HURT);
        target.setUpEntity(target.spawn(EntityType.CHICKEN));
    }
}
