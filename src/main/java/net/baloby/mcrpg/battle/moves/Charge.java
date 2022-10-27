package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;

public class Charge extends Move{

    public Charge(String name) {super(name);}

    public void execute(Unit user, Unit target){
        super.execute(user,target);
    }
}
