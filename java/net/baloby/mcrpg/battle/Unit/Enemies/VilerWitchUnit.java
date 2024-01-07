package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.entity.EntityType;

public class VilerWitchUnit extends Unit {
    public VilerWitchUnit(){
        super(ModEntities.VILER_WITCH.get());
        addSummonable(EntityType.WITCH,2);
        this.MAX_HP = 30;
        this.HP = MAX_HP;
        this.removeMoves();
        this.addMove(Moves.CHICKEN.get().create());
//        this.addMove(Moves.HASTE.get().create());
    }
}
