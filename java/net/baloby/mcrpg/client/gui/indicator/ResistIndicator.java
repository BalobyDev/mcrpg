package net.baloby.mcrpg.client.gui.indicator;

public class ResistIndicator extends DmgIndicator{
    public ResistIndicator(int dmg,int old) {
        super(dmg,old);
        this.extra = "RESIST";
    }
}
