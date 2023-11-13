package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.misc.IdleGoal;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;

public class NpcUnit extends Unit{

    public BattleNpc character;

    public NpcUnit(BattleNpc character){
        this.setParty();
        this.character = character;
        this.station = new Vector3d(1.5,1,party instanceof PlayerParty ? 1.5 : 9.5);
        this.playerControl = (party instanceof PlayerParty);
        this.setStation();
        this.entity = this.spawnAndLoad(character);
        this.MAX_MP = character.MAXMP;
        this.MP = character.MP;
        if(entity instanceof HumanoidEntity) ((HumanoidEntity) entity).setCharacter(character);
        this.battle.entityMap.put(this.entity,this);
        this.name = character.getName();
        this.MAX_HP = character.MAXHP;
        this.HP = character.HP;
        this.DEF += this.getArmorValue();
        this.update();
        this.stack = entity.getMainHandItem();
        this.XP = 10;
        this.addAffinities();
        this.addStats();
        for (int i = 0; i < 8; i++) {
            if(character.moveSet.containsKey(i)){
                this.addMove(character.moveSet.get(i).create());
            }
        }
    }

    @Override
    public void swing(){
        super.swing();
    }

    public MobEntity spawn(BattleNpc character){
        HumanoidEntity entity = (HumanoidEntity) character.spawnLoad(this.arena, new Vector3d(station.x,station.y, station.z));
        entity.goalSelector.addGoal(0, new IdleGoal());
        int rot = playerControl ? 0: 180;
        entity.setYBodyRot(rot);
        entity.setYHeadRot(rot);
        entity.setItemInHand(Hand.MAIN_HAND, character.item);
        return entity;
    }

    public MobEntity spawnAndLoad(BattleNpc character){
        MobEntity entity = spawn(character);
        return entity;
    }

    public void remove(){
        super.remove();
        character.HP = (int)HP;
        character.MP = (int)MP;
        character.setDirty(true);
    }
}
