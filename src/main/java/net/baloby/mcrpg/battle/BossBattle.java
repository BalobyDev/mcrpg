package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.battle.Unit.BossUnit;
import net.baloby.mcrpg.battle.Unit.EnderDragonUnit;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;

public class BossBattle extends Battle{

    public BossUnit boss;

    public BossBattle(ServerWorld arena, MobEntity entity, ServerPlayerEntity sp) {
        super(arena, entity, sp);

    }
}
