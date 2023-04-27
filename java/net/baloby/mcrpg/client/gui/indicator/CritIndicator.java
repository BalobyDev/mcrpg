package net.baloby.mcrpg.client.gui.indicator;


public class CritIndicator extends DmgIndicator {

    public CritIndicator(int dmg) {
        super(dmg);
        this.extra = "CRIT!";
    }
}
