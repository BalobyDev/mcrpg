package net.baloby.mcrpg.client.gui.profile;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.INpcData;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.entity.player.ServerPlayerEntity;

public class NpcProfile extends Profile{

    public NpcType type;
    public BattleNpc npc;

    public NpcProfile(ServerPlayerEntity player, BattleNpc npc) {
        super(player,npc.getName(), npc.HP, npc.MAXHP, npc.MP, npc.MAXMP, npc.STR, npc.MAG,npc.DEF,npc.SPD,npc.getSkin(), npc.moveSet);
        this.type = npc.getType();
        this.npc = npc;
    }

    @Override
    public void addHp(int num) {
        super.addHp(num);
        this.npc.giveHp(num);
    }

    @Override
    public void addMp(int num) {
        super.addMp(num);
        this.npc.giveMp(num);
    }

    @Override
    public void save(){

        if(npc!=null) {
            INpcData npcData = this.player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            npcData.saveNpc(this.npc);
            npcData.loadNpcs();
        }

    }

}
