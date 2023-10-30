package net.baloby.mcrpg.world;

import net.minecraft.util.text.ITextComponent;

public class Location {

    private ITextComponent name;

    public Location(ITextComponent name){
        this.name = name;
    }

    public ITextComponent getName() {
        return name;
    }
}
