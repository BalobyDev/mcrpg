package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.CultistMonkUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class CultistEntity extends MonsterEntity implements ICustomBattleEntity{

    public enum Type{MONK,SISTER,ACOLYTE,ELDER}
    protected Type type;

    public CultistEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    @Override
    public Unit unit() {
        return new CultistMonkUnit();
    }
}
