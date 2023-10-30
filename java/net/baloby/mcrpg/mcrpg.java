package net.baloby.mcrpg;

import net.baloby.mcrpg.client.ClientEventBusSubscriber;
import net.baloby.mcrpg.data.*;
import net.baloby.mcrpg.data.UniqueFeatures.IUniqueFeatures;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeatures;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesStorage;
import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.network.RpgNetwork;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.world.ArenaBiomeProvider;
import net.baloby.mcrpg.world.ArenaChunkGenerator;
import net.baloby.mcrpg.world.CutsceneChunkGenerator;
import net.baloby.mcrpg.world.RpgNoiseGenerator;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(mcrpg.MODID)
public class mcrpg
{
    public static final String MODID = "mcrpg";
    public static final Logger LOGGER = LogManager.getLogger();

    public mcrpg() {
        Registration.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEventBusSubscriber::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        RpgNetwork.registerMessages();

    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.CULTIST.get(), ModEntities.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.ICEOLOGER.get(), ModEntities.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.INFERNO.get(), ModEntities.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.HUMANOID.get(), ModEntities.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.HUMANOID_SLIM.get(), ModEntities.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.HUMANOID_PIGLIN.get(), ModEntities.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.VILER_WITCH.get(), ModEntities.setCustomAttributes(3,30).build());

            ModStructures.setupStructures();
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(mcrpg.MODID, "arenagen"),
                    ArenaChunkGenerator.CODEC);
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(mcrpg.MODID,"cutscenegen"),
                    CutsceneChunkGenerator.CODEC);
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation("wew"),
                    RpgNoiseGenerator.CODEC);
            Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(mcrpg.MODID, "biomes"),
                    ArenaBiomeProvider.CODEC);
            CapabilityManager.INSTANCE.register(IPlayerData.class, new PlayerStorage(), PlayerData::new);
            CapabilityManager.INSTANCE.register(INpcData.class, new NpcStorage(), NpcData::new);
            CapabilityManager.INSTANCE.register(IUniqueFeatures.class, new UniqueFeaturesStorage(), UniqueFeatures::new);
        });
    }
}

