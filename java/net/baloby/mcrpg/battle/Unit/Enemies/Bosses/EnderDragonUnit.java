package net.baloby.mcrpg.battle.Unit.Enemies.Bosses;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.entity.EntityType;
import net.minecraft.util.text.StringTextComponent;

public class EnderDragonUnit extends BossUnit {

    public EnderDragonUnit(){
        super(EntityType.ENDER_DRAGON);
        this.MAX_HP = 200;
        this.HP = 200;
        this.removeMoves();
        this.addAffinity(Element.ENDER, Affinity.BLOCK);
        this.addMove(new BasicAttack(new StringTextComponent("Bite")));
        this.BASE_STR=10;
        this.BASE_MAG=12;
    }
}
