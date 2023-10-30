package net.baloby.mcrpg.battle.moves;

import net.minecraft.util.text.StringTextComponent;

public class LifeGoesOn extends Move{
    public LifeGoesOn() {
        super(new StringTextComponent("Life Goes On"), new StringTextComponent("Revives a party member"));
        this.cost = 4;
    }
}
