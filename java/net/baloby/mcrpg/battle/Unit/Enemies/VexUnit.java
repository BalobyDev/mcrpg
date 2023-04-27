package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;

public class VexUnit extends Unit {

    public VexUnit(){
        super(EntityType.VEX);
        this.MAX_HP = 15;
    }
}
