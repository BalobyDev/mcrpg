package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.Move;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ICharProfile {


    void setMp(int mp);

    void setMaxMp(int maxMp);

    void setMaxHp(int maxHp);

    int getMp();

    int getMaxMp();

    int getMaxHp();

    ArrayList<Move> getMoves();
}
