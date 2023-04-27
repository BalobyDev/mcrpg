package net.baloby.mcrpg.setup.Items;

import net.baloby.mcrpg.battle.Unit.Unit;

public interface BattleItem {

    void use(Unit user, Unit target);
}
