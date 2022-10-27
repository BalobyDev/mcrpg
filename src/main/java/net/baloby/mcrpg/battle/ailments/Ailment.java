package net.baloby.mcrpg.battle.ailments;


import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.MoveTextGui;
import net.baloby.mcrpg.tools.Dice;

public class Ailment {

    public Unit unit;
    public String name;
    public String textToShow;
    public int turnsLeft;


    public Ailment(String name, String textToShow){
        this.name = name;
        this.turnsLeft = 3;
    }

    public static Ailment POISON = new DamageAilment("Poison","Poison",2);

    public void execute(Unit unit){
        this.turnsLeft-=1;

    }

    public static class MoveStopAilment extends Ailment{

        public int chance;

        public MoveStopAilment(String name,int chance) {
            super(name,"");
            this.chance = chance;

        }

        @Override
        public void execute(Unit unit){
            if(Dice.roll()<chance){
                MoveTextGui.open(this);
            }
            else{
                unit.takeTurn();
            }
        }
    }

    public static class DamageAilment extends Ailment{
        public float dmg;

        public DamageAilment(String name, String textToShow,float dmg) {
            super(name, textToShow);
            this.dmg = dmg;
        }

        @Override
        public void execute(Unit unit){
            MoveTextGui.open(this);
            unit.takeDamage(dmg);
        }
    }
}
