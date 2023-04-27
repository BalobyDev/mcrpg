package net.baloby.mcrpg.client.gui.indicator;

public class ResistIndicator extends DmgIndicator{
    public ResistIndicator(int dmg) {
        super(dmg);
        this.extra = "RESIST";
    }
}
