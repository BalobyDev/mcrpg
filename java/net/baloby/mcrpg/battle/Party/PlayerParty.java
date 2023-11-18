package net.baloby.mcrpg.battle.Party;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.*;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class PlayerParty extends Party{

    public PlayerUnit player;


    public PlayerParty(Battle battle, ServerPlayerEntity player) {
        super(battle);
        this.line = 1.5;
        this.size = 1;
        for (int i = 1; i < 4; i++) {
            IPlayerData profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            if(profile.getPartyMembers().contains(""+i)){
                if(profile.getPartyMembers().getString(""+i)!="-"){
                    size++;
                }
            }
        }

        this.configStations();
    }

    public void addPlayer(ServerPlayerEntity player){
        this.player = new PlayerUnit(player);
        addMember(this.player);
    }

    @Override
    public void addMembers(){
        IPlayerData profile = this.player.profile;
        for (int i = 0; i < 3; i++) {
            if(profile.getPartyMembers().contains(""+(i+1))){
                NpcType npcType = Registration.NPC_REGISTRY.get().getValue(new ResourceLocation(profile.getPartyMembers().getString(""+(i+1))));
                addMember(new NpcUnit((BattleNpc) npcType.listCreate()));}
        }
        for(Unit unit : active){
            battle.turnOrder.add(unit);
        }
    }

    @Override
    public void addMember(Unit unit){
        super.addMember(unit);
        unit.playerControl = true;
    }

    public CompoundNBT save(){
        CompoundNBT nbt = new CompoundNBT();
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i) instanceof NpcUnit){
                Npc npc = ((NpcUnit) members.get(i)).character;
                nbt.putString(""+i, npc.getType().getRegistryName().getPath());
            }
        }
        return nbt;
    }

    @Override
    public void configStations(){
        if(size==1){
            availableStations.add(blockPos(1.5));
        }
        else if(size==2) {
            availableStations.add(blockPos(3.5));
            availableStations.add(blockPos(-0.5));
        }
        else if(size==3) {
            availableStations.add(blockPos(5.5));
            availableStations.add(blockPos(1.5));
            availableStations.add(blockPos(-2.5));
        }
        else{
            availableStations.add(blockPos(7.5));
            availableStations.add(blockPos(3.5));
            availableStations.add(blockPos(-0.5));
            availableStations.add(blockPos(-4.5));
        }
    }

    public void conclusion() {
        player.conclusion();
    }
}
