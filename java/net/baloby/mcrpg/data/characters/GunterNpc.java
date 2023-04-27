package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.data.characters.shop.CostType;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.baloby.mcrpg.data.characters.shop.ShopItem;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class GunterNpc extends Npc implements ShopNpc{

    private Shop shop;

    public GunterNpc(){
        super(Npcs.GUNTER.get(), "Gunter", ModEntities.HUMANOID_SLIM.get(), new ResourceLocation(mcrpg.MODID, "textures/entity/gunther.png"));
        this.shop = new Shop(new ShopItem(Items.IRON_SWORD,3,1, CostType.EMERALDS));
    }


    @Override
    public void setShop(Shop shop) {

    }

    @Override
    public Shop getShop() {
        return null;
    }
}
