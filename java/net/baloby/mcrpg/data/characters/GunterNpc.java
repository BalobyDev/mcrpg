package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.data.characters.shop.CostType;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.baloby.mcrpg.data.characters.shop.ShopItem;
import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class GunterNpc extends Npc implements ShopNpc{

    private Shop shop;

    public GunterNpc(){
        super(Npcs.GUNTER.get(), "Gunter", ModEntities.HUMANOID_SLIM.get(), new ResourceLocation(mcrpg.MODID, "textures/entity/gunther.png"));
        this.shop = new Shop(new ShopItem(Items.IRON_SWORD,3,1, CostType.EMERALDS));
        this.dialogueTree = new DialogueTree(ModSetup.DIALOGUE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"gunter_intro")));
    }


    @Override
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public Shop getShop() {
        return this.shop;
    }
}
