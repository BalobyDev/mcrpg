package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Optional;

public abstract class Move {
    public ITextComponent name;
    public ITextComponent desc;
    public String altText;
    public Element type;
    public boolean friendly = false;
    public boolean ranged = false;
    public Unit user;
    public Unit target;
    public int spd = 100;
    public float cost = 0;
    public enum costType{HP, MP}

    public Move(ITextComponent name){
        this.name = name;
    }

    public Move(ITextComponent name, ITextComponent desc){
        this.name = name;
        this.desc = desc;
    }

    public void deduct(Unit user){
        user.MP -= cost;
    }

    public static class Properties{

    }
    public void execute(Unit user, Unit target){
        this.deduct(user);
    }

    public ITextComponent getName(){
        return name;
    }

    public Unit getTarget(Unit user){
        Unit target = friendly ? user.party.random() : user.battle.playerParty.random();
        return target;
    }
    public SoundEvent getSound(){
        return SoundEvents.CAT_DEATH;
    }
}
