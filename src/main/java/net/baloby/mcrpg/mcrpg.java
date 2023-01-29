package net.baloby.mcrpg;

import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.client.ClientEventBusSubscriber;
import net.baloby.mcrpg.data.*;
import net.baloby.mcrpg.data.UniqueFeatures.IUniqueFeatures;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeatures;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesStorage;
import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.entities.custom.partyMembers.SteveEntity;
import net.baloby.mcrpg.network.RpgNetwork;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.world.ArenaBiomeProvider;
import net.baloby.mcrpg.world.ArenaChunkGenerator;
import net.baloby.mcrpg.world.structures.ConfigStructures;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.server.ServerWorld;
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
            GlobalEntityTypeAttributes.put(ModEntities.ICEOLOGER.get(), RanaEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.RANA.get(), RanaEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.STEVE.get(), SteveEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.HUMANOID.get(), RanaEntity.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(ModEntities.HUMANOID_SLIM.get(), RanaEntity.setCustomAttributes().build());
//            GlobalEntityTypeAttributes.put(ModEntities.NEW_ENDERMAN.get(), EndermanEntity.setCustomAttributes().build());
            UnitType.registerUnits();
            ModStructures.setupStructures();
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(mcrpg.MODID, "arenagen"),
                    ArenaChunkGenerator.CODEC);
            Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(mcrpg.MODID, "biomes"),
                    ArenaBiomeProvider.CODEC);
            CapabilityManager.INSTANCE.register(IPlayerProfile.class, new PlayerStorage(), PlayerProfile::new);
            CapabilityManager.INSTANCE.register(ICharProfile.class, new NpcStorage(), CharProfile::new);
            CapabilityManager.INSTANCE.register(IUniqueFeatures.class, new UniqueFeaturesStorage(), UniqueFeatures::new);


        });
    }
}

