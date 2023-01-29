package net.baloby.mcrpg.misc;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.client.gui.DialougeGui;
import net.baloby.mcrpg.data.*;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.entities.render.NewPlayerRenderer;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModDimensions;

import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Dimension;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.WorldEvent;
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
            ServerWorld world = player.getServer().getLevel(ModDimensions.FORREST_ARENA);
            Biome biome = ((ServerPlayerEntity) player).getLevel().getBiome(player.blockPosition());
            if(player.blockPosition().getY()<50) {
                world = player.getServer().getLevel(ModDimensions.ARENA);
            }
            if(((ServerPlayerEntity) player).getLevel().dimension().equals(Dimension.NETHER)){
                world = player.getServer().getLevel(ModDimensions.NETHER_ARENA);

            }

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
        if(w instanceof ServerWorld && e instanceof ServerPlayerEntity){
//            w.getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
        }
    }

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event){
        if(Battle.isActive){
            event.setCanceled(true);
            return;
        }
        if(event.getItemStack().isEmpty()){
            event.setCanceled(true);
            new NewPlayerRenderer(Minecraft.getInstance().getEntityRenderDispatcher()).renderRightHand(event.getMatrixStack(),event.getBuffers(),event.getLight(),Minecraft.getInstance().player);
        }

    }

    @SubscribeEvent
    public static void sleepEvent(PlayerWakeUpEvent event){
        if(event.getPlayer().level.getDayTime()<13000) {
            return;
        }
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            IPlayerProfile playerProfile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            playerProfile.setMp(20);
            ICharProfile charProfile = player.getLevel().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            for(String str : playerProfile.getPartyMembers().getAllKeys()){
                String name = playerProfile.getPartyMembers().getString(str);

                CompoundNBT nbt = charProfile.getNbts().getCompound(name);
                nbt.put("hp",nbt.get("maxHp"));
                nbt.put("mp",nbt.get("maxMp"));

            }
        }
    }

    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.EntityInteract event){
        Entity e = event.getTarget();
        PlayerEntity p = event.getPlayer();
        if((e instanceof RanaEntity||e instanceof HumanoidEntity)&&p instanceof ServerPlayerEntity){
            DialougeGui.open((ServerPlayerEntity) p,e);
        }
    }


}







