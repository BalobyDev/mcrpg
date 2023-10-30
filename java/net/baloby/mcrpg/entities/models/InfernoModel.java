package net.baloby.mcrpg.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.baloby.mcrpg.entities.custom.enemies.InfernoEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;

public class InfernoModel<T extends MonsterEntity> extends EntityModel<T> {
	private final ModelRenderer core;
	private final ModelRenderer shield4_r1;
	private final ModelRenderer shield3_r1;
	private final ModelRenderer shield2_r1;

	public InfernoModel() {
		texWidth = 64;
		texHeight = 64;

		core = new ModelRenderer(this);
		core.setPos(0.0F, 24.0F, 0.0F);
		core.texOffs(0, 0).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);
		core.texOffs(40, 0).addBox(-9.0F, -21.0F, -5.0F, 2.0F, 18.0F, 10.0F, 0.0F, false);
		core.texOffs(0, 48).addBox(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		core.texOffs(32, 48).addBox(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, true);

		shield4_r1 = new ModelRenderer(this);
		shield4_r1.setPos(0.0F, 0.0F, 0.0F);
		core.addChild(shield4_r1);
		setRotationAngle(shield4_r1, -3.1416F, 0.0F, 3.1416F);
		shield4_r1.texOffs(40, 0).addBox(-9.0F, -21.0F, -5.0F, 2.0F, 18.0F, 10.0F, 0.0F, false);

		shield3_r1 = new ModelRenderer(this);
		shield3_r1.setPos(0.0F, 0.0F, 0.0F);
		core.addChild(shield3_r1);
		setRotationAngle(shield3_r1, -3.1416F, 1.5708F, 3.1416F);
		shield3_r1.texOffs(40, 0).addBox(-9.0F, -21.0F, -5.0F, 2.0F, 18.0F, 10.0F, 0.0F, false);

		shield2_r1 = new ModelRenderer(this);
		shield2_r1.setPos(0.0F, 0.0F, 0.0F);
		core.addChild(shield2_r1);
		setRotationAngle(shield2_r1, -3.1416F, -1.5708F, 3.1416F);
		shield2_r1.texOffs(40, 0).addBox(-9.0F, -21.0F, -5.0F, 2.0F, 18.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		core.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

}