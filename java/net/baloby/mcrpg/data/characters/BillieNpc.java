package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;

public class BillieNpc extends BattleNpc {

    public BillieNpc(){
        super(Npcs.BILLIE.get(),new TranslationTextComponent("npc.mcrpg.billie"),new ResourceLocation(mcrpg.MODID,"textures/entity/billie.png"), ModEntities.HUMANOID.get(), Items.NETHERITE_AXE,25,18, Moves.ENDRA.get());
        this.hurtSound = SoundEvents.ENDERMAN_HURT;
        this.size = 1.2f;
        this.weaponType = WeaponType.AXE;

    }
}
