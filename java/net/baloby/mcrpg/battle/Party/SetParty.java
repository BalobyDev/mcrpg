package net.baloby.mcrpg.battle.Party;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;

import java.util.ArrayList;

public class SetParty extends Party{

    private ArrayList<EntityType> membersList = new ArrayList<>();

    public SetParty(Battle battle, EntityType type, EntityType... units) {
        super(battle);
        this.membersList.add(type);
        for(EntityType unit : units){
            membersList.add(unit);
        }
        this.size = membersList.size();
        this.configStations();
    }

    @Override
    public void addMembers(){
        for(EntityType member : membersList){
            this.addMember(member);
        }
        for(Unit unit : getRowOrder()){
            battle.turnOrder.add(unit);
        }
    }
}
