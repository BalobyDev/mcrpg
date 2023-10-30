package net.baloby.mcrpg.battle.Party;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.NpcUnit;
import net.baloby.mcrpg.battle.Unit.PlayerUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBattleEntity;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {
    public Battle battle;
    public int size = 2;
    public int maxSize = 4;
    public ArrayList<Unit> members = new ArrayList<Unit>();
    public ArrayList<Unit> active = new ArrayList<Unit>();
    public Boolean wipedOut;
    public EntityType type;
    public Unit unit;
    public Entity entity;
    protected double line = 9.5;
    public ArrayList<Vector3d> availableStations = new ArrayList<Vector3d>();

    public Party(Battle battle){
        this.battle = battle;
    }

    public Party(Battle battle, Entity entity){
        Party playerParty = battle.playerParty;
        this.battle = battle;
        this.size = battle.playerParty.size+Dice.roll(3)-1;
        this.entity = entity;

        if(size>4){
            size = 4;
        }
        if(playerParty.size<3&&size<playerParty.size){
            size=playerParty.size;
        }
        this.configStations();
        this.type = entity.getType();

    }

    public void setActive(Unit unit){

    }

    public void addMember(Unit unit){
        if(members.size() < size){
            this.members.add(unit);
            this.active.add(unit);
            unit.party = this;

            //Just added this last time let's test it in the win screen!
            if(!(unit instanceof NpcUnit || unit instanceof PlayerUnit)){
                //entity origin parameters
                LootTable table = unit.arena.getServer().getLootTables().get(unit.entity.getLootTable());
                LootContext.Builder contextBuilder = new LootContext.Builder(unit.arena);
                contextBuilder.withParameter(LootParameters.THIS_ENTITY, unit.entity);
                contextBuilder.withParameter(LootParameters.ORIGIN, unit.station);
                contextBuilder.withParameter(LootParameters.DAMAGE_SOURCE, DamageSource.MAGIC);

                List<ItemStack> list = table.getRandomItems(contextBuilder.create(LootParameterSets.ENTITY));

                for (ItemStack stack:list) {
                    battle.addItem(stack.getItem());
                }
                for(MoveType moveType: unit.getMoveReward()){
                    battle.moveReward.add(moveType);
                }
            }
        }
    }

    public void addMember(EntityType type){
        Unit unit = Unit.getByEntity(type);
        addMember(unit);
    }

    public void addMembers(){

        addMember(type);
        for (int i = 0; i<size-1;i++){
            if(members.size() >= size||members.get(0).getSummonable().size()<1)return;
            addMember(members.get(0).getSummonable().get(Dice.roll(members.get(0).summonable.size())));
        }
        for(Unit unit : getRowOrder()){
            battle.turnOrder.add(unit);
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
        //I have an idea what if we didn't listCreate new stations, that could better help us organize them left to right
        if(size%2==0){
            availableStations.add(blockPos(3.5));
            availableStations.add(blockPos(-0.5));
            availableStations.add(blockPos(7.5));
            availableStations.add(blockPos(-4.5));
        }
        else {
            availableStations.add(blockPos(1.5));
            availableStations.add(blockPos(5.5));
            availableStations.add(blockPos(-2.5));
        }
    }

    protected Vector3d blockPos(double x){
        return new Vector3d(x,102,line);

    }
    public void removeStation(Vector3d pos){
        availableStations.remove(pos);
    }


    public Vector3d randomStation(){
        int r = Dice.roll(this.availableStations.size());
        return availableStations.get(r);
    }

    public ArrayList<Unit> getRowOrder(){
        ArrayList<Unit> order = new ArrayList<>();
        ArrayList<Unit> unAssigned = (ArrayList<Unit>) active.clone();
        for (int i = 0; i < active.size(); i++) {
            Unit unit = unAssigned.get(0);
            for (int j = 0; j < unAssigned.size(); j++) {
                if(unit.station.x()<unAssigned.get(j).station.x()){
                    unit = unAssigned.get(j);
                }
            }
            order.add(unit);
            unAssigned.remove(unit);
        }
        return order;
    }


    public Unit selectRandom(){
        return members.get(Dice.roll(members.size()));
    }
}
