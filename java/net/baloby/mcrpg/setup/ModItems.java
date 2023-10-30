package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.setup.Items.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> BATWING = Registration.ITEMS.register("bat_wing", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> FAIRY_DUST = Registration.ITEMS.register("fairy_dust", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> HOMESTEW = Registration.ITEMS.register("home_stew", () ->
            new HomeStewItem(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(new Food.Builder().nutrition(5).saturationMod(2).alwaysEat().build())));
    public static final RegistryObject<Item> IRONSHIELD = Registration.ITEMS.register( "iron_shield", ()->
            new IronShieldItem(new Item.Properties().durability(1008).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> MAGIC_MILKSHAKE = Registration.ITEMS.register("magic_milkshake", () ->
            new MagicMilkshakeItem(new Item.Properties().tab(ItemGroup.TAB_BREWING)));
    public static final RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SPICY_MILKSHAKE = Registration.ITEMS.register("spicy_milkshake", () ->
            new MagicMilkshakeItem(new Item.Properties().tab(ItemGroup.TAB_BREWING)));
    public static final RegistryObject<Item> WITCH_HAT = Registration.ITEMS.register("witch_hat", () ->
            new WitchHatItem(new WitchHatArmorMaterial(33,4,0,0), EquipmentSlotType.HEAD,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    static void register() {}

}
