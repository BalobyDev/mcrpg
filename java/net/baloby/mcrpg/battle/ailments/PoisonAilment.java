package net.baloby.mcrpg.battle.ailments;

public class PoisonAilment extends DamageAilment {
    public PoisonAilment(){
        super("Poison", 2);
    }

    @Override
    public String getTextToShow(){
        return unit.name + " is hurt by posion!";
    }
}
