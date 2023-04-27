package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.dialouge.*;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class AlexNpc extends BattleNpc{

    public AlexNpc(){
        super(Npcs.ALEX.get(),"Alex",new ResourceLocation("textures/entity/alex.png"), ModEntities.HUMANOID_SLIM.get(), Items.TRIDENT, 20,20, Moves.VOLTAGE.get());
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"alex_intro")));
    }
}
