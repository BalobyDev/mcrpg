package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.StatMod.Stat;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Fortitude extends BuffMove{

    public Fortitude() {
        super(new StringTextComponent("Fortitude"), new StringTextComponent("Increases the defense of one ally."), Stat.DEF, 1);
    }
}
