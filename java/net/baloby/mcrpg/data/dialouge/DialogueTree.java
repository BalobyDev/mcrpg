package net.baloby.mcrpg.data.dialouge;

import java.util.ArrayList;

public class DialogueTree{

    public ArrayList<DialogueChain> chains = new ArrayList<>();

    public DialogueTree(DialogueChain... branches){
        for(DialogueChain branch : branches){
            this.chains.add(branch);
        }
    }

    public DialogueTree addChain(DialogueChain chain){
        this.chains.add(chain);
        return this;
    }

    public DialogueChain getChain(int num){
        return chains.get(num);
    }
}
