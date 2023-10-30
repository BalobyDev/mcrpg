package net.baloby.mcrpg.battle.ailments;

public class HungerAilment extends ActionPreventAilment{
    public HungerAilment() {
        super("Hunger", 50);
    }

    @Override
    public String getTextToShow(){
        return unit.name + " is busy thinking about "+ unit.favoriteFood;
    }
}
