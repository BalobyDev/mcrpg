package net.baloby.mcrpg.tools;

public class SequencePart {


    public Runnable runnable;
    public int time;

    public SequencePart(Runnable runnable, int time){
        this.runnable = runnable;
        this.time = time;
    }

    public void tick(){time--;}

}
