package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class JeanNpc extends BattleNpc{
    public JeanNpc(){
        super(Npcs.JEAN.get(), new TranslationTextComponent("npc.mcrpg.jean"), new ResourceLocation(mcrpg.MODID, "textures/entity/jean.png"), ModEntities.HUMANOID_SLIM.get(), Items.ENDER_EYE, 20, 20);
    }
}
