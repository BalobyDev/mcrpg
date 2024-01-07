package net.baloby.mcrpg.battle.ailments;

public class SleepAilment extends ActionPreventAilment{

    public SleepAilment() {
        super("Sleep", 0);
    }

    @Override
    public String getTextToShow(){
        return unit.name + " is asleep!";
    }
}