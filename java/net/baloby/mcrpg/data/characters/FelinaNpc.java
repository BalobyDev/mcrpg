package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.characters.shop.*;
import net.baloby.mcrpg.data.dialouge.*;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModItems;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

public class FelinaNpc extends BattleNpc implements ShopNpc{

    private Shop shop;

    public FelinaNpc(){
        super(Npcs.FELINA.get(), "Felina", new ResourceLocation(mcrpg.MODID,"textures/entity/cassandra.png"), ModEntities.HUMANOID_SLIM.get(), Items.AIR,150,150, Moves.IGNI.get());
        this.shop = new Shop(
                new ShopItem(ModItems.MAGIC_MILKSHAKE.get(),3,50, CostType.EXP),
                new ShopItem(ModItems.HOMESTEW.get(),5,50, CostType.EXP),
                new ShopMove(Moves.IGNI.get(),10,1, CostType.EXP),
                new ShopMove(Moves.FLORA.get(),10,1, CostType.EXP),
                new ShopMove(Moves.AQUA.get(),10,1, CostType.EXP),
                new ShopMove(Moves.MIRACLE.get(),18,1, CostType.EXP));
        this.hurtSound = SoundEvents.CAT_HURT;
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"felina_intro")));

    }

    @Override
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public Shop getShop() {
        return shop;
    }
}
