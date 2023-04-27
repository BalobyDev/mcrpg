package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.Bosses.BossUnit;
import net.baloby.mcrpg.battle.Unit.Unit;

public interface ICustomBossEntity extends ICustomBattleEntity{

    Unit unit();

    BossUnit boss();
}
