package net.baloby.mcrpg.battle.StatMod;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public enum Stat {
    ATK(new StringTextComponent("Attack")){
        @Override
        public void takeEffect(Unit unit, int direction) {
            unit.STR += (unit.BASE_STR/4)*direction;
            unit.MAG += (unit.BASE_MAG/4)*direction;
        }
    },
    DEF(new StringTextComponent("Defense")){
        @Override
        public void takeEffect(Unit unit,int direction) {
            unit.DEF += (unit.BASE_DEF/4)*direction;
        }
    },
    SPD(new StringTextComponent("Speed")) {
        @Override
        public void takeEffect(Unit unit, int direction) {
            unit.SPD += (unit.BASE_SPD/4)*direction;
        }
    };

    private ITextComponent text;
    Stat(ITextComponent text){
        this.text = text;
    }

    public ITextComponent getText(){
        return text;
    }

    public abstract void takeEffect(Unit unit, int direction);

}
