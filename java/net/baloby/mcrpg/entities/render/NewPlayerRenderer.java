package net.baloby.mcrpg.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public class NewPlayerRenderer extends LivingRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public NewPlayerRenderer(EntityRendererManager manager) {
        this(manager,true);
    }

    public NewPlayerRenderer(EntityRendererManager p_i46103_1_, boolean p_i46103_2_) {
        super(p_i46103_1_, new PlayerModel<>(0.0F, p_i46103_2_), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new ArrowLayer<>(this));
        this.addLayer(new Deadmau5HeadLayer(this));
        this.addLayer(new CapeLayer(this));
        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new ParrotVariantLayer<>(this));
        this.addLayer(new SpinAttackEffectLayer<>(this));
        this.addLayer(new BeeStingerLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayerEntity clientPlayerEntity){
        return new ResourceLocation(mcrpg.MODID, "textures/entity/camryn.png");
    }

    public void render(AbstractClientPlayerEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_){
        this.setModelProperties(p_225623_1_);
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);

    }

    public Vector3d getRenderOffset(AbstractClientPlayerEntity p_225627_1_, float p_225627_2_) {
        return p_225627_1_.isCrouching() ? new Vector3d(0.0D, -0.125D, 0.0D) : super.getRenderOffset(p_225627_1_, p_225627_2_);
    }

    protected void scale(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float num){
        super.scale(entity,matrixStack,num);
        float f = 0.9375F;
        matrixStack.scale(f,f,f);
    }

    private void setModelProperties(AbstractClientPlayerEntity p_177137_1_) {
        PlayerModel<AbstractClientPlayerEntity> playermodel = this.getModel();
        if (p_177137_1_.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.hat.visible = p_177137_1_.isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = p_177137_1_.isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = p_177137_1_.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = p_177137_1_.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = p_177137_1_.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = p_177137_1_.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
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

    public void renderRightHand(MatrixStack p_229144_1_, IRenderTypeBuffer p_229144_2_, int p_229144_3_, AbstractClientPlayerEntity p_229144_4_) {
        this.renderHand(p_229144_1_, p_229144_2_, p_229144_3_, p_229144_4_, (this.model).rightArm, (this.model).rightSleeve);
    }

    public void renderLeftHand(MatrixStack p_229146_1_, IRenderTypeBuffer p_229146_2_, int p_229146_3_, AbstractClientPlayerEntity p_229146_4_) {
        this.renderHand(p_229146_1_, p_229146_2_, p_229146_3_, p_229146_4_, (this.model).leftArm, (this.model).leftSleeve);
    }

    private void renderHand(MatrixStack p_229145_1_, IRenderTypeBuffer p_229145_2_, int p_229145_3_, AbstractClientPlayerEntity p_229145_4_, ModelRenderer p_229145_5_, ModelRenderer p_229145_6_) {
        PlayerModel<AbstractClientPlayerEntity> playermodel = this.getModel();
        this.setModelProperties(p_229145_4_);
        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(p_229145_4_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        p_229145_5_.xRot = 0.0F;
        p_229145_5_.render(p_229145_1_, p_229145_2_.getBuffer(RenderType.entitySolid(p_229145_4_.getSkinTextureLocation())), p_229145_3_, OverlayTexture.NO_OVERLAY);
        p_229145_6_.xRot = 0.0F;
        p_229145_6_.render(p_229145_1_, p_229145_2_.getBuffer(RenderType.entityTranslucent(p_229145_4_.getSkinTextureLocation())), p_229145_3_, OverlayTexture.NO_OVERLAY);
    }

    private static BipedModel.ArmPose getArmPose(AbstractClientPlayerEntity p_241741_0_, Hand p_241741_1_) {
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
