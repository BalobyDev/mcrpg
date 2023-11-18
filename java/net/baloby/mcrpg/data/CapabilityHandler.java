package net.baloby.mcrpg.data;

import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
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
    public static final ResourceLocation UNIQUE_FEATURES = new ResourceLocation(mcrpg.MODID,"unique_features");

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
        event.addCapability(UNIQUE_FEATURES, new UniqueFeaturesCapabilityProvider());

        Minecraft.getInstance().submitAsync(()->{
            INpcData charProfile = event.getObject().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            for(String str : charProfile.getNbts().getAllKeys()){
                Npc npc = Registration.NPC_REGISTRY.get().getValue(new ResourceLocation(str)).listCreate();
                npc.load((CompoundNBT) charProfile.getNbts().get(str));
           }



        });
        Minecraft.getInstance().submitAsync(()->{

            event.getObject().getCapability(UniqueFeaturesCapabilityProvider.FEATURES_CAP).resolve().get();
        });

        //event.addCapability(CHAR_PROF, new CharacterCapabilityProvider());



    }

    @SubscribeEvent
    public static void playerEvent(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            IPlayerData profile = event.getPlayer().getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
            profile.getMaxMp();


        }
    }


    @SubscribeEvent
    public static void sleepEvent(SleepFinishedTimeEvent event){

    }
}
