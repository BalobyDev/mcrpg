package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;

public enum Element {
    PHYSICAL(new ResourceLocation(mcrpg.MODID,"textures/element/physical.png")),
    FIRE(new ResourceLocation(mcrpg.MODID,"textures/element/fire.png")),
    ICE(new ResourceLocation(mcrpg.MODID,"textures/element/ice.png")),
    ELECTRIC(new ResourceLocation(mcrpg.MODID,"textures/element/electric.png")),
    LIFE(new ResourceLocation(mcrpg.MODID,"textures/element/life.png")),
    ENDER(new ResourceLocation(mcrpg.MODID,"textures/element/ender.png")),
    WITHER(new ResourceLocation(mcrpg.MODID,"textures/element/wither.png")),
    AILMENT(new ResourceLocation(""),false),
    SUPPORT(new ResourceLocation(mcrpg.MODID,"textures/element/support.png"),false),
    STATMOD(new ResourceLocation(mcrpg.MODID,"textures/element/statmod.png"),false);

    private final ResourceLocation icon;

    private boolean combat = true;

    Element(ResourceLocation icon){
        this.icon = icon;
        this.combat = combat;
    }
    Element(ResourceLocation icon,boolean combat){
        this(icon);
        this.combat = combat;
    }

    public ResourceLocation getIcon(){
        return this.icon;
    }

    public boolean getCombat(){return combat;}
}


