package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.ailments.SleepAilment;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Lullaby extends AilmentInflictingMove{

    public Lullaby() {
        super(new StringTextComponent("Lullaby"), new StringTextComponent("Puts one enemy to sleep"),50);
        this.cost = 2;
    }

    @Override
    protected Ailment createAilment() {
        return new SleepAilment();
    }
}