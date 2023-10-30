package net.baloby.mcrpg.setup.Items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class WitchHatArmorMaterial implements IArmorMaterial {

    private int durability;
    private int defense;
    private int poise;
    private int weight;

    public WitchHatArmorMaterial(int durability, int defense, int poise, int weight){
        this.durability = durability;
        this.defense = defense;
        this.poise = poise;
        this.weight = weight;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType equipmentSlotType) {
        return durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType equipmentSlotType) {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "witch_hat";
    }

    @Override
    public float getToughness() {
        return defense;
    }

    @Override
    public float getKnockbackResistance() {
        return poise;
    }
}
