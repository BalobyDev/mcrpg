package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;

public class SlimeUnit extends Unit {

    private int size;
    private SlimeEntity slime;

    public SlimeUnit(){
        super(EntityType.SLIME);
        addAffinity(Element.FIRE, Affinity.BLOCK);
        addAffinity(Element.LIFE, Affinity.BLOCK);
        this.slime = (SlimeEntity) entity;
        this.size = slime.getSize();
        Animation.particles(this,ParticleTypes.ITEM_SLIME,10, SoundEvents.SLIME_JUMP);
    }

    public void grow(){
        this.size++;
    }

    public void shrink(){
        this.size--;
    }

    public void newSlime(){
        this.entity.remove();
        this.battle.arena.addFreshEntity(new SlimeEntity(EntityType.SLIME,arena));
    }
}
