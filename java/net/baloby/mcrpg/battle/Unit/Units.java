package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Unit.Enemies.*;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;

public class Units {

    public static final RegistryObject<UnitType<BlazeUnit>> BLAZE = Registration.UNIT_TYPES.register("blaze",()-> new UnitType<>(EntityType.BLAZE, BlazeUnit::new));
    public static final RegistryObject<UnitType<CaveSpiderUnit>> CAVE_SPIDER = Registration.UNIT_TYPES.register("cave_spider",()-> new UnitType<>(EntityType.CAVE_SPIDER, CaveSpiderUnit::new));
    public static final RegistryObject<UnitType<CreeperUnit>> CREEPER = Registration.UNIT_TYPES.register("creeper",()-> new UnitType<>(EntityType.CREEPER, CreeperUnit::new));
    public static final RegistryObject<UnitType<DrownedUnit>> DROWNED = Registration.UNIT_TYPES.register("drowned",()-> new UnitType<>(EntityType.DROWNED, DrownedUnit::new));
    public static final RegistryObject<UnitType<EndermanUnit>> ENDERMAN = Registration.UNIT_TYPES.register("enderman",()-> new UnitType<>(EntityType.ENDERMAN, EndermanUnit::new));
    public static final RegistryObject<UnitType<EvokerUnit>> EVOKER_UNIT = Registration.UNIT_TYPES.register("evoker",()-> new UnitType<>(EntityType.EVOKER, EvokerUnit::new));
    public static final RegistryObject<UnitType<HuskUnit>> HUSK = Registration.UNIT_TYPES.register("husk",()-> new UnitType<>(EntityType.HUSK, HuskUnit::new));
    public static final RegistryObject<UnitType<IronGolemUnit>> IRON_GOLEM = Registration.UNIT_TYPES.register("iron_golem",()-> new UnitType<>(EntityType.IRON_GOLEM, IronGolemUnit::new));
    public static final RegistryObject<UnitType<PhantomUnit>> PHANTOM = Registration.UNIT_TYPES.register("phantom",()->new UnitType<>(EntityType.PHANTOM, PhantomUnit::new));
    public static final RegistryObject<UnitType<PigmanUnit>> PIGMAN = Registration.UNIT_TYPES.register("pigman",()->new UnitType<>(EntityType.PIGLIN, PigmanUnit::new));
    public static final RegistryObject<UnitType<PillagerUnit>> PILLAGER = Registration.UNIT_TYPES.register("pillager",()-> new UnitType<>(EntityType.PILLAGER, PillagerUnit::new));
    public static final RegistryObject<UnitType<SilverfishUnit>> SILVERFISH = Registration.UNIT_TYPES.register("silverfish",()-> new UnitType<>(EntityType.SILVERFISH, SilverfishUnit::new));
    public static final RegistryObject<UnitType<SkeletonUnit>> SKELETON = Registration.UNIT_TYPES.register("skeleton",()-> new UnitType<>(EntityType.SKELETON, SkeletonUnit::new));
    public static final RegistryObject<UnitType<SlimeUnit>> SLIME = Registration.UNIT_TYPES.register("slime",()-> new UnitType<>(EntityType.SLIME, SlimeUnit::new));
    public static final RegistryObject<UnitType<SpiderUnit>> SPIDER = Registration.UNIT_TYPES.register("spider",()-> new UnitType<>(EntityType.SPIDER, SpiderUnit::new));
    public static final RegistryObject<UnitType<StrayUnit>> STRAY = Registration.UNIT_TYPES.register("stray",()-> new UnitType<>(EntityType.STRAY, StrayUnit::new));
    public static final RegistryObject<UnitType<VexUnit>> VEX = Registration.UNIT_TYPES.register("vex",()-> new UnitType<>(EntityType.VEX, VexUnit::new));
    public static final RegistryObject<UnitType<VindicatorUnit>> VINDICATOR = Registration.UNIT_TYPES.register("vindicator",()-> new UnitType<>(EntityType.VINDICATOR, VindicatorUnit::new));
    public static final RegistryObject<UnitType<WitchUnit>> WITCH = Registration.UNIT_TYPES.register("witch",()-> new UnitType<>(EntityType.WITCH, WitchUnit::new));
    public static final RegistryObject<UnitType<WolfUnit>> WOLF = Registration.UNIT_TYPES.register("wolf",()-> new UnitType<>(EntityType.WOLF, WolfUnit::new));
    public static final RegistryObject<UnitType<ZombieUnit>> ZOMBIE = Registration.UNIT_TYPES.register("zombie",()-> new UnitType<>(EntityType.ZOMBIE, ZombieUnit::new));
    public static final RegistryObject<UnitType<ZombiePigmanUnit>> ZOMBIE_PIGMAN = Registration.UNIT_TYPES.register("zombie_pigman",()-> new UnitType<>(EntityType.ZOMBIFIED_PIGLIN, ZombiePigmanUnit::new));
    public static final RegistryObject<UnitType<ZombieVillagerUnit>> ZOMBIE_VILLAGER = Registration.UNIT_TYPES.register("zombie_villager",()->new UnitType<>(EntityType.ZOMBIE_VILLAGER, ZombieVillagerUnit::new));

    public static void register(){

    }
}
