package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Party.SetParty;
import net.baloby.mcrpg.battle.Unit.Enemies.Bosses.BossUnit;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBossEntity;
import net.baloby.mcrpg.network.PacketBattle;
import net.baloby.mcrpg.network.RpgNetwork;
import net.baloby.mcrpg.tools.Clock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;

public class BossBattle extends Battle{

    public BossUnit boss;

    public BossBattle(ServerWorld arena, MobEntity entity, ServerPlayerEntity sp) {
        this.sp = sp;
        instance = this;
        this.arena = arena;
        this.clock = new Clock();
        this.playerParty = new PlayerParty(this,sp);
        playerParty.addPlayer(sp);
        this.playerParty.addMembers();
        this.turn = 0;
        this.enemyType = entity.getType();
        if(entity instanceof ICustomBossEntity) this.enemyParty = new SetParty(this, enemyType);
        enemyParty.addMembers();
        this.setActiveUnit(playerParty.members.get(0));
        RpgNetwork.sendToClient(new PacketBattle(),sp);
        this.camera = new CustomCamera(arena);
        this.xp = getXp();
        showStuff(false);
        this.theme = new Theme(this);
        playTheme(1);
        setTime();
        isActive = true;
        for(Entity e : arena.getAllEntities()){
            if(!entityMap.containsKey(e)){
                e.remove();
            }
        }

        entity.remove();
    }
}
