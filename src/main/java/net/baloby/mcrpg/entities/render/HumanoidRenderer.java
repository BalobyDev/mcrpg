package net.baloby.mcrpg.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;

public class HumanoidRenderer extends MobRenderer<HumanoidEntity, PlayerModel<HumanoidEntity>> {

    private static final ResourceLocation ALEX = new ResourceLocation("textures/entity/alex.png");
    //private static final ResourceLocation JEAN = new ResourceLocation(mcrpg.MODID,"textures/entity/jean.png");

    private Npc character = Npcs.RANA;

    public HumanoidRenderer(EntityRendererManager manager) {
        super(manager, new PlayerModel(0,true), 1.0f);
        this.addLayer(new HeldItemLayer<>(this));
    }



    @Override
    public ResourceLocation getTextureLocation(HumanoidEntity entity) {
        return entity.getResourceLocation();
    }

    public void renderRightHand(MatrixStack p_229144_1_, IRenderTypeBuffer p_229144_2_, int p_229144_3_, HumanoidEntity p_229144_4_) {
        this.renderHand(p_229144_1_, p_229144_2_, p_229144_3_, p_229144_4_, (this.model).rightArm, (this.model).rightSleeve);
    }

    public void renderLeftHand(MatrixStack p_229146_1_, IRenderTypeBuffer p_229146_2_, int p_229146_3_, HumanoidEntity p_229146_4_) {
        this.renderHand(p_229146_1_, p_229146_2_, p_229146_3_, p_229146_4_, (this.model).leftArm, (this.model).leftSleeve);
    }

    private void renderHand(MatrixStack p_229145_1_, IRenderTypeBuffer p_229145_2_, int p_229145_3_, HumanoidEntity p_229145_4_, ModelRenderer p_229145_5_, ModelRenderer p_229145_6_) {
        PlayerModel<HumanoidEntity> playermodel = this.getModel();
        this.setModelProperties(p_229145_4_);
        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(p_229145_4_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        p_229145_5_.xRot = 0.0F;
        p_229145_5_.render(p_229145_1_, p_229145_2_.getBuffer(RenderType.entitySolid(getTextureLocation(p_229145_4_))), p_229145_3_, OverlayTexture.NO_OVERLAY);
        p_229145_6_.xRot = 0.0F;
        p_229145_6_.render(p_229145_1_, p_229145_2_.getBuffer(RenderType.entityTranslucent(getTextureLocation(p_229145_4_))), p_229145_3_, OverlayTexture.NO_OVERLAY);
    }
    private void setModelProperties(HumanoidEntity p_177137_1_) {
        PlayerModel<HumanoidEntity> humanoidModel = this.getModel();
        if (p_177137_1_.isSpectator()) {
            humanoidModel.setAllVisible(false);
            humanoidModel.head.visible = true;
            humanoidModel.hat.visible = true;
        } else {
            humanoidModel.setAllVisible(true);
            /*humanoidModel.hat.visible = p_177137_1_.isModelPartShown(PlayerModelPart.HAT);
            humanoidModel.jacket.visible = p_177137_1_.isModelPartShown(PlayerModelPart.JACKET);
            humanoidModel.leftPants.visible = p_177137_1_.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            humanoidModel.rightPants.visible = p_177137_1_.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            humanoidModel.leftSleeve.visible = p_177137_1_.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            humanoidModel.rightSleeve.visible = p_177137_1_.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);*/
            humanoidModel.crouching = p_177137_1_.isCrouching();
            BipedModel.ArmPose bipedmodel$armpose = getArmPose(p_177137_1_, Hand.MAIN_HAND);
            BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(p_177137_1_, Hand.OFF_HAND);
            if (bipedmodel$armpose.isTwoHanded()) {
                bipedmodel$armpose1 = p_177137_1_.getOffhandItem().isEmpty() ? BipedModel.ArmPose.EMPTY : BipedModel.ArmPose.ITEM;
            }

            if (p_177137_1_.getMainArm() == HandSide.RIGHT) {
                humanoidModel.rightArmPose = bipedmodel$armpose;
                humanoidModel.leftArmPose = bipedmodel$armpose1;
            } else {
                humanoidModel.rightArmPose = bipedmodel$armpose1;
                humanoidModel.leftArmPose = bipedmodel$armpose;
            }
        }

    }
    private static BipedModel.ArmPose getArmPose(HumanoidEntity p_241741_0_, Hand p_241741_1_) {
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
