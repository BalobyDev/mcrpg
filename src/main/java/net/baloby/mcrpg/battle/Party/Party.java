package net.baloby.mcrpg.battle.Party;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;

public class Party {
    public Battle battle;
    public int size = 2;
    public int maxSize = 4;
    public ArrayList<Unit> members = new ArrayList<Unit>();
    public ArrayList<Unit> active = new ArrayList<Unit>();
    public Boolean wipedOut;
    public EntityType type;
    public Unit unit;
    protected double line = 9.5;
    public ArrayList<BlockPos> availableStations = new ArrayList<BlockPos>();

    public Party(Battle battle){
        this.battle = battle;
    }

    public Party(Battle battle, EntityType type){
        this.battle = battle;
        //this.size = Dice.roll(3)+2;
        this.size = 4;
        this.configStations();
        this.type = type;

    }

    public void setActive(Unit unit){

    }

    public void addMember(Unit unit){
        if(members.size() < size){
            this.members.add(unit);
            this.active.add(unit);
            unit.party = this;
            battle.turnOrder.add(unit);
        }
    }

    public void addMember(EntityType type){
        Unit unit = Unit.getByType(type);
        addMember(unit);
    }

    public void addMembers(){
        addMember(type);
        for (int i = 0; i<size-1;i++){
            addMember(Unit.getByType(members.get(0).summonable.get(Dice.roll(members.get(0).summonable.size()))));
        }

    }

    public Unit random(){
        Unit rand = members.get(Dice.roll(members.size()));
        if(rand.isDead){
            return random();
        }
        return rand;
    }


    public void configStations(){
        //I have an idea what if we didn't create new stations, that could better help us organize them left to right
        switch (size){
            case 1:
                availableStations.add(blockPos(1.5));
                break;
            case 2:
                availableStations.add(blockPos(3.5));
                availableStations.add(blockPos(-0.5));
                break;
            case 3:
                availableStations.add(blockPos(5.5));
                availableStations.add(blockPos(1.5));
                availableStations.add(blockPos(-2.5));
                break;
            case 4:
                availableStations.add(blockPos(7.5));
                availableStations.add(blockPos(3.5));
                availableStations.add(blockPos(-0.5));
                availableStations.add(blockPos(-4.5));
                break;
        }
    }

    protected BlockPos blockPos(double x){
        return new BlockPos(x,1,line);

    }
    public void removeStation(BlockPos pos){
        availableStations.remove(pos);
    }


    public BlockPos randomStation(){
        int r = Dice.roll(this.availableStations.size());
        return availableStations.get(r);
    }

    public Unit selectRandom(){
        return members.get(Dice.roll(members.size()));
    }
}
