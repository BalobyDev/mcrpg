package net.baloby.mcrpg.client.gui.indicator;

public class WeakIndicator extends DmgIndicator{
    public WeakIndicator(int dmg) {
        super(dmg);
        this.extra = "WEAK!";
    }
}
