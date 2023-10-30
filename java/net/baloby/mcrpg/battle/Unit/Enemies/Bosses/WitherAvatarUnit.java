package net.baloby.mcrpg.battle.Unit.Enemies.Bosses;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;

public class WitherAvatarUnit extends BossUnit {

    public WitherAvatarUnit(){
        super(EntityType.WITHER);
        this.addAffinity(Element.WITHER, Affinity.DRAIN);
        this.MAX_HP = 200;
        this.HP = 200;
    }
}
