package net.baloby.mcrpg.battle.moves;

public enum Affinity {
    NONE("-"),
    WEAK("Wk"),
    STRONG("Str"),
    BLOCK("Nul"),
    DRAIN("Dr");

    private final String text;

    Affinity(String text){
        this.text = text;
    }

    public String getText(){return text;}
}
