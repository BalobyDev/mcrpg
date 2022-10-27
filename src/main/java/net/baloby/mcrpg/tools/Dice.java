package net.baloby.mcrpg.tools;

import java.util.Random;

public class Dice {
    public static int roll(){
        Random random = new Random();
        int roll = random.nextInt(20) + 1;
        return roll;
    }
    public static int roll(int number){
        Random random = new Random();
        int roll = random.nextInt(number);
        return roll;
    }
}
