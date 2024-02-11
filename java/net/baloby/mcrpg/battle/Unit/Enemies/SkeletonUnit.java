package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.entities.custom.enemies.NewSkeletonEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class SkeletonUnit extends Unit {
    public SkeletonUnit(){
        super(EntityType.SKELETON);
        this.BASE_STR = 40;
        this.SPD = 10;
        this.XP = 10;
        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.WITHER, Affinity.STRONG);
        addSummonable(EntityType.ZOMBIE,2);
        addSummonable(EntityType.SPIDER,2);
    }

    @Override
    public MobEntity spawn(EntityType type){
        MobEntity e = new NewSkeletonEntity(EntityType.SKELETON, arena);
        e.setItemInHand(Hand.MAIN_HAND,new ItemStack(Items.BOW));
        return e;
    }
}
