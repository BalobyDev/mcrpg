package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;

public class IronGolemUnit extends Unit {
    public IronGolemUnit(){
        super(EntityType.IRON_GOLEM);
        this.BASE_STR = 80;
        this.XP = 50;
    }
}
