package net.baloby.mcrpg.data;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = mcrpg.MODID)
public class CapabilityHandler {

    public static final ResourceLocation CHAR_PROF = new ResourceLocation(mcrpg.MODID,"char_prof");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        //Entity entity = event.getObject();
        if (!(event.getObject() instanceof PlayerEntity)) return;
        event.addCapability(CHAR_PROF, new PlayerCapabilityProvider());

    }

    @SubscribeEvent
    public static void attachCapabilitys(AttachCapabilitiesEvent<World> event) {
        //Entity entity = event.getObject();
        if (!(event.getObject() instanceof ServerWorld)) return;
        event.addCapability(CHAR_PROF, new CharacterCapabilityProvider());

        //event.addCapability(CHAR_PROF, new CharacterCapabilityProvider());



    }

    @SubscribeEvent
    public static void playerEvent(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            event.getPlayer().getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve();


        }
    }


    @SubscribeEvent
    public static void sleepEvent(SleepFinishedTimeEvent event){

    }
}
