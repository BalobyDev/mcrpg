package net.baloby.mcrpg.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class corpse extends EntityModel<Entity> {
	private final ModelRenderer waist;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer hat;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightItem;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftItem;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public corpse() {
		texWidth = 64;
		texHeight = 32;

		waist = new ModelRenderer(this);
		waist.setPos(0.0F, 12.0F, 6.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -1.0F, 0.0F);
		waist.addChild(body);
		setRotationAngle(body, -0.1745F, 0.0F, 0.0F);
		body.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		setRotationAngle(head, 0.8727F, -0.1047F, 0.2269F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(hat);
		hat.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-5.0F, 2.0F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.3054F, 0.0F, 0.0F);
		rightArm.texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		rightItem = new ModelRenderer(this);
		rightItem.setPos(-1.0F, 7.0F, 1.0F);
		rightArm.addChild(rightItem);
		

		leftArm = new ModelRenderer(this);
		leftArm.setPos(5.0F, 2.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.1765F, 0.0033F, 0.0124F);
		leftArm.texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);

		leftItem = new ModelRenderer(this);
		leftItem.setPos(1.0F, 7.0F, 1.0F);
		leftArm.addChild(leftItem);
		

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-2.0F, 12.0F, 0.0F);
		body.addChild(rightLeg);
		setRotationAngle(rightLeg, -1.922F, -0.1264F, -2.3221F);
		rightLeg.texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(2.0F, 12.0F, 0.0F);
		body.addChild(leftLeg);
		setRotationAngle(leftLeg, -1.1986F, -0.1726F, -0.7232F);
		leftLeg.texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		waist.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}