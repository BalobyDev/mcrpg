package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Heal;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.battle.moves.UseItem;
import net.minecraft.entity.EntityType;

public class WitchUnit extends Unit {
    public WitchUnit(){super(EntityType.WITCH);
        this.MP = 20;
        this.XP = 30;
        this.addMove(Moves.POISON.get().create());


    }
}
