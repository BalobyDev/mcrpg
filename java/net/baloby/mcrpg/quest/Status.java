package net.baloby.mcrpg.quest;

public enum Status {


    LOCKED("locked"), UNLOCKED("in progress"), COMPLETE("complete");

    private final String text;

    Status(String text){
        this.text = text;
    }

    public String getText(){return this.text;}
}
