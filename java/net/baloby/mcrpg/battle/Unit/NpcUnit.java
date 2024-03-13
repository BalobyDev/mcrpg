package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.misc.IdleGoal;
import net.minecraft.entity.MobEntity;
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
        this.MAX_MP = character.MIND*2;
        this.MP = character.MP;
        if(entity instanceof HumanoidEntity) ((HumanoidEntity) entity).setCharacter(character);
        this.battle.entityMap.put(this.entity,this);
        this.name = character.getName().getString();
        this.MAX_HP = character.VIGOR*2;
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
        if(!playerControl){
        this.addMove(new BasicAttack());}
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

    public void addAffinities(){
        super.addAffinities();
        for (Element type : Element.values()){
            if(character.affinities.get(type)!=null) {
                affinities.put(type, character.affinities.get(type));
            }
        }
    }

    public void remove(){
        super.remove();
        if(playerControl) {
            character.HP = (int) HP;
            character.MP = (int) MP;
            character.setDirty(true);
        }
    }
}
