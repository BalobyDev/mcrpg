package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.setup.Items.HomeStewItem;
import net.baloby.mcrpg.setup.Items.EtherItem;
import net.baloby.mcrpg.setup.Items.IronShieldItem;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> HOMESTEW = Registration.ITEMS.register("homestew", () ->
            new HomeStewItem(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(new Food.Builder().nutrition(5).saturationMod(2).alwaysEat().build())));
    public static final RegistryObject<Item> BATWING = Registration.ITEMS.register("batwing", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> ETHER = Registration.ITEMS.register("ether", () ->
            new EtherItem(new Item.Properties().tab(ItemGroup.TAB_BREWING)));
    public static final RegistryObject<Item> IRONSHIELD = Registration.ITEMS.register( "ironshield", ()->
            new IronShieldItem(new Item.Properties().durability(1008).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    static void register() {}

}
