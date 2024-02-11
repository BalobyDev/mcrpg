package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.CultistMonkUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.HashMap;

public class CultistEntity extends MonsterEntity implements ICustomBattleEntity{

    public enum Type{MONK,SISTER,ACOLYTE,ELDER,APOSTLE}
    protected Type type;
    protected ResourceLocation skin = new ResourceLocation(mcrpg.MODID,"textures/entity/cult_member.png");

    protected static HashMap<Type,ResourceLocation> skinMap(){
        HashMap<Type, ResourceLocation> map = new HashMap<>();
        map.put(Type.MONK,new ResourceLocation(mcrpg.MODID,"textures/entity/cult_member.png"));

        return map;
    }

    public CultistEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    public ResourceLocation getSkin() {
        return skin;
    }

    @Override
    public Unit unit() {
        return new CultistMonkUnit();
    }
}
