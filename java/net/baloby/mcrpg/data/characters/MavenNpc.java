package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class MavenNpc extends BattleNpc{

    public MavenNpc(){
        super(Npcs.MAVEN.get(), "Maven", new ResourceLocation(mcrpg.MODID, "textures/entity/maven.png"), ModEntities.HUMANOID_SLIM.get(), Items.AIR, 300, 400);
        this.bounty = 2000;

    }
}
