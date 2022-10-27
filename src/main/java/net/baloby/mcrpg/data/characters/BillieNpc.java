package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class BillieNpc extends BattleNpc {

    public BillieNpc(){
        super("billie",new ResourceLocation(mcrpg.MODID,"textures/entity/billie.png"),false, Items.NETHERITE_AXE,40,20);

    }
}
