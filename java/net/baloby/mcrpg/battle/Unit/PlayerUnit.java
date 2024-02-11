package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.ailments.PoisonAilment;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.setup.Registration;
import net.baloby.mcrpg.tools.Teleport;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;

public class PlayerUnit extends Unit{
    public ServerPlayerEntity player;
    public IPlayerData profile;

    public PlayerUnit(ServerPlayerEntity player) {
        this.player = player;
        this.party = battle.playerParty;
        this.name = "Camren";
        this.profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        this.setStation();
        this.playerControl = true;
        Teleport.teleport(player,station.x,102,station.z);
        this.setMoves();
        MAX_HP = profile.getVigor()*2;
        float oneUnit = profile.getVigor()/10;
        HP = player.getHealth()*oneUnit;
        MP = profile.getMp();
        MAX_MP = profile.getMind()*2;
        this.BASE_STR = 10;
        this.STR = BASE_STR;
        this.BASE_MAG = 10;
        this.MAG = BASE_MAG;
        setEntity(player);
        this.DEF += this.getArmorValue();
        this.stack = entity.getMainHandItem();
        this.battle.entityMap.put(entity,this);
        addAffinities();
        player.setCustomNameVisible(false);
        if(player.getActiveEffects().contains(Effects.POISON)){
            player.removeEffect(Effects.POISON);
            this.setAilment(new PoisonAilment());
        }
        update();

    }


    @Override
    public void setEntity(LivingEntity e) {
        super.setEntity(e);
    }

    @Override
    public void swing(){
        SAnimateHandPacket sanimatehandpacket = new SAnimateHandPacket(entity, Hand.MAIN_HAND == Hand.MAIN_HAND ? 0 : 3);
        arena.getChunkSource().broadcastAndSend(entity, sanimatehandpacket);
    }

    @Override
    public void crouch(){
        Minecraft.getInstance().player.xRot=45;
    }

    @Override
    public void takeDamage(float dmg,Element type, boolean crit){
        super.takeDamage(dmg,type,crit);
        entity.heal(1);
        entity.hurt(DamageSource.OUT_OF_WORLD,1);
    }

    public void action(Move move, Unit target){
        super.action(move,target);
        if(entity.swingingArm!=null) entity.swing(entity.swingingArm);
    }

    public void setPlayer(ServerPlayerEntity p){this.player = p;}

    public void conclusion(){
        profile.setMp((int)MP);
        if(HP>0)player.setHealth(HP);
        else player.kill();
        player.setInvisible(false);
    }

    public void setMoves(){
        CompoundNBT moves = this.profile.getMoves();
        for (int i = 0; i < 8; i++) {
            if(moves.contains(""+i)){
                moveSet.add(Registration.MOVE_REGISTRY.get().getValue(new ResourceLocation(moves.getString(""+i))).create());
            }
        }
    }

    public void win(){
        battle.camera.setPos(1,3,5);
    }

    public void tick(){
        super.tick();
        player.setCustomNameVisible(false);
        if(Minecraft.getInstance().screen==null){
            battle.activeUnit.takeTurn();
        }
    }

    public void lose(){
        battle.lose();
        player.kill();
    }
}
