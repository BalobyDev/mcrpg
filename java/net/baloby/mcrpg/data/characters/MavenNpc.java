package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class MavenNpc extends BattleNpc{

    public MavenNpc(){
        super(Npcs.MAVEN.get(), new TranslationTextComponent("npc.mcrpg.maven"), new ResourceLocation(mcrpg.MODID, "textures/entity/maven.png"), ModEntities.HUMANOID_SLIM.get(), Items.AIR, 80, 80);
        this.bounty = 2000;

    }
}
