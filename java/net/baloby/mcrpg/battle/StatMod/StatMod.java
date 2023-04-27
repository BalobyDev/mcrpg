package net.baloby.mcrpg.battle.StatMod;

import net.baloby.mcrpg.battle.Unit.Unit;

import java.util.HashMap;

public class StatMod {


    public HashMap<Stat,Integer> statMods;
    public Unit unit;

    public StatMod(Stat stat, int num, Unit unit){
        statMods.put(Stat.ATK,0);
        statMods.put(Stat.DEF,0);
        statMods.put(Stat.SPD,0);

    }
}
