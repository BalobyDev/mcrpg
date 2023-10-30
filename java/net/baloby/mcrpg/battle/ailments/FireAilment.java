package net.baloby.mcrpg.battle.ailments;

public class FireAilment extends DamageAilment {

    public FireAilment() {
        super("Fire", 3);
    }

    @Override
    public String getTextToShow(){
        return unit.name + " is hurt by fire!";
    }
}
