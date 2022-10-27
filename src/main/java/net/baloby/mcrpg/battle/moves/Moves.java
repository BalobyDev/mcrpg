package net.baloby.mcrpg.battle.moves;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;

import java.util.ArrayList;

public class Moves {

    public static ArrayList<Move> moveList = new ArrayList<Move>();
    public static Move AQUA = new SpellAttack(8, Element.ICE,"Aqua",ParticleTypes.BUBBLE_POP,SoundEvents.GRASS_BREAK);
    public static Move ENDRA = new SpellAttack(8, Element.ENDER, "Endra",ParticleTypes.DRAGON_BREATH,SoundEvents.ENDERMAN_TELEPORT);
    public static Move FLORA = new SpellAttack(8, Element.LIFE,"Flora",ParticleTypes.SOUL,SoundEvents.GRASS_BREAK);
    public static Move IGNI = new SpellAttack(8, Element.FIRE,"Igni",ParticleTypes.FLAME, SoundEvents.FIRECHARGE_USE);
    public static Move VOLTAGE = new SpellAttack(8, Element.ELECTRIC,"Voltage", ParticleTypes.TOTEM_OF_UNDYING, SoundEvents.NOTE_BLOCK_BIT);
    public static Move HEAL = new Heal();

    public static void register(){
        AQUA.register();
        ENDRA.register();
        FLORA.register();
        IGNI.register();
        VOLTAGE.register();
        HEAL.register();
    }



}
