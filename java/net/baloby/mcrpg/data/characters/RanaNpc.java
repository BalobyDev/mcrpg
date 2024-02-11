package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModItems;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class RanaNpc extends BattleNpc{

    public RanaNpc(){
        super(Npcs.RANA.get(),new TranslationTextComponent("npc.mcrpg.rana"), new ResourceLocation(mcrpg.MODID, "textures/entity/rana.png"), ModEntities.HUMANOID_SLIM.get(), ModItems.IRON_YOYO.get(), 10,10, Moves.FLORA.get(),Moves.HEAL.get());
        this.LVL = 5;
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"rana_intro")));
        this.affinities.put(Element.FIRE, Affinity.WEAK);
        this.STR = 8;
        this.MAG = 10;

    }
}
