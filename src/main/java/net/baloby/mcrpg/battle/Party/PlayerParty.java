package net.baloby.mcrpg.battle.Party;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.*;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.minecraft.entity.player.ServerPlayerEntity;

public class PlayerParty extends Party{

    public PlayerUnit player;


    public PlayerParty(Battle battle, ServerPlayerEntity player) {
        super(battle);
        this.line = 1.5;
        this.size = 3;
        this.configStations();
    }

    public void addPlayer(ServerPlayerEntity player){
        this.player = new PlayerUnit(player);
        addMember(this.player);
    }

    @Override
    public void addMembers(){
        addMember(new NpcUnit((BattleNpc) Npcs.ALEX));
        addMember(new NpcUnit((BattleNpc) Npcs.RANA));

    }

    @Override
    public void addMember(Unit unit){
        super.addMember(unit);
        unit.playerControl = true;
    }

    public void conclusion(){
        player.conclusion();
    }
}
