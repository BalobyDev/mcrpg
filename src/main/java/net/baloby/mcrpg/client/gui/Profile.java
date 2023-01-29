package net.baloby.mcrpg.client.gui;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.ICharProfile;
import net.baloby.mcrpg.data.IPlayerProfile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;

public class Profile {

    public String name;
    public int hp;
    public int maxHp;
    public int mp;
    public int maxMp;

    public Profile(String name, int hp, int maxHp, int mp, int maxMp){
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.mp = mp;
        this.maxMp = maxMp;


    }

    public static Profile fromPlayer(ServerPlayerEntity player){
        IPlayerProfile playerProfile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        Profile prof = new Profile(player.getDisplayName().getString(), (int) player.getHealth(), playerProfile.getMaxHp(), playerProfile.getMp(), playerProfile.getMaxMp());
        return prof;
    }

    public static Profile fromNpc(NpcType type, ServerWorld world){
        BattleNpc npc = (BattleNpc) type.create();
        Profile prof = new Profile(npc.getName(), npc.HP, npc.MAXHP,npc.MP,npc.MAXMP);
        return prof;
    }
}
