package net.baloby.mcrpg.entities.models;// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.WitchEntity;

public class VilerWitchModel<T extends WitchEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer scarf;
	private final ModelRenderer nose;
	private final ModelRenderer hat;
	private final ModelRenderer hat2;
	private final ModelRenderer hat2_r1;
	private final ModelRenderer hat3;
	private final ModelRenderer hat3_r1;
	private final ModelRenderer hat4;
	private final ModelRenderer hat4_r1;
	private final ModelRenderer body;
	private final ModelRenderer arms;
	private final ModelRenderer right_shoulder_r1;
	private final ModelRenderer left_arm_r1;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public VilerWitchModel() {
		texWidth = 64;
		texHeight = 128;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		scarf = new ModelRenderer(this);
		scarf.setPos(0.0F, 24.0F, 0.0F);
		head.addChild(scarf);
		scarf.texOffs(0, 37).addBox(-4.5F, -26.9F, -4.5F, 9.0F, 5.0F, 9.0F, 0.0F, false);

		nose = new ModelRenderer(this);
		nose.setPos(0.0F, 24.0F, 0.0F);
		head.addChild(nose);
		nose.texOffs(24, 0).addBox(-1.0F, -25.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		nose.texOffs(0, 0).addBox(-1.0F, -24.0F, -6.75F, 1.0F, 1.0F, 1.0F, -0.25F, false);

		hat = new ModelRenderer(this);
		hat.setPos(-5.0F, -8.0313F, -5.0F);
		head.addChild(hat);
		hat.texOffs(5, 70).addBox(-5.0F, -0.9688F, -4.0F, 20.0F, 3.0F, 19.0F, 0.0F, false);

		hat2 = new ModelRenderer(this);
		hat2.setPos(6.75F, 0.0313F, 7.0F);
		hat.addChild(hat2);
		

		hat2_r1 = new ModelRenderer(this);
		hat2_r1.setPos(-1.75F, 32.0F, -2.0F);
		hat2.addChild(hat2_r1);
		setRotationAngle(hat2_r1, -0.0436F, 0.0F, 0.0F);
		hat2_r1.texOffs(0, 51).addBox(-3.25F, -36.5F, -5.0F, 7.0F, 4.0F, 7.0F, 0.0F, false);

		hat3 = new ModelRenderer(this);
		hat3.setPos(0.0F, -3.0F, 0.0F);
		hat2.addChild(hat3);
		

		hat3_r1 = new ModelRenderer(this);
		hat3_r1.setPos(-1.75F, 35.0F, -2.0F);
		hat3.addChild(hat3_r1);
		setRotationAngle(hat3_r1, -0.0873F, 0.0F, 0.0F);
		hat3_r1.texOffs(0, 62).addBox(-1.5F, -39.0F, -5.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		hat4 = new ModelRenderer(this);
		hat4.setPos(0.0F, -3.0F, 0.0F);
		hat3.addChild(hat4);
		

		hat4_r1 = new ModelRenderer(this);
		hat4_r1.setPos(-1.75F, 38.0F, -2.0F);
		hat4.addChild(hat4_r1);
		setRotationAngle(hat4_r1, -0.1745F, 0.0F, 0.1309F);
		hat4_r1.texOffs(16, 67).addBox(-4.75F, -40.0F, -6.5F, 1.0F, 2.0F, 1.0F, 0.25F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(16, 19).addBox(-4.0F, -24.0F, -2.5F, 8.0F, 12.0F, 6.0F, 0.0F, false);
		body.texOffs(30, 44).addBox(-5.0F, -23.0F, -3.0F, 10.0F, 19.0F, 7.0F, 0.0F, false);
		body.texOffs(0, 100).addBox(-5.5F, -15.0F, -3.5F, 11.0F, 3.0F, 8.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setPos(0.0F, 2.0F, 0.0F);
		setRotationAngle(arms, -0.1745F, 0.0F, 0.0F);
		

		right_shoulder_r1 = new ModelRenderer(this);
		right_shoulder_r1.setPos(0.0F, 22.0F, 0.0F);
		arms.addChild(right_shoulder_r1);
		setRotationAngle(right_shoulder_r1, -0.7854F, 0.0F, 0.0F);
		right_shoulder_r1.texOffs(32, 0).addBox(-9.0F, -18.0F, -17.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		right_shoulder_r1.texOffs(42, 11).addBox(4.0F, -18.0F, -17.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);

		left_arm_r1 = new ModelRenderer(this);
		left_arm_r1.setPos(0.0F, 24.0F, 4.0F);
		arms.addChild(left_arm_r1);
		setRotationAngle(left_arm_r1, -0.7854F, 0.0F, 0.0F);
		left_arm_r1.texOffs(44, 22).addBox(4.0F, -14.0F, -20.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		left_arm_r1.texOffs(44, 22).addBox(-8.0F, -14.0F, -20.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);
		left_arm_r1.texOffs(40, 34).addBox(-4.0F, -10.0F, -20.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-2.0F, 12.0F, 0.0F);
		right_leg.texOffs(0, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(2.0F, 12.0F, 0.0F);
		left_leg.texOffs(0, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(WitchEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		arms.render(matrixStack, buffer, packedLight, packedOverlay);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}