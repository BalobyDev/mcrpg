package net.baloby.mcrpg.setup;

import com.mojang.serialization.Lifecycle;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.battle.Unit.Units;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.world.structures.ModStructures;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Registration {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, mcrpg.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, mcrpg.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, mcrpg.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, mcrpg.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, mcrpg.MODID);
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, mcrpg.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, mcrpg.MODID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, mcrpg.MODID);

    public static final DeferredRegister<MoveType<?>> MOVES = DeferredRegister.create(MoveType.MOVE_GENERIC, mcrpg.MODID);
    public static final DeferredRegister<NpcType<?>> NPC_TYPES = DeferredRegister.create(NpcType.NPC_GENERIC, mcrpg.MODID);
    public static final DeferredRegister<UnitType<?>> UNIT_TYPES = DeferredRegister.create(UnitType.UNIT_GENERIC, mcrpg.MODID);
    public static final Supplier<IForgeRegistry<MoveType<?>>> MOVE_REGISTRY = MOVES.makeRegistry("movetypes",RegistryBuilder::new);
    public static final Supplier<IForgeRegistry<NpcType<?>>> NPC_REGISTRY = NPC_TYPES.makeRegistry("npctypes",RegistryBuilder::new);
    public static final Supplier<IForgeRegistry<UnitType<?>>> UNIT_REGISTRY = UNIT_TYPES.makeRegistry("unittypes", RegistryBuilder::new);


        public static void register(){
            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
            BIOMES.register(eventBus);
            ITEMS.register(eventBus);
            SOUNDS.register(eventBus);
            ENTITY_TYPES.register(eventBus);
            STRUCTURES.register(eventBus);
            PARTICLES.register(eventBus);
            MOVES.register(eventBus);
            NPC_TYPES.register(eventBus);
            UNIT_TYPES.register(eventBus);
            CONTAINERS.register(eventBus);


            ModBiomes.register();
            ModItems.register();
            ModSounds.register();
            ModEntities.register();
            ModStructures.register();
            ModParticles.register();
            ModContainers.register();
            Moves.register();
            Npcs.register();
            Units.register();
        }
    }
