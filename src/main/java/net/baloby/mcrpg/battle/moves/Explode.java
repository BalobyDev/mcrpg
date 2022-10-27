package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;

public class Explode extends Attack{

    public Explode() {
        super();
        this.name = "Explode";
        this.type = Element.PHYSICAL;
        this.dmg = 20;
    }

    public void execute(Unit user, Unit target){
        Animation.particles(target, ParticleTypes.EXPLOSION,10, SoundEvents.GENERIC_EXPLODE);
        super.execute(user,target);
    }
}
