package net.baloby.mcrpg.battle.moves;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Lullaby extends AilmentInflictingMove{
    public Lullaby() {
        super(new StringTextComponent("Lullaby"), new StringTextComponent("Puts one enemy to sleep"));
    }
}
