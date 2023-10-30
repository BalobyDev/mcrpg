package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Unit.Enemies.Bosses.BossUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.BattleGui;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.baloby.mcrpg.client.gui.WinScreen;
import net.baloby.mcrpg.data.INpcData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBattleEntity;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBossEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.network.PacketBattle;
import net.baloby.mcrpg.network.RpgNetwork;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Clock;
import net.baloby.mcrpg.tools.Teleport;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Dimension;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.apache.logging.log4j.Level;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Battle {
    public static Battle instance;
    public static boolean isActive = false;
    public boolean isOver = false;
    public CustomCamera camera;
    public int turn;
    public ServerWorld arena;
    public EntityType enemyType;
    public ServerPlayerEntity sp;
    public Map<Entity,Unit> entityMap = new HashMap<Entity,Unit>();
    public ArrayList<Unit> turnOrder = new ArrayList<Unit>();
    public ArrayList<MoveType> moveReward = new ArrayList<>();
    public HashMap<Item, Integer> items = new HashMap<Item, Integer>();
    public BattleGui gui;
    public HashMap<String, Battle> battleMap = new HashMap<>();
    public ServerWorld returnWorld;
    public OverlayGui overlay;
    public BlockPos returnPoint;
    public Unit activeUnit;
    public INpcData profile;
    public PlayerParty playerParty;
    public Party enemyParty;
    public Clock clock;
    public int ticks;
    public int xp;
    public AnimationSequence sequence;
    protected Theme theme;

    public void conclude(){
        if(arena.tickingEntities) return;
        sp.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().getSendBack().putString("world","");
        enemyParty.members.forEach(Unit::remove);
        playerParty.members.forEach(Unit::remove);
        sendBack();
    }

    public void win(){
        conclude();
        sp.giveExperiencePoints(getXp());
        for (Map.Entry<Item, Integer> set : items.entrySet()){
            ItemEntity itemEntity = new ItemEntity(returnWorld,returnPoint.getX(),returnPoint.getY(),returnPoint.getZ(),
                    new ItemStack(set.getKey(),set.getValue()));
            this.returnWorld.addFreshEntity(itemEntity);
        }
        for(MoveType move: moveReward){
            sp.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().addAvailableMove(move);
        }
    }

    public void addMove(MoveType moveType){
        this.moveReward.add(moveType);
    }

    public void lose(){
        conclude();
    }

    public static void init(ServerPlayerEntity entity, Entity target, ServerWorld destination, BlockPos pos) {
        ServerWorld world = entity.getLevel();
        Battle battle;
        Teleport.teleport(entity,destination,new BlockPos(1.5,1.0,1.5));
        entity.teleportTo(-0.5,102,1.5);

        if(target instanceof ICustomBossEntity ){
            battle = new BossBattle(destination,target,entity);
        }
        else if(target instanceof EnderDragonPartEntity){
            battle = new BossBattle(destination,new EnderDragonEntity(EntityType.ENDER_DRAGON,destination),entity);
        }
        else{
            battle = new Battle(destination, (MobEntity) target,entity);
        }
        battle.returnWorld = world;
        battle.returnPoint = pos;
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("world",world.dimension().location().toString());
        nbt.putIntArray("pos",new int[]{pos.getX(),pos.getY(),pos.getZ()});
        entity.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().setSendBack(nbt);
    }

    public Battle(){

    }
    public Battle(ServerWorld arena, MobEntity entity, ServerPlayerEntity sp){
        this.sp = sp;
        instance = this;
        this.arena = arena;
        this.clock = new Clock();
        this.playerParty = new PlayerParty(this,sp);
        playerParty.addPlayer(sp);
        this.playerParty.addMembers();
        this.turn = 0;
        this.enemyType = entity.getType();
        this.enemyParty = new Party(this,entity);
        enemyParty.addMembers();
        this.setActiveUnit(playerParty.members.get(0));
        RpgNetwork.sendToClient(new PacketBattle(),sp);
        this.camera = new CustomCamera(arena);
        this.overlay = new OverlayGui();
        this.xp = getXp();
        showStuff(false);
        this.theme = new Theme(this);
        playTheme(1);
        setTime();
        isActive = true;
        for(Entity e : arena.getAllEntities()){
            if(!entityMap.containsKey(e)){
                e.remove();
            }
        }
        for(Unit unit : enemyParty.members){
            for(ItemStack item: unit.items){
                addItem(item.getItem(),item.getCount());
            }

        }

        entity.remove();
    }

    public void setActiveUnit(Unit unit){
        this.activeUnit = unit;
        if(enemyParty.active.size() == 0) {
            WinScreen.open();
            isOver = true;
            return;
        }
        if (!unit.isDead){
            mcrpg.LOGGER.log(Level.DEBUG, "It is " + unit.name + "'s turn!");
            unit.takeTurn();
        }
        else {nextTurn();}
        if (unit.playerControl&&camera!=null) camera.setBehind();
    }

    public void nextTurn(){
        playerParty.members.forEach(Unit::removeIndicator);
        setActiveUnit(turnOrder.get((turnOrder.indexOf(activeUnit)+1)%turnOrder.size()));
        enemyParty.members.forEach(Unit::removeIndicator);
    }
    public void sendBack(){
        this.playerParty.conclusion();
        showStuff(true);
        this.overlay = null;
        isActive = false;
        instance = null;
        arena.getServer().submitAsync(()->Teleport.teleport(sp,returnWorld,returnPoint));

        camera.camera.remove();
    }

    protected void setTime(){
        arena.getServer().submitAsync(()->{arena.setDayTime(returnWorld.getDayTime());});
    }

    public int getXp(){
        int a = 0;
        for (int i = 0;i<enemyParty.members.size();i++){
            a += enemyParty.members.get(i).XP;
        }
        return a;
    }

    public void addItem(Item item,int count){
        if(item == Items.AIR) return;
        if(this.items.containsKey(item)){
            int value = items.get(item)+count;
            this.items.replace(item,value);
            return;
        }
        this.items.put(item,count);
    }

    public void addItem(Item item){
        this.addItem(item,1);
    }

    public void showStuff(boolean bool){
        ForgeIngameGui.renderExperiance = bool;
        ForgeIngameGui.renderCrosshairs = bool;
        ForgeIngameGui.renderHealth = bool;
    }

    protected void playTheme(float volume){
        Minecraft.getInstance().submitAsync(()->{
            Minecraft.getInstance().getSoundManager().play(theme);
        });
    }
    public static Battle getInstance(){return instance;}

    public void tick(){
        playerParty.members.forEach(Unit::tick);
        enemyParty.members.forEach(Unit::tick);
        if(sequence!=null)sequence.tick();
        ticks++;
    }
}


