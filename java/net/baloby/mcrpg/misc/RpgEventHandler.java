package net.baloby.mcrpg.misc;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.client.gui.DialougeGui;
import net.baloby.mcrpg.client.gui.NewPlayerInventory;
import net.baloby.mcrpg.client.gui.PronounSelectScreen;
import net.baloby.mcrpg.data.*;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBattleEntity;
import net.baloby.mcrpg.entities.render.NewPlayerRenderer;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.ArrayList;

@EventBusSubscriber(modid = mcrpg.MODID, bus = Bus.FORGE)
public class RpgEventHandler {

    @SubscribeEvent
    public static void onAttackEvent(AttackEntityEvent event) {
        Entity target = event.getTarget();
        PlayerEntity player = event.getPlayer();

        if(player instanceof  ServerPlayerEntity){
            RegistryKey<World> dimension = ((ServerPlayerEntity) player).getLevel().dimension();

        if ((UnitType.unitMap.containsKey(target.getEntity().getType())||target instanceof ICustomBattleEntity)  &&
                !(dimension.equals(ModDimensions.ARENA)||dimension.equals(ModDimensions.FORREST_ARENA)||dimension.equals(ModDimensions.NETHER_ARENA))) {
            ServerWorld world = player.getServer().getLevel(ModDimensions.FORREST_ARENA);
            Biome biome = ((ServerPlayerEntity) player).getLevel().getBiome(player.blockPosition());
            if (player.blockPosition().getY() < 50) {
                world = player.getServer().getLevel(ModDimensions.ARENA);
            }
            if (((ServerPlayerEntity) player).getLevel().dimension().equals(Dimension.NETHER)) {
                world = player.getServer().getLevel(ModDimensions.NETHER_ARENA);
            }

            Battle.init((ServerPlayerEntity) player, (MobEntity) target, world, player.blockPosition());
        }
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
                ServerPlayerEntity player = (ServerPlayerEntity) e;
                IPlayerData data = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
                if(!(data.getPronouns().contains("sub")&&data.getPronouns().contains("obj")&&data.getPronouns().contains("pos"))){
                    PronounSelectScreen.open(player);
                }
            }
        }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        if(event.player instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) event.player;
            IPlayerData data = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            if(!(data.getPronouns().contains("sub"))) {
                PronounSelectScreen.open(player);
            }
            if((!Battle.isActive)&&data.getSendBack().contains("world")){
                CompoundNBT nbt = data.getSendBack();
                for (ServerWorld world : player.getServer().getAllLevels()){
                    if(world.dimension().location().toString().equals(nbt.getString("world"))) {

                        ArrayList<Entity> entities = new ArrayList<>();
                        for(Entity entity : player.getLevel().getAllEntities()){
                            if(entity instanceof PlayerEntity) entities.add(entity);
                        }

                        for (Entity entity : entities) {

                            player.getServer().submitAsync(()->{
                                entity.remove();
                            });

                        }

                        int[] ints = nbt.getIntArray("pos");
                        player.getServer().submitAsync(()->{
                            player.teleportTo(world, ints[0],ints[1],ints[2],0,0);
                            nbt.putString("world","");});

                    }
                }

            }
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
            NewPlayerRenderer renderer = new NewPlayerRenderer(Minecraft.getInstance().getEntityRenderDispatcher());
            renderer.renderRightHand(event.getMatrixStack(),event.getBuffers(),event.getLight(),Minecraft.getInstance().player);
        }

    }

    @SubscribeEvent
    public static void playerDeath(PlayerEvent.Clone event){
        if(event.getPlayer() instanceof ServerPlayerEntity && event.getOriginal()instanceof ServerPlayerEntity){
            ServerPlayerEntity old = (ServerPlayerEntity) event.getOriginal();
            IPlayerData oldPlayerProfile = old.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            IPlayerData newPlayerProfile = event.getPlayer().getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            INpcData charProfile = old.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            if(event.isWasDeath()){
                newPlayerProfile.setQuests(oldPlayerProfile.getQuests());
                newPlayerProfile.setPronouns(oldPlayerProfile.getPronouns());
                newPlayerProfile.setAllMoves(oldPlayerProfile.getAllMoves());
                newPlayerProfile.setMoves(oldPlayerProfile.getMoves());
                int i = 1;
                for(String str : oldPlayerProfile.getPartyMembers().getAllKeys()) {
                    String name = oldPlayerProfile.getPartyMembers().getString(str);
                    CompoundNBT nbt = charProfile.getNbts().getCompound(name);
                    nbt.put("hp", nbt.get("maxHp"));
                    nbt.put("mp", nbt.get("maxMp"));
                    if(oldPlayerProfile.getPartyMembers().contains(""+i)) {
                        newPlayerProfile.addPartyMember(oldPlayerProfile.getPartyMember(i));
                    }
                    i++;
                }
            }
        }




    }

    @SubscribeEvent
    public static void sleepEvent(PlayerWakeUpEvent event){
        if(event.getPlayer().level.getDayTime()<13000) {
            return;
        }
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            IPlayerData playerProfile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            playerProfile.setMp(20);
            INpcData charProfile = player.getLevel().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
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
        if((e instanceof HumanoidEntity)&&p instanceof ServerPlayerEntity){
            ((HumanoidEntity) e).character.talk((ServerPlayerEntity) event.getPlayer());
        }
    }


    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event){
        if(event.getPlayer() instanceof ServerPlayerEntity){

        }

    }

    @SubscribeEvent
    public static void openInventory(GuiScreenEvent event){
        Screen gui = event.getGui();
        if(gui instanceof InventoryScreen) {
            if (!(gui instanceof NewPlayerInventory)){
                NewPlayerInventory.open(Minecraft.getInstance().player);

            }
        }
    }
}







