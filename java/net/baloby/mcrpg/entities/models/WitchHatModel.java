package net.baloby.mcrpg.entities.models;// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class WitchHatModel<T extends LivingEntity> extends BipedModel {
	private final ModelRenderer bb_main;
	private final ModelRenderer purp_r1;
	private final ModelRenderer purp_r1_r1;

	public WitchHatModel() {
		super(1.0f);
		texWidth = 64;
		texHeight = 64;
		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, -8.0F, 0.0F);
		bb_main.texOffs(24, 21).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 0.0F, 10.0F, 0.0F, false);
		bb_main.texOffs(0, 46).addBox(-8.0F, -9.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		bb_main.texOffs(0, 10).addBox(-3.0F, -11.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
		setRotationAngle(bb_main,0,0,0);


		purp_r1 = new ModelRenderer(this);
		purp_r1.setPos(0.0F, -8.0F, 0.0F);
		bb_main.addChild(purp_r1);
		setRotationAngle(purp_r1, -0.3491F, 0.0F, 0.0F);
		purp_r1.texOffs(1, 3).addBox(-1.0F, -4.3F, -2.0F, 2.0F, 3.0F, 2.0F, 0.7F, false);

		purp_r1_r1 = new ModelRenderer(this);
		purp_r1_r1.setPos(0.0F, 0.0F, 0.0F);
		purp_r1.addChild(purp_r1_r1);
		setRotationAngle(purp_r1_r1, -0.3491F, 0.0F, 0.0F);
		purp_r1_r1.texOffs(1, 3).addBox(-1.0F, -7.0F, -3.2F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		this.head = bb_main;
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}