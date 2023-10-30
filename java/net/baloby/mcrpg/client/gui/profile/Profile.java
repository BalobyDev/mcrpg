package net.baloby.mcrpg.client.gui.profile;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public abstract class Profile {

    public String name;
    public int hp;
    public int maxHp;
    public int mp;
    public int maxMp;
    public int STR;
    public int MAG;
    public int DEF;
    public int SPD;
    private ResourceLocation skin;
    protected ServerPlayerEntity player;
    private HashMap<Integer, MoveType> moves;

    public Profile(ServerPlayerEntity player, String name, int hp, int maxHp, int mp, int maxMp, int str, int mag, int def, int spd, ResourceLocation skin, HashMap<Integer,MoveType> moves){
        this.player = player;
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.mp = mp;
        this.maxMp = maxMp;
        this.STR = str;
        this.MAG = mag;
        this.DEF = def;
        this.SPD = spd;
        this.skin = skin;
        this.moves = moves;
    }

    public Entity makeEntity(){
        return null;
    }

    public static PlayerProfile fromPlayer(ServerPlayerEntity player){
        IPlayerData playerData = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();

        PlayerProfile prof = new PlayerProfile(player,player.getDisplayName().getString(), (int) player.getHealth(), playerData.getMaxHp(), playerData.getMp(),
                playerData.getMaxMp(),
                playerData.getStr(), playerData.getMag(),
                playerData.getDef(), playerData.getSpd(), new ResourceLocation(mcrpg.MODID,"textures/entity/camryn.png"),playerData.moveList());
        return prof;
    }

    public static NpcProfile fromNpc(NpcType type, ServerPlayerEntity player){
        BattleNpc npc = (BattleNpc) type.create();
        CompoundNBT nbt = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts().getCompound(type.getRegistryName().getPath());
        npc.load(nbt);
        return new NpcProfile(player,npc);
    }

    public void addHp(int num){
        this.hp += num;
        if(hp>maxHp){
            this.hp = maxHp;
        }
        if(hp<0){
            this.hp = 0;
        }
    }
    public void addMp(int num){
        this.mp += num;
        if(mp>maxMp){
            this.mp = maxMp;
        }
        if(mp<0){
            this.mp = 0;
        }
    }

    public ServerPlayerEntity getPlayer(){
        return player;
    }

    public HashMap<Integer, MoveType> getMoves() {
        return moves;
    }

    public ResourceLocation getSkin(){
        return skin;
    }

    public void save(){}

}
