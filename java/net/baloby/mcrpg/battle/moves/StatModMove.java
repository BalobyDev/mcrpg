package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.StatMod.Stat;
import net.baloby.mcrpg.battle.StatMod.StatMod;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.baloby.mcrpg.tools.AnimationSequence;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class StatModMove extends Move{

    private Stat stat;
    private int direction;

    public StatModMove(ITextComponent name, ITextComponent desc, Stat stat, int direction) {
        super(name, desc);
        this.type = Element.STATMOD;
        this.cost = 2;
        this.stat = stat;
        this.direction = direction;
    }

    public ITextComponent getTextToShow(){
        return new StringTextComponent(stat.getText().getString()+" "+(direction==1?"up":"down")+"!");
    }

    public StatMod createStatMod(Unit unit){
        return new StatMod(stat,direction,unit);
    }

    public void apply(Unit target){
        StatMod mod = createStatMod(target);
        mod.takeEffect();
        target.statMods.put(stat,mod);
        MoveTextGui.open(getTextToShow());
    }

    public int getDirection() {
        return direction;
    }
}
