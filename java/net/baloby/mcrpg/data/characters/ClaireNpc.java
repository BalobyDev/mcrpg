package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class ClaireNpc extends BattleNpc{

    public ClaireNpc(){
        super(Npcs.CLAIRE.get(),new StringTextComponent("Claire"),new ResourceLocation(mcrpg.MODID,"textures/entity/claire.png"), ModEntities.HUMANOID_SLIM.get(), Items.AIR, 40,55,
                Moves.LULLABY.get(),Moves.MELODY.get());
        this.MAG = 44;
        this.ENDURANCE = 30;
        this.bounty = 400;
    }
}
