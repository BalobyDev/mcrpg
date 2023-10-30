package net.baloby.mcrpg.client.gui.indicator;

public class WeakIndicator extends DmgIndicator{
    public WeakIndicator(int dmg,int old) {
        super(dmg,old);
        this.extra = "WEAK!";
    }
}
