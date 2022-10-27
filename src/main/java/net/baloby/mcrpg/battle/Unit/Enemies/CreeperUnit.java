package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Charge;
import net.baloby.mcrpg.battle.moves.Explode;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;

public class CreeperUnit extends Unit {

    private boolean charged;

    public CreeperUnit(){
        super(EntityType.CREEPER);
        addSummonable(EntityType.SPIDER);
        addSummonable(EntityType.SKELETON);
        addSummonable(EntityType.ZOMBIE);
        charged = false;
    }

    public void charge(){
        Animation.particles(this, ParticleTypes.SMOKE,10, SoundEvents.CREEPER_PRIMED);
        action(new Charge("The creeper is getting primed"),this);
        charged = true;

    }

    public void explode(){action(new Explode(),battle.playerParty.random());}

    @Override
    public void getMove(){
        if(HP>MAX_HP/2){super.getMove();}
        else if (charged == true) {explode();}
        else{charge();}
    }
}
