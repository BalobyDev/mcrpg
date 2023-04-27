package net.baloby.mcrpg.client.gui.profile;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerProfile extends Profile {


    public PlayerProfile(ServerPlayerEntity player, String name, int hp, int maxHp, int mp, int maxMp, ResourceLocation skin, HashMap<Integer,MoveType> moves) {
        super(player,name, hp, maxHp, mp, maxMp, skin, moves);
    }


    @Override
    public void addHp(int num) {
        super.addHp(num);
        player.heal(num);
    }

    @Override
    public void addMp(int num) {
        super.addMp(num);
        IPlayerData playerData = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        playerData.setMp(playerData.getMp()+20);
        if(playerData.getMp()>playerData.getMaxMp()){
            playerData.setMp(playerData.getMaxMp());
        }
    }

    @Override
    public void save(){
        IPlayerData data = this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        data.setMaxHp(maxHp);
        data.setMaxMp(maxHp);
        data.setMp(mp);
        data.setMoves(getMoves());
    }
}
