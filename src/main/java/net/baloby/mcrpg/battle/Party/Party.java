package net.baloby.mcrpg.battle.Party;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.NpcUnit;
import net.baloby.mcrpg.battle.Unit.PlayerUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;
import java.util.Collections;
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
    protected double line = 9.5;
    public ArrayList<Vector3d> availableStations = new ArrayList<Vector3d>();

    public Party(Battle battle){
        this.battle = battle;
    }

    public Party(Battle battle, EntityType type){
        this.battle = battle;
        //this.size = Dice.roll(3)+2;
        this.size = 4;

        if(battle.playerParty.members.size()<4){
            this.size = battle.playerParty.members.size()+1;
        }
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
            }
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
        //I have an idea what if we didn't listCreate new stations, that could better help us organize them left to right
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

    public Unit selectRandom(){
        return members.get(Dice.roll(members.size()));
    }
}
