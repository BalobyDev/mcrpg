package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, mcrpg.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, mcrpg.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, mcrpg.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, mcrpg.MODID);
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, mcrpg.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, mcrpg.MODID);

        public static void register(){
            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
            ITEMS.register(eventBus);
            SOUNDS.register(eventBus);
            ENTITY_TYPES.register(eventBus);
            STRUCTURES.register(eventBus);
            PARTICLES.register(eventBus);

            ModItems.register();
            ModSounds.register();
            ModEntities.register();
            ModStructures.register();
        }
    }
