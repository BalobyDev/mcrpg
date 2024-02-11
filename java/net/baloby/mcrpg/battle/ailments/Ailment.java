package net.baloby.mcrpg.battle.ailments;


import net.baloby.mcrpg.battle.Unit.Unit;

public class Ailment {

    public Unit unit;
    public String name;
    public int turnsLeft;

    public boolean isMental() {
        return mental;
    }

    protected boolean mental;


    public Ailment(String name){
        this.name = name;
        this.turnsLeft = 3;
    }

    public String getTextToShow(){
        return unit.name;
    }


    public void execute(Unit unit){
        this.turnsLeft-=1;
    }

}
