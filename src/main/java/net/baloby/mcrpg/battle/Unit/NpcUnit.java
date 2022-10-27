package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.misc.IdleGoal;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class NpcUnit extends Unit{


    public Npc character;

    public NpcUnit(BattleNpc character){
        this.setParty();
        this.character = character;
        this.station = new BlockPos(1.5,1,party instanceof PlayerParty ? 1.5 : 9.5);
        this.playerControl = (party instanceof PlayerParty);
        this.setStation();
        this.MAX_MP = character.MP;
        this.MP = character.MP;
        this.entity = this.spawn(character);
        if(entity instanceof HumanoidEntity) ((HumanoidEntity) entity).setCharacter(character);
        this.battle.entityMap.put(this.entity,this);
        this.name = character.name;
        this.MAX_HP = this.entity.getMaxHealth();
        this.HP = MAX_HP;
        this.DEF += entity.getArmorValue();
        this.update();
        this.stack = entity.getMainHandItem();
        this.XP = 10;
        addAffinities();
        addMove(new BasicAttack());
    }

    public MobEntity spawn(BattleNpc character){
        HumanoidEntity entity = (HumanoidEntity) character.spawn(Battle.arena, new Vector3d(station.getX(),station.getY(), station.getZ()));
        entity.goalSelector.addGoal(0, new IdleGoal());
        int rot = playerControl ? 0: 180;
        entity.setYBodyRot(rot);
        entity.setYHeadRot(rot);
        entity.setCustomNameVisible(true);
        return entity;
    }
}
