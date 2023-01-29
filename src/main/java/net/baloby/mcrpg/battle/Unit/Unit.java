package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.client.gui.BattleGui;
import net.baloby.mcrpg.client.gui.Indicator;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.baloby.mcrpg.misc.IdleGoal;
import net.baloby.mcrpg.tools.Animation;
import net.baloby.mcrpg.tools.Dice;
import net.baloby.mcrpg.tools.Clock;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.HashMap;


public class Unit {

    public String name;
    public Battle battle = Battle.getInstance();
    public EntityType type;
    public LivingEntity entity;
    public ServerWorld arena = battle.arena;
    public Party party;
    public float LVL, MP, MAX_HP = 20, MAX_MP, MAG = 0, BASE_ATK = 30, ATK=BASE_ATK,  BASE_DEF=20, DEF=20, BASE_SPD=0, SPD=BASE_SPD;
    public float HP = MAX_HP;
    public boolean playerControl = false;
    public boolean gaurding = false;
    public ArrayList<Move> movesSet = new ArrayList<Move>();
    public ArrayList<EntityType> summonable = new ArrayList<EntityType>();
    public ItemStack stack;
    public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    public Vector3d station = new Vector3d(1.5,1,9.5);
    public boolean isDead = false;
    public HashMap<Element, Affinity> affinities = new HashMap<>();
    public Indicator indicator;
    public String favoriteFood = "your flesh";
    public int XP;

    public Unit(){}

    public Unit(EntityType type){
        this.setParty();
        this.station = new Vector3d(1.5,72,party instanceof PlayerParty ? 1.5 : 9.5);
        this.playerControl = (party instanceof PlayerParty);
        this.addSummonable(type);
        this.setStation();
        this.setUpEntity(spawn(type));
        this.battle.entityMap.put(this.entity,this);
        this.name = entity.getDisplayName().getString();
        this.type = type;
        this.MAX_HP = this.entity.getMaxHealth();
        this.HP = MAX_HP;
        this.DEF += entity.getArmorValue();
        this.update();
        this.stack = entity.getMainHandItem();
        this.XP = 10;
        this.setNoAi(true);
        addAffinities();
        addMove(new BasicAttack());
    }

    public void setUpEntity(MobEntity entity){
        arena.getServer().submitAsync(()-> arena.addFreshEntity(entity));
        entity.goalSelector.addGoal(0, new IdleGoal());
        entity.moveTo(station);
        int rot = playerControl ? 0: 180;
        entity.setYBodyRot(rot);
        entity.setYHeadRot(rot);
        entity.setCustomNameVisible(true);
        this.entity = entity;
    }


    public MobEntity spawn(EntityType type) {
        if(this.entity!=null&&!(this.entity instanceof PlayerEntity)){entity.remove();}
        if(entity instanceof PlayerEntity) entity.setInvisible(!entity.isInvisible());
        MobEntity entity = (MobEntity)type.spawn(arena, null, null, new BlockPos(station), SpawnReason.SPAWN_EGG,true,true);
        return entity;
    }

    public boolean isNoAi(){
        if(entity instanceof MobEntity)
        return ((MobEntity) entity).isNoAi();
        return false;
    }



    public void setStation(){
        Vector3d pos = party.availableStations.get(0);
        station = pos;
        party.removeStation(pos);
    }
    public void setEntity(LivingEntity e){this.entity = e;}

    public void remove(){
        if(!(entity instanceof PlayerEntity))entity.remove();
    }

    public void action(Move move, Unit target){
        battle.camera.setFacingAngled(this);
        move.execute(this, target);

        Clock.waitFor(4,()-> Battle.getInstance().nextTurn());
        MoveTextGui.open(move);
        swing();
    }

    public void addSummonable(EntityType type){
        summonable.add(type);
    }

    private void removeSummonables(){summonable = new ArrayList<EntityType>();}

    public ItemStack getHeldItem(){
        return entity.getMainHandItem();
    }

    public void takeDamage(float dmg){
        entity.hurt(DamageSource.OUT_OF_WORLD, 0);
        HP -= dmg;
        if(gaurding)HP+=dmg/2;
        gaurding = false;
        updateIndicator(Indicator.dmgIndicator((int) dmg));
        update();
        if(HP <= 0){
            HP = 0;
            isDead=true;
            //party.members.remove(this);
            if(!(this instanceof PlayerUnit)){
                die();
            }
            else{
                battle.lose();
            }
        }
    }

    public void heal(float hp){
        float healed = hp;

        if (HP+hp>MAX_HP){
            healed = MAX_HP-HP;
            HP=MAX_HP;
        }
        else {
            HP+=hp;
        }
        this.indicator = Indicator.healIndicator((int) healed);
        update();
    }

    public void setParty(){
        this.party = battle.enemyParty != null ? battle.enemyParty : battle.playerParty;
    }

    public void updateIndicator(Indicator indicator){
        this.indicator = indicator;
    }

    public void removeIndicator(){this.indicator = null;}

    public void miss(){
        Animation.sound(SoundEvents.EGG_THROW);
        updateIndicator(Indicator.missIndicator());
    }

    public void addMove(Move move){
        if (movesSet.size() < 8){
            movesSet.add(move);
        }
    }

    public void getMove(){
        int i = Dice.roll(movesSet.size());
        action(movesSet.get(i), battle.playerParty.random());
    }

    public void removeMoves(){
        movesSet = new ArrayList<Move>();
    }

    public void takeTurn(){
        if (!playerControl){
            getMove();
        }
        else{
            BattleGui.open();
            //battle.camera.setBehind();
        }
    }

    public void setNoAi(boolean bool){
        arena.getServer().submitAsync(()->{if(entity instanceof MobEntity){
            ((MobEntity) entity).setNoAi(bool);
        }});
    }

    public void setStack(ItemStack stack){
        this.entity.setItemInHand(Hand.MAIN_HAND,stack);
    }

    public static Unit getByType(EntityType type){
        if (UnitType.unitMap.containsKey(type)){
            return UnitType.unitMap.get(type).create();
        }
        return new Unit(type);
    }

    public void update(){
        this.entity.setCustomName(new StringTextComponent((int)HP+"/"+(int) MAX_HP));
    }

    public void highlight(){
        entity.setGlowing(!entity.isGlowing());
    }

    public void addAffinities(){
        for (Element type : Element.values()){
            affinities.put(type, Affinity.NONE);
        }
    }
    public void addAffinity(Element elem, Affinity aff){
        affinities.replace(elem,aff);
    }


    public int getXp(){
        return XP;
    }

    public void setStationX(double x){
        this.station = new Vector3d(x,station.y,station.z);
    }


    public void swing(){
        entity.swing(Hand.MAIN_HAND);
    }

    public void tick(){

    }

    public void die(){
        remove();
        Animation.particles(this, ParticleTypes.SMOKE,20,SoundEvents.FIRE_EXTINGUISH);
        this.party.active.remove(this);
        this.isDead = true;
    }

    public void revive(){

    }
}







