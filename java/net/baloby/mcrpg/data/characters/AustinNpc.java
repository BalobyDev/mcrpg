package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class AustinNpc extends BattleNpc{

    public AustinNpc(){
        super(Npcs.AUSTIN.get(),new StringTextComponent("Austin"),new ResourceLocation(mcrpg.MODID,"textures/entity/austin.png"), ModEntities.HUMANOID_PIGLIN.get(), Items.NETHERITE_SWORD, 40,30);
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"austin_default")));
        this.STR = 35;
        this.ENDURANCE = 30;
    }

    @Override
    public SoundEvent getHurtSound() {
        return SoundEvents.PIGLIN_HURT;
    }
}
