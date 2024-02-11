package net.baloby.mcrpg.battle.ailments;

public class ChickenAilment extends ActionPreventAilment{
    public ChickenAilment() {
        super("ChickenMove", 0);
    }

    public String getTextToShow(){
        return unit.name + " is too busy being a chicken!";
    }

}
