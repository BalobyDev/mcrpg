package net.baloby.mcrpg.tools;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.DamageAilment;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.client.gui.MoveTextGui;

import java.util.ArrayList;

public class AnimationSequence {
    public Battle battle = Battle.getInstance();
    public ArrayList<SequencePart> list = new ArrayList<SequencePart>();
    private boolean started = false;

    public AnimationSequence(){

    }

    public void tick(){
        if (!started)return;
        SequencePart part = list.get(0);
        if(part.time>0){
            part.tick();
        }
        else {
            list.remove(part);
            if(list.isEmpty()){
                end();
            }
            else {
                list.get(0).runnable.run();
            }
        }
    }

    public void start(){
        battle.sequence = this;
        started = true;
        list.get(0).runnable.run();
    }

    public void end(){
        battle.sequence = null;
    }


    public void addPart(Runnable runnable, int time){
        list.add(new SequencePart(runnable,time));
    }

    public static AnimationSequence ailmentSequence(AilmentInflictingMove move, Unit user, Unit target, Ailment ailment){
        AnimationSequence sequence = new AnimationSequence();
        sequence.addPart(()->{
            Battle.getInstance().camera.setFacingAngled(user);
            user.swing();
        },20);
        sequence.addPart(()->{
            Battle.getInstance().camera.setFacingAngled(target);
            move.trySetAilment(target,ailment);
            Animation.sound(move.getSound());
        },20);
        return sequence;
    }

    public static AnimationSequence aoeAttackSequence(AOESpell move, Unit user, Party target){
        AnimationSequence sequence = new AnimationSequence();
        sequence.addPart(()->{
            Battle.getInstance().camera.setFacingAngled(user);
            user.swing();
        },20);

            for (int i = 0; i < target.active.size(); i++) {
                int finalI = i;
                sequence.addPart(()->{
                Battle.getInstance().camera.setFacingParty(target);
                //index out of bounds exception when they die
                move.tryAttack(user,target.active.get(finalI), move.dmg);

            },7);
        }
        return sequence;
    }

    public static AnimationSequence basicAtkSequence(Attack move, Unit user, Unit target, float dmg,boolean crit){
        AnimationSequence sequence = new AnimationSequence();
        sequence.addPart(()->{
            Battle.getInstance().camera.setFacingAngled(user);
            user.swing();
        },20);
        sequence.addPart(()->{
            Battle.getInstance().camera.setFacingAngled(target);
            move.tryAttack(user, target, dmg);
        },20);
        if(user.getAilment()!=null){
            if(user.getAilment() instanceof DamageAilment){
                sequence.addPart(()->{Battle.getInstance().camera.setFacingAngled(user);
                    user.getAilment().execute(user);
                },20);
            }
        }
        return sequence;
    }

    public static AnimationSequence basicAllySequence(Move move, Unit user, Unit target, float hp){
        AnimationSequence sequence = new AnimationSequence();
        sequence.addPart(()->{
            Battle.getInstance().camera.setFacing(user);
            user.swing();
        },20);
        sequence.addPart(()->{Battle.getInstance().camera.setFacing(target);
            target.heal(hp);
            if(move instanceof Heal){
                Animation.particles(target, ((Heal) move).particle, 10,((Heal) move).sound);
            }
        },20);
        return sequence;
    }
}
