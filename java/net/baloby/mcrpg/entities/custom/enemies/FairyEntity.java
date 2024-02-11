package net.baloby.mcrpg.entities.custom.enemies;

import net.baloby.mcrpg.battle.Unit.Enemies.WoodlandPixieUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FairyEntity extends MobEntity implements ICustomBattleEntity {

    private ResourceLocation SKIN = new ResourceLocation(mcrpg.MODID,"textures/entity/woodland_pixie.png");
    public FairyEntity(EntityType<? extends MobEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    public ResourceLocation getSkin(){
        return SKIN;
    }

    @Override
    public Unit unit() {
        return new WoodlandPixieUnit();
    }
}
