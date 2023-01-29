package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class RanaNpc extends BattleNpc{

    public RanaNpc(){
        super(Npcs.RANA.get(),"Rana", new ResourceLocation(mcrpg.MODID, "textures/entity/rana.png"),true, Items.BOW,20,20, Moves.FLORA.get(),Moves.HEAL.get());
    }
}
