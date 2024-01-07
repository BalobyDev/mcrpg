package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.entities.custom.enemies.NewEndermanEntity;
import net.baloby.mcrpg.entities.custom.enemies.NewStrayEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;

public class StrayUnit extends Unit {
    public StrayUnit(){
        super(EntityType.STRAY);

        addAffinity(Element.LIFE, Affinity.WEAK);
        addAffinity(Element.ICE, Affinity.STRONG);

    }

    @Override
    public MobEntity spawn(EntityType type){
        MobEntity e = new NewStrayEntity(EntityType.STRAY, arena);
        return e;
    }
}
