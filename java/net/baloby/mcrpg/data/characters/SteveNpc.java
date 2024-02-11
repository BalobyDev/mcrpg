package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.baloby.mcrpg.setup.ModSounds;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class SteveNpc extends BattleNpc{
    public SteveNpc(){
        super(Npcs.STEVE.get(),new StringTextComponent("Steve"), new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png"), ModEntities.HUMANOID.get(), Items.IRON_SWORD,10,10, Moves.AQUA.get());
        this.LVL = 5;
        this.hurtSound = ModSounds.OOH.get();
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"steve_intro")));
        this.addAffinity(Element.PSYCHIC, Affinity.STRONG);
        this.ENDURANCE = 12;
        this.weaponType = WeaponType.SWORD;
    }
}
