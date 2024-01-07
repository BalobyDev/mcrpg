package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class IdaNpc extends BattleNpc{

    public IdaNpc(){
        super(Npcs.IDA.get(),"Ida",new ResourceLocation(mcrpg.MODID,"textures/entity/ida.png"), ModEntities.HUMANOID.get(), Items.NETHERITE_AXE,180,250);

    }
}
