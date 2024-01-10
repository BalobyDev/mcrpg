package net.baloby.mcrpg.client.gui.profile;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class PlayerProfile extends Profile {


    public PlayerProfile(ServerPlayerEntity player, String name, int lvl, int hp, int maxHp, int mp, int maxMp, int str, int mag, int def, int spd, ResourceLocation skin, HashMap<Integer,MoveType> moves) {
        super(player,name, lvl, hp, maxHp, mp, maxMp,str,mag,def,spd, skin, moves);
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
        if(playerData.getMp()>playerData.getMind()){
            playerData.setMp(playerData.getMind());
        }
    }

    @Override
    public void save(){
        IPlayerData data = this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        data.setVigor(maxHp);
        data.setMind(maxHp);
        data.setMp(mp);
        data.setMoves(getMoves());
    }
}
