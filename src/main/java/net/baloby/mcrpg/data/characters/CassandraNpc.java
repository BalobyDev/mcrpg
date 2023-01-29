package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class CassandraNpc extends BattleNpc implements ShopNpc{

    private Shop shop;

    public CassandraNpc(){
        super(Npcs.CASSANDRA.get(), "Cassandra", new ResourceLocation(mcrpg.MODID,"textures/entity/cassandra.png"),true, Items.AIR,150,150, Moves.IGNI.get());
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
