package net.baloby.mcrpg.misc;

import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class  IdleGoal extends Goal {

    public IdleGoal(){
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.TARGET, Flag.JUMP));
    }

    public boolean canUse() {
        return true;
    }
}
