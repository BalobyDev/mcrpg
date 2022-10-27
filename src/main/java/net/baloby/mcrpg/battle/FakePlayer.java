package net.baloby.mcrpg.battle;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;

public class FakePlayer extends LivingEntity {
    public float yHeadRot = 0;
    public float yBodyRot = 0;

    public FakePlayer(EntityType<? extends LivingEntity> type, ServerWorld world) {
        super(type,world);
        onAddedToWorld();
    }


    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }


    @Override
    public ItemStack getItemBySlot(EquipmentSlotType p_184582_1_) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {

    }

    @Override
    public HandSide getMainArm() {
        return null;
    }

    @Override
    public void tick(){return;}
}
