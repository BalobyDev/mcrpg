package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;

public class UnitSelection {
    public Party party;
    public Move move;
    public Unit user;
    public Unit selected;
    public Battle battle;
    private static UnitSelection selection;

    public UnitSelection(Party party, Move move){
        this.party = party;
        this.user = battle.activeUnit;
        this.battle.camera.setPos(1.5,3,4.5);
        this.move = move;
        this.selected = party.active.get(party.active.size()-1);
        selection = this;
    }

    public void takeAction(){
        user.action(move,selected);
    }

    public void changeSelected(){
        selected.highlight();
        selected = party.members.get((party.members.indexOf(selected)+1)% party.members.size());
        selected.highlight();
    }



    public static UnitSelection get(){return selection;}
}
