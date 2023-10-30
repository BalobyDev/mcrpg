package net.baloby.mcrpg.client.gui.indicator;


public class CritIndicator extends DmgIndicator {

    public CritIndicator(int dmg,int old) {
        super(dmg,old);
        this.extra = "CRIT!";
    }
}
