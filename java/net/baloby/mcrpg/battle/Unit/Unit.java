package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.StatMod.Stat;
import net.baloby.mcrpg.battle.StatMod.StatMod;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.DamageAilment;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.client.gui.BattleGui;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.baloby.mcrpg.client.gui.indicator.*;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBattleEntity;
import net.baloby.mcrpg.misc.IdleGoal;
import net.baloby.mcrpg.tools.Animation;
import net.baloby.mcrpg.tools.Dice;
import net.baloby.mcrpg.tools.Clock;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Unit {

    public String name;
    public Battle battle = Battle.getInstance();
    public EntityType type;
    public LivingEntity entity;
    public ServerWorld arena = battle.arena;
    public Party party;
    public float LVL, MP, MAX_HP = 20, MAX_MP,BASE_MAG=10, MAG =BASE_MAG, BASE_STR = 10, STR=BASE_STR,  BASE_DEF=10, DEF=10, BASE_SPD=10, SPD=BASE_SPD,POISE;
    public float HP = MAX_HP;
    public boolean playerControl = false;
    public boolean gaurding = false;
    public ArrayList<Move> moveSet = new ArrayList<Move>();
    public ArrayList<EntityType> summonable = new ArrayList<EntityType>();
    public ItemStack stack;
    public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    public HashMap<Stat, StatMod> statMods = new HashMap<>();
    public Vector3d station = new Vector3d(1.5,1,9.5);
    public boolean isDead = false;
    public HashMap<Element, Affinity> affinities = new HashMap<>();
    public Indicator indicator;
    public String favoriteFood = "food";
    public int XP;
    private Ailment ailment;
    private ArrayList<MoveType> moveReward = new ArrayList<>();

    public Unit(){}

    public Unit(EntityType type){
        this.setParty();
        this.station = new Vector3d(1.5,72,party instanceof PlayerParty ? 1.5 : 9.5);
        this.playerControl = (party instanceof PlayerParty);
        this.addSummonable(type,3);
        this.setStation();
        this.setUpEntity(spawn(type));
        this.battle.entityMap.put(this.entity,this);
        this.name = entity.getDisplayName().getString();
        this.type = type;
        this.MAX_HP = this.entity.getMaxHealth();
        this.HP = MAX_HP;
        this.DEF += this.getArmorValue();
        this.DEF += this.getArmorValue();
        this.update();
        this.stack = entity.getMainHandItem();
        this.XP = 10;
        this.setNoAi(true);
        addAffinities();
        addStats();
        addMove(new BasicAttack());
    }

    public void setUpEntity(MobEntity entity){
        arena.getServer().submitAsync(()-> arena.addFreshEntity(entity));
        entity.goalSelector.addGoal(0, new IdleGoal());
        entity.moveTo(station);
        int rot = playerControl ? 0: 180;
        entity.setYBodyRot(rot);
        entity.setYHeadRot(rot);
        this.entity = entity;
    }


    public MobEntity spawn(EntityType type) {
        if(this.entity!=null&&!(this.entity instanceof PlayerEntity)){entity.remove();}
        if(entity instanceof PlayerEntity) entity.setInvisible(!entity.isInvisible());
        MobEntity entity = (MobEntity)type.spawn(arena, null, null, new BlockPos(station), SpawnReason.SPAWN_EGG,true,true);
        entity.goalSelector.addGoal(0, new IdleGoal());
        return entity;
    }

    public void setStation(){
        Vector3d pos = party.availableStations.get(0);
        station = pos;
        party.removeStation(pos);
    }
    
    public int getArmorValue(){
        int value = 0;
        for (ItemStack armor : entity.getArmorSlots()) {
            if(!armor.isEmpty() && armor.getItem() instanceof ArmorItem){
                value+=((ArmorItem) armor.getItem()).getDefense();
            }
        }
        return value;
    }
    public void setEntity(LivingEntity e){this.entity = e;}

    public void remove(){
        if(!(entity instanceof PlayerEntity))entity.remove();
    }

    public void action(Move move, Unit target){
        battle.camera.setFacingAngled(this);
        move.execute(this, target);
        int length = 4;
        if(this.ailment!=null){
            if(this.ailment instanceof DamageAilment){
                length = 6;


            }
        }
        Clock.waitFor(length,()-> Battle.getInstance().nextTurn());
        MoveTextGui.open(move);
        walk();
        swing();
    }

    public void updateEquipment(){

    }

    public void addSummonable(EntityType type, int num){
        for (int i = 0; i < num; i++) {
            summonable.add(type);
        }
    }

    public ArrayList<EntityType> getSummonable() {
        return summonable;
    }

    private void removeSummonables(){summonable = new ArrayList<EntityType>();}

    public ItemStack getHeldItem(){
        return entity.getMainHandItem();
    }

    public void takeDamage(float dmg, @Nullable Element element, boolean crit){
        entity.hurt(DamageSource.OUT_OF_WORLD, 0);
        int oldHp = (int) HP;
        HP -= dmg;
        if(gaurding)HP+=dmg/2;
        gaurding = false;

        if(crit){
            updateIndicator(new CritIndicator((int) dmg,oldHp));
        }
        if(element!=null&&this.affinities.get(element).equals(Affinity.WEAK)){
            updateIndicator(new WeakIndicator((int) dmg,oldHp));
        }
        else if(element!=null&&this.affinities.get(element).equals(Affinity.STRONG)){
            updateIndicator(new ResistIndicator((int) dmg,oldHp));
        }
        else {
            updateIndicator(new DmgIndicator((int) dmg,oldHp));
        }



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
        this.updateIndicator(Indicator.healIndicator((int) healed));
        update();
    }

    public void walk(){
        entity.setSpeed(0.1f);
    }

    public void  setParty(){
        this.party = battle.enemyParty != null ? battle.enemyParty : battle.playerParty;
    }

    public void updateIndicator(Indicator indicator){
        this.indicator = indicator;
        indicator.unit = this;
    }

    public void removeIndicator(){
        if(indicator!=null) {
            this.indicator.unit = null;
            this.indicator = null;
        }
    }

    public void miss(){
        Animation.sound(SoundEvents.EGG_THROW);
        updateIndicator(Indicator.missIndicator());
    }

    public void addMove(Move move){
        if (moveSet.size() < 8){
            moveSet.add(move);
        }
    }

    public void getMove(){
        int i = Dice.roll(moveSet.size());
        action(moveSet.get(i), battle.playerParty.random());
    }

    public void removeMoves(){
        moveSet = new ArrayList<Move>();
    }

    public void takeTurn(){
        if (!playerControl){
            getMove();
        }
        else{
            BattleGui.open();
        }

        for(Map.Entry<Stat, StatMod> stat : statMods.entrySet()){
            stat.getValue().takeTurn();
        }

        if(ailment!=null){
            if(ailment.turnsLeft<1){
                this.cureAilment();
            }
        }
    }

    public void setNoAi(boolean bool){
        arena.getServer().submitAsync(()->{if(entity instanceof MobEntity){
            ((MobEntity) entity).setNoAi(bool);
        }});
    }

    public void setStack(ItemStack stack){
        this.stack = stack;
        this.entity.setItemInHand(Hand.MAIN_HAND,stack);
    }

    public static Unit getByEntity(EntityType type){
        if (UnitType.unitMap.containsKey(type)){
             return UnitType.unitMap.get(type).create();
        }
        Entity e = type.spawn(Battle.getInstance().arena,null,null,new BlockPos(0,0,0),SpawnReason.SPAWN_EGG,false,false);
            if(e instanceof ICustomBattleEntity){
                e.remove();
                return ((ICustomBattleEntity) e).unit();
            }
            e.remove();
        return new Unit(type);
    }

    public void update(){
        if(this.HP<=this.MAX_HP/4) {
            this.crouch();
        }
        else if(this.ailment==null){
            this.unCrouch();
        }
    }

    public void highlight(){
//        entity.setGlowing(!entity.isGlowing());
        if(this.indicator==null) {
            this.updateIndicator(new Indicator(""));
            this.indicator.showHp = true;
        }
        else {
            this.removeIndicator();
        }
    }

    public void addAffinities(){
        for (Element type : Element.values()){
            affinities.put(type, Affinity.NONE);
        }
    }

    public void addStats(){
        for (Stat stat:Stat.values()){
            statMods.put(stat,new StatMod(stat,0,this));
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
        if(entity.getMainHandItem().getItem().equals(Items.BOW)){}
        entity.swing(Hand.MAIN_HAND);
    }

    public void tick(){
        if(entity instanceof MobEntity){
            ((MobEntity) entity).ambientSoundTime = 0;
        }
        if(indicator!=null){
            indicator.tick();
        }
    }

    public void die(){
        remove();
        Animation.particles(this, ParticleTypes.SMOKE,20,SoundEvents.FIRE_EXTINGUISH);
        this.party.active.remove(this);
        this.isDead = true;
    }

    public Ailment getAilment() {
        return ailment;
    }

    public void setAilment(Ailment ailment) {
        ailment.unit = this;
        this.ailment = ailment;
        this.crouch();
    }

    public void cureAilment(){
        this.ailment = null;
        this.unCrouch();
    }

    public void crouch(){
        this.entity.xRot = 45;
        this.entity.setPose(Pose.CROUCHING);
    }

    public void unCrouch(){
        this.entity.setPose(Pose.STANDING);
    }

    public void revive(){

    }

    public ArrayList<MoveType> getMoveReward() {
        return moveReward;
    }

    public void addMoveReward(MoveType moveReward) {
        this.moveReward.add(moveReward);
    }
}







