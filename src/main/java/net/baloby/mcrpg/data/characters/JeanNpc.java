package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class JeanNpc extends BattleNpc{
    public JeanNpc(){
        super(Npcs.JEAN.get(), "Jean", new ResourceLocation(mcrpg.MODID, "textures/entity/jean.png"),true, Items.ENDER_EYE, 30, 45);
    }
}
