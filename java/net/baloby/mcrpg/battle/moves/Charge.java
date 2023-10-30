package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

public class Charge extends Move{

    public Charge(ITextComponent name) {super(name);}

    public void execute(Unit user, Unit target){
        super.execute(user,target);
    }
}
