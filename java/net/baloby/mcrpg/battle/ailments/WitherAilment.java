package net.baloby.mcrpg.battle.ailments;

public class WitherAilment extends DamageAilment{
    public WitherAilment() {
        super("Wither", 2);
    }

    @Override
    public String getTextToShow(){
        return unit.name + " is withering away!";
    }
}
