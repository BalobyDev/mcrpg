package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.data.CharProfile;
import net.baloby.mcrpg.data.ICharProfile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.tools.Teleport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class PlayerUnit extends Unit{
    public ServerPlayerEntity player;
    public ICharProfile profile;

    public PlayerUnit(ServerPlayerEntity player) {
        this.player = player;
        this.party = battle.playerParty;
        this.name = "Camryn";
        this.profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        this.setStation();
        this.playerControl = true;
        Teleport.teleport(player,station.getX(),1,station.getZ()+0.5);
        movesSet.add(Moves.HEAL);
        movesSet.add(Moves.VOLTAGE);
        HP = player.getHealth();
        MP = profile.getMp();
        MAX_MP = profile.getMaxMp();
        ATK = 30;
        setEntity(player);
        this.DEF += entity.getArmorValue();
        this.stack = entity.getMainHandItem();
        this.battle.entityMap.put(entity,this);
        addAffinities();
        player.setCustomNameVisible(false);
        update();
    }


    @Override
    public void setEntity(LivingEntity e) {
        super.setEntity(e);
    }

    @Override
    public void takeDamage(float dmg){
        super.takeDamage(dmg);
        entity.heal(1);
        entity.hurt(DamageSource.OUT_OF_WORLD,1);
    }

    @Override
    public void swing(){
        SAnimateHandPacket sanimatehandpacket = new SAnimateHandPacket(entity, Hand.MAIN_HAND == Hand.MAIN_HAND ? 0 : 3);
        arena.getChunkSource().broadcastAndSend(entity, sanimatehandpacket);
    }

    public void action(Move move, Unit target){
        super.action(move,target);
        entity.swing(entity.swingingArm);
    }

    public void setPlayer(ServerPlayerEntity p){this.player = p;}

    public void conclusion(){
        profile.setMp((int)MP);
        if(HP>0)player.setHealth(HP);
        else player.kill();
    }

    public void win(){
        battle.camera.setPos(1,3,5);
    }

    public void lose(){
        battle.lose();
        player.kill();
    }
}
