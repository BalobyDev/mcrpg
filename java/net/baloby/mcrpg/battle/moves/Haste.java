package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.StatMod.Stat;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Haste extends BuffMove{
    public Haste() {
        super(new StringTextComponent("Haste"), new StringTextComponent("Increases the hit and evasion rate of one ally."), Stat.SPD, 1);
    }
}
