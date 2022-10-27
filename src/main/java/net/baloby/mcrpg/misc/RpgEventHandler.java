package net.baloby.mcrpg.misc;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.client.gui.DialougeGui;
import net.baloby.mcrpg.data.ICharProfile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModDimensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = mcrpg.MODID, bus = Bus.FORGE)
public class RpgEventHandler {

    @SubscribeEvent
    public static void onAttackEvent(AttackEntityEvent event) {
        Entity target = event.getTarget();
        PlayerEntity player = event.getPlayer();
        if (UnitType.unitMap.containsKey(target.getEntity().getType()) && player instanceof ServerPlayerEntity && !((ServerPlayerEntity) player).getLevel().dimension().equals(ModDimensions.ARENA)) {
            ServerWorld world = player.getServer().getLevel(ModDimensions.ARENA);
            Battle.init((ServerPlayerEntity) player, (MobEntity) target, world, player.blockPosition());
        }
    }

    @SubscribeEvent
    public static void onSpawnEvent(EntityJoinWorldEvent event) {
        Entity e = event.getEntity();
        World w = event.getWorld();
        if (w.dimension().equals(ModDimensions.ARENA) && e instanceof SlimeEntity) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event){
        if(!(Battle.isActive)){
            return;
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void sleepEvent(PlayerSleepInBedEvent event){
        if(event.getPlayer().level.getDayTime()<13000) {
            return;
        }
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            ICharProfile profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            profile.setMp(20);
        }
    }

    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.EntityInteract event){
        Entity e = event.getTarget();
        if(e instanceof RanaEntity||e instanceof HumanoidEntity){
            DialougeGui.open(e);
        }
    }

    @SubscribeEvent
    public static void soundEvent(SoundEvent.SoundSourceEvent event){
        if(event.getSound().equals(SoundEvents.PORTAL_TRAVEL)) {
            event.setCanceled(true);
        }
    }
}






