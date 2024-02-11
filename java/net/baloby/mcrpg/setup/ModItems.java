package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.setup.Items.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> BAT_WING = Registration.ITEMS.register("bat_wing", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> FAIRY_DUST = Registration.ITEMS.register("fairy_dust", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> GOLDEN_GREATSWORD = Registration.ITEMS.register("golden_greatsword", () ->
            new GreatSwordItem(ItemTier.GOLD,4,-1.4f,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> HOME_STEW = Registration.ITEMS.register("home_stew", () ->
            new HomeStewItem(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(new Food.Builder().nutrition(5).saturationMod(2).alwaysEat().build())));
    public static final RegistryObject<Item> IRON_SHIELD = Registration.ITEMS.register( "iron_shield", ()->
            new IronShieldItem(new Item.Properties().durability(1008).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_SPEAR = Registration.ITEMS.register("iron_spear", () ->
            new SpearItem(ItemTier.IRON,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_YOYO = Registration.ITEMS.register("iron_yoyo", () ->
            new YoYoItem(ItemTier.IRON,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> MAGIC_MILKSHAKE = Registration.ITEMS.register("magic_milkshake", () ->
            new MagicMilkshakeItem(new Item.Properties().tab(ItemGroup.TAB_BREWING)));
    public static final RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> RUBY_ORE = Registration.ITEMS.register("ruby_ore", () ->
            new BlockItem(ModBlocks.RUBY_ORE.get(),new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SLIME_CHEST = Registration.ITEMS.register("slime_chest", () ->
            new BlockItem(ModBlocks.SLIME_CHEST.get(),new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
    public static final RegistryObject<Item> SPICY_MILKSHAKE = Registration.ITEMS.register("spicy_milkshake", () ->
            new MagicMilkshakeItem(new Item.Properties().tab(ItemGroup.TAB_BREWING)));
    public static final RegistryObject<Item> WITCH_HAT = Registration.ITEMS.register("witch_hat", () ->
            new WitchHatItem(new WitchHatArmorMaterial(33,4,0,0), EquipmentSlotType.HEAD,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    static void register() {}

}
