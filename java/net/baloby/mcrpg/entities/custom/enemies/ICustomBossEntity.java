package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.Bosses.BossUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.world.server.ServerWorld;

public interface ICustomBossEntity extends ICustomBattleEntity{

    Unit unit();

    ServerWorld getArena();

    BossUnit boss();
}
