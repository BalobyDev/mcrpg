package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.entity.EntityType;

public class VilerWitchUnit extends Unit {
    public VilerWitchUnit(){
        super(ModEntities.VILER_WITCH.get());

        addSummonable(EntityType.WITCH,2);


    }
}
