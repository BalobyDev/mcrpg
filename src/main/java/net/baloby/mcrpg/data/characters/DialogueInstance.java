package net.baloby.mcrpg.data.characters;

public class DialogueInstance {

    protected Npc npc;
    private String text;

    public DialogueInstance(Npc npc, String text){
        this.npc = npc;
        this.text = text;

    }
}
