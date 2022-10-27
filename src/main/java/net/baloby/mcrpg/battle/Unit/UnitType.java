package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Unit.Enemies.*;
import net.minecraft.entity.EntityType;


import java.util.ArrayList;
import java.util.HashMap;

public class UnitType<T extends Unit> {

    private EntityType entity;
    private T unit;
    private final UnitType.IFactory<T> factory;
    public ArrayList<UnitType> summonable = new ArrayList<UnitType>();

    public static HashMap<EntityType, UnitType> unitMap = new HashMap<>();

    //public static final UnitType<AlexUnit> ALEX_UNIT = new UnitType<AlexUnit>(ModEntities.HUMANOID.get(), (AlexUnit::new));
    public static final UnitType<BlazeUnit> BLAZE_UNIT = new UnitType<BlazeUnit>(EntityType.BLAZE, (BlazeUnit::new));
    public static final UnitType<CaveSpiderUnit> CAVE_SPIDER_UNIT = new UnitType<CaveSpiderUnit>(EntityType.CAVE_SPIDER, (CaveSpiderUnit::new));
    public static final UnitType<CreeperUnit> CREEPRER_UNIT = new UnitType<CreeperUnit>(EntityType.CREEPER, (CreeperUnit::new));
    public static final UnitType<DrownedUnit> DROWNED_UNIT = new UnitType<DrownedUnit>(EntityType.DROWNED, (DrownedUnit::new));
    public static final UnitType<EndermanUnit> ENDERMAN_UNIT = new UnitType<EndermanUnit>(EntityType.ENDERMAN, (EndermanUnit::new));
    public static final UnitType<EnderDragonUnit> ENDER_DRAGON_UNIT = new UnitType<EnderDragonUnit>(EntityType.ENDER_DRAGON, (EnderDragonUnit::new));
    public static final UnitType<EvokerUnit> EVOKER_UNIT = new UnitType<EvokerUnit>(EntityType.EVOKER, (EvokerUnit::new));
    public static final UnitType<HuskUnit> HUSK_UNIT = new UnitType<HuskUnit>(EntityType.HUSK, (HuskUnit::new));
    public static final UnitType<IronGolemUnit> IRON_GOLEM_UNIT = new UnitType<IronGolemUnit>(EntityType.IRON_GOLEM, (IronGolemUnit::new));
    public static final UnitType<PigmanUnit> PIGMAN_UNIT = new UnitType<PigmanUnit>(EntityType.PIGLIN, (PigmanUnit::new));
    public static final UnitType<PillagerUnit> PILLAGER_UNIT = new UnitType<PillagerUnit>(EntityType.PILLAGER, (PillagerUnit::new));
    public static final UnitType<SkeletonUnit> SKELETON_UNIT = new UnitType<SkeletonUnit>(EntityType.SKELETON,(SkeletonUnit::new));
    public static final UnitType<SpiderUnit> SPIDER_UNIT = new UnitType<SpiderUnit>(EntityType.SPIDER,(SpiderUnit::new));
    public static final UnitType<VindicatorUnit> VINDICATOR_UNIT = new UnitType<VindicatorUnit>(EntityType.VINDICATOR,(VindicatorUnit::new));
    public static final UnitType<WitchUnit> WITCH_UNIT = new UnitType<WitchUnit>(EntityType.WITCH,(WitchUnit::new));
    public static final UnitType<WitherSkeletonUnit> WITHER_SKELETON_UNIT = new UnitType<WitherSkeletonUnit>(EntityType.WITHER_SKELETON,(WitherSkeletonUnit::new));
    public static final UnitType<WolfUnit> WOLF_UNIT = new UnitType<WolfUnit>(EntityType.WOLF, (WolfUnit::new));
    public static final UnitType<ZombieUnit> ZOMBIE_UNIT = new UnitType<ZombieUnit>(EntityType.ZOMBIE,(ZombieUnit::new));
    public static final UnitType<ZombiePigmanUnit> ZOMBIE_PIGMAN_UNIT = new UnitType<ZombiePigmanUnit>(EntityType.ZOMBIFIED_PIGLIN, (ZombiePigmanUnit::new));



    public UnitType(EntityType type,IFactory factory){
        this.entity = type;
        this.factory = factory;
    }

    public static void registerUnits(){
        //ALEX_UNIT.register();
        BLAZE_UNIT.register();
        CAVE_SPIDER_UNIT.register();
        CREEPRER_UNIT.register();
        DROWNED_UNIT.register();
        ENDERMAN_UNIT.register();
        ENDER_DRAGON_UNIT.register();
        EVOKER_UNIT.register();
        HUSK_UNIT.register();
        IRON_GOLEM_UNIT.register();
        PIGMAN_UNIT.register();
        PILLAGER_UNIT.register();
        SKELETON_UNIT.register();
        SPIDER_UNIT.register();
        VINDICATOR_UNIT.register();
        WITCH_UNIT.register();
        WITHER_SKELETON_UNIT.register();
        WOLF_UNIT.register();
        ZOMBIE_PIGMAN_UNIT.register();
        ZOMBIE_UNIT.register();
    }

    public void addSummonable(UnitType unitType){

    }

    public void register(){
        unitMap.put(entity, this);
    }

    public Unit create(){
        return factory.create();
    }

    public interface IFactory<T extends Unit>{
        T create();
    }
}
