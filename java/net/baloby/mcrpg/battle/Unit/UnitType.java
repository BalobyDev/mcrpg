package net.baloby.mcrpg.battle.Unit;

import net.baloby.mcrpg.battle.Unit.Enemies.*;
import net.baloby.mcrpg.battle.Unit.Enemies.HoglinUnit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;


import javax.annotation.Nullable;
import java.util.HashMap;

public class UnitType<T extends Unit> implements IForgeRegistryEntry<UnitType<?>> {

    private EntityType entity;
    private T unit;
    private final UnitType.IFactory<T> factory;
    private ResourceLocation registryName;
    public static final Class<UnitType<?>> UNIT_GENERIC = (Class<UnitType<?>>) ((Class<?>)UnitType.class);


    public static HashMap<EntityType, UnitType> unitMap = new HashMap<>();

    public static final UnitType<BlazeUnit> BLAZE_UNIT = new UnitType<BlazeUnit>(EntityType.BLAZE, (BlazeUnit::new));
    public static final UnitType<CaveSpiderUnit> CAVE_SPIDER_UNIT = new UnitType<CaveSpiderUnit>(EntityType.CAVE_SPIDER, (CaveSpiderUnit::new));
    public static final UnitType<CreeperUnit> CREEPRER_UNIT = new UnitType<CreeperUnit>(EntityType.CREEPER, (CreeperUnit::new));
    public static final UnitType<DrownedUnit> DROWNED_UNIT = new UnitType<DrownedUnit>(EntityType.DROWNED, (DrownedUnit::new));
    public static final UnitType<EndermanUnit> ENDERMAN_UNIT = new UnitType<EndermanUnit>(EntityType.ENDERMAN, (EndermanUnit::new));
    public static final UnitType<EvokerUnit> EVOKER_UNIT = new UnitType<EvokerUnit>(EntityType.EVOKER, (EvokerUnit::new));
    public static final UnitType<HoglinUnit> HOGLIN_UNIT = new UnitType<HoglinUnit>(EntityType.HOGLIN, (HoglinUnit::new));
    public static final UnitType<HuskUnit> HUSK_UNIT = new UnitType<HuskUnit>(EntityType.HUSK, (HuskUnit::new));
    public static final UnitType<IronGolemUnit> IRON_GOLEM_UNIT = new UnitType<IronGolemUnit>(EntityType.IRON_GOLEM, (IronGolemUnit::new));
    public static final UnitType<PigmanUnit> PIGMAN_UNIT = new UnitType<PigmanUnit>(EntityType.PIGLIN, (PigmanUnit::new));
    public static final UnitType<PillagerUnit> PILLAGER_UNIT = new UnitType<PillagerUnit>(EntityType.PILLAGER, (PillagerUnit::new));
    public static final UnitType<SkeletonUnit> SKELETON_UNIT = new UnitType<SkeletonUnit>(EntityType.SKELETON,(SkeletonUnit::new));
    public static final UnitType<SilverfishUnit> SILVERFISH_UNIT = new UnitType<SilverfishUnit>(EntityType.SILVERFISH,(SilverfishUnit::new));
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
        unitMap.put(type,this);
    }



    @Override
    public UnitType<T> setRegistryName(ResourceLocation name) {
        this.registryName = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public Class<UnitType<?>> getRegistryType() {
        return null;
    }

    public Unit create() {
        return factory.create();
    }

    public interface IFactory<T extends Unit>{
        T create();
    }
}
