package net.baloby.mcrpg.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.baloby.mcrpg.entities.custom.enemies.CultistEntity;
import net.baloby.mcrpg.entities.models.HumanoidModel;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;

public class CodRenderer extends BipedRenderer<CultistEntity, HumanoidModel<CultistEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(mcrpg.MODID,"textures/entity/cult_member.png");

    public CodRenderer(EntityRendererManager manager) {
        super(manager, new HumanoidModel<>(0,true), 1.0f);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CultistEntity entity) {
        return TEXTURE;
    }


    public void render(CultistEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_){
        this.setModelProperties(p_225623_1_);
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);

    }





    private void setModelProperties(CultistEntity p_177137_1_) {
        HumanoidModel<CultistEntity> playermodel = this.getModel();
        if (p_177137_1_.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.crouching = p_177137_1_.isCrouching();
            BipedModel.ArmPose bipedmodel$armpose = getArmPose(p_177137_1_, Hand.MAIN_HAND);
            BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(p_177137_1_, Hand.OFF_HAND);
            if (bipedmodel$armpose.isTwoHanded()) {
                bipedmodel$armpose1 = p_177137_1_.getOffhandItem().isEmpty() ? BipedModel.ArmPose.EMPTY : BipedModel.ArmPose.ITEM;
            }

            if (p_177137_1_.getMainArm() == HandSide.RIGHT) {
                playermodel.rightArmPose = bipedmodel$armpose;
                playermodel.leftArmPose = bipedmodel$armpose1;
            } else {
                playermodel.rightArmPose = bipedmodel$armpose1;
                playermodel.leftArmPose = bipedmodel$armpose;
            }
        }

    }

    private static BipedModel.ArmPose getArmPose(CultistEntity p_241741_0_, Hand p_241741_1_) {
        ItemStack itemstack = p_241741_0_.getItemInHand(p_241741_1_);
        if (itemstack.isEmpty()) {
            return BipedModel.ArmPose.EMPTY;
        } else {
            if (p_241741_0_.getUsedItemHand() == p_241741_1_ && p_241741_0_.getUseItemRemainingTicks() > 0) {
                UseAction useaction = itemstack.getUseAnimation();
                if (useaction == UseAction.BLOCK) {
                    return BipedModel.ArmPose.BLOCK;
                }

                if (useaction == UseAction.BOW) {
                    return BipedModel.ArmPose.BOW_AND_ARROW;
                }

                if (useaction == UseAction.SPEAR) {
                    return BipedModel.ArmPose.THROW_SPEAR;
                }

                if (useaction == UseAction.CROSSBOW && p_241741_1_ == p_241741_0_.getUsedItemHand()) {
                    return BipedModel.ArmPose.CROSSBOW_CHARGE;
                }
            } else if (!p_241741_0_.swinging && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
                return BipedModel.ArmPose.CROSSBOW_HOLD;
            }

            return BipedModel.ArmPose.ITEM;
        }
    }
}
