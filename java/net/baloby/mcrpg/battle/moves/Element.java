package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;

public enum Element {
    PHYSICAL(new ResourceLocation(mcrpg.MODID,"textures/element/fire.png")),
    ELECTRIC(new ResourceLocation(mcrpg.MODID,"textures/element/electric.png")),
    FIRE(new ResourceLocation(mcrpg.MODID,"textures/element/fire.png")),
    LIFE(new ResourceLocation(mcrpg.MODID,"textures/element/life.png")),
    ICE(new ResourceLocation(mcrpg.MODID,"textures/element/ice.png")),
    ENDER(new ResourceLocation(mcrpg.MODID,"textures/element/ender.png")),
    WITHER(new ResourceLocation(mcrpg.MODID,"textures/element/wither.png")),
    SUPPORT(new ResourceLocation(mcrpg.MODID,"textures/element/support.png"));

    private final ResourceLocation icon;

    Element(ResourceLocation icon){
        this.icon = icon;
    }

    public ResourceLocation getIcon(){
        return this.icon;
    }
}


