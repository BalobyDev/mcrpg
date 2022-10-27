package net.baloby.mcrpg.battle;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Unit.PlayerUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.BattleGui;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.baloby.mcrpg.client.gui.WinScreen;
import net.baloby.mcrpg.data.ICharProfile;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.network.PacketBattle;
import net.baloby.mcrpg.network.RpgNetwork;
import net.baloby.mcrpg.setup.ModSounds;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.baloby.mcrpg.tools.Clock;
import net.baloby.mcrpg.tools.Teleport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.entity.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
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
    public static ServerWorld arena;
    public EntityType enemyType;
    public ServerPlayerEntity sp;
    public Map<Entity,Unit> entityMap = new HashMap<Entity,Unit>();
    public ArrayList<Unit> turnOrder = new ArrayList<Unit>();
    public BattleGui gui;
    public ServerWorld returnWorld;
    public OverlayGui overlay;
    public BlockPos returnPoint;
    public Unit activeUnit;
    public static final BlockPos station = new BlockPos(1.5,1.0,1.5);
    public ICharProfile profile;
    public PlayerParty playerParty;
    public Party enemyParty;
    public Clock clock;
    public int ticks;
    public int xp;
    public AnimationSequence sequence;
    private Theme theme;
    public void conclude(){
        if(arena.tickingEntities)return;
        enemyParty.members.forEach(Unit::remove);
        playerParty.members.forEach(Unit::remove);
        sendBack();
    }
    public void lose(){
        conclude();

    }
    public static void init(ServerPlayerEntity entity, MobEntity target, ServerWorld destination, BlockPos pos) {
        ServerWorld world = entity.getLevel();
        Teleport.teleport(entity,destination,station);
        entity.teleportTo(-0.5,1,1.5);
        Battle battle = new Battle(destination,target,entity);
        battle.returnWorld = world;
        battle.returnPoint = pos;
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
        this.enemyParty = new Party(this,enemyType);
        enemyParty.addMembers();
        this.setActiveUnit(playerParty.members.get(0));
        RpgNetwork.sendToClient(new PacketBattle(),sp);
        this.camera = new CustomCamera(arena);
        this.overlay = new OverlayGui();
        this.xp = getXp();
        showStuff(false);
        this.theme = new Theme(this);
        playTheme(1);
        isActive = true;
        entity.remove();
    }

    public Unit createUnit(EntityType type){
        Unit unit = Unit.getByType(type);
        turnOrder.add(unit);
        return unit;
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
        Teleport.teleport(sp,returnWorld,returnPoint);
        sp.giveExperiencePoints(getXp());
        camera.camera.remove();
    }

    public int getXp(){
        int a = 0;
        for (int i = 0;i<enemyParty.members.size();i++){
            a += enemyParty.members.get(i).XP;
        }
        return a;
    }

    public void showStuff(boolean bool){
        ForgeIngameGui.renderExperiance = bool;
        ForgeIngameGui.renderCrosshairs = bool;
        ForgeIngameGui.renderHealth = bool;
    }

    private void playTheme(float volume){
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


