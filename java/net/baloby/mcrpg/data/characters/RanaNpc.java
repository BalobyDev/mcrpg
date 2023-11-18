package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModItems;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class RanaNpc extends BattleNpc{

    public RanaNpc(){
        super(Npcs.RANA.get(),"Rana", new ResourceLocation(mcrpg.MODID, "textures/entity/rana.png"), ModEntities.HUMANOID_SLIM.get(), ModItems.IRON_YOYO.get(), 20,20, Moves.FLORA.get(),Moves.HEAL.get());
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"rana_intro")));
        this.STR = 8;
        this.MAG = 10;

    }
}
