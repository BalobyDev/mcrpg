package net.baloby.mcrpg.tools;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Attack;
import net.baloby.mcrpg.battle.moves.Heal;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.SpellAttack;

import java.util.ArrayList;

public class AnimationSequence {
    public int time = 80;
    public int timeLeft;
    public Battle battle = Battle.getInstance();
    public ArrayList<Runnable> list = new ArrayList<Runnable>();
    private boolean started = false;

    public AnimationSequence(double time){
        this.time = (int) (time*10);
        this.timeLeft = this.time;


    }

    public void tick(){
        if (!started)return;
        timeLeft--;
        if(timeLeft == time/2){
            list.get(list.size()-1).run();
        }
        if(timeLeft==0){end();}
    }

    public void start(){
        battle.sequence = this;
        started = true;
        list.get(0).run();
    }

    public void end(){
        battle.sequence =null;
    }


    public void addThing(Runnable runnable){
        list.add(runnable);
    }


    public static AnimationSequence basicAtkSequence(Attack move, Unit user, Unit target, float dmg){
        AnimationSequence sequence = new AnimationSequence(4);
        sequence.list.add(()->{
            Battle.getInstance().camera.setFacingAngled(user);
            user.swing();
        });
        sequence.list.add(()->{Battle.getInstance().camera.setFacingAngled(target);
            move.tryAttack(user, target, dmg);
            if(move instanceof SpellAttack){
                Animation.particles(target, ((SpellAttack) move).particle, 10,((SpellAttack) move).sound);
            }
        });
        return sequence;
    }

    public static AnimationSequence basicAllySequence(Move move, Unit user, Unit target, float hp){
        AnimationSequence sequence = new AnimationSequence(4);
        sequence.list.add(()->{
            Battle.getInstance().camera.setFacing(user);
            user.swing();
        });
        sequence.list.add(()->{Battle.getInstance().camera.setFacing(target);
            target.heal(hp);
            if(move instanceof Heal){
                Animation.particles(target, ((Heal) move).particle, 10,((Heal) move).sound);
            }
        });
        return sequence;
    }


}
