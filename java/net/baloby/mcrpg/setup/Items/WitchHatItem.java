package net.baloby.mcrpg.setup.Items;

import net.baloby.mcrpg.entities.models.HumanoidModel;
import net.baloby.mcrpg.entities.models.WitchHatModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class WitchHatItem extends ArmorItem {
    public WitchHatItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        WitchHatModel<LivingEntity> hat = new WitchHatModel<>();
        hat.young = _default.young;
        hat.riding = _default.riding;
        hat.crouching = _default.crouching;
        return (A) hat;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return "mcrpg:textures/models/armor/witch_hat.png";
    }
}
