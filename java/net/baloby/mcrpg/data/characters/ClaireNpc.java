package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class ClaireNpc extends BattleNpc{

    public ClaireNpc(){
        super(Npcs.CLAIRE.get(),"Claire",new ResourceLocation(mcrpg.MODID,"textures/entity/claire.png"), ModEntities.HUMANOID_SLIM.get(), Items.AIR, 80,100,
                Moves.LULLABY.get(),Moves.MELODY.get());
        this.MAG = 30;
        this.SPD = 30;
        this.bounty = 400;
    }
}
