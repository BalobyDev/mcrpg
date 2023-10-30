package net.baloby.mcrpg.battle.ailments;

public class ChickenAilment extends ActionPreventAilment{
    public ChickenAilment() {
        super("Chicken", 100);
    }

    public String getTextToShow(){
        return unit.name+" is too busy being a chicken!";
    }

}
