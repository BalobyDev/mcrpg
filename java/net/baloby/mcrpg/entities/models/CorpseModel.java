package net.baloby.mcrpg.entities.models;

import net.baloby.mcrpg.entities.CorpseEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CorpseModel extends BipedModel<CorpseEntity> {


    public CorpseModel() {
        super(0.0f);
        setRotationAngle(body, -0.1745F, 0.0F, 0.0F);
        setRotationAngle(head, 0.8727F, -0.1047F, 0.2269F);
        this.rightArm = new ModelRenderer(this, 40, 16);
        this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, false);
        this.rightArm.setPos(-5.0F, 12.0F, 0.0F);
        setRotationAngle(rightArm, 0.3054F, 0.0F, 0.0F);
        this.leftArm = new ModelRenderer(this, 40, 16);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, false);
        this.leftArm.setPos(5.0F, 12.0F, 0.0F);
        setRotationAngle(leftArm, 0.1765F, 0.0033F, 0.0124F);
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, false);
        this.rightLeg.setPos(-2.0F, 22.0F, 0.0F);
        setRotationAngle(rightLeg, -1.922F, -0.1264F, -2.3221F);
        this.leftLeg = new ModelRenderer(this, 0, 16);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, false);
        this.leftLeg.setPos(2.0F, 22.0F, 0.0F);
        setRotationAngle(leftLeg, -1.1986F, -0.1726F, -0.7232F);
        this.head.setPos(0,10,0);
        this.body.setPos(0,10,0);


    }

    @Override
    public void prepareMobModel(CorpseEntity entity, float x, float y, float z) {
//        this.rightArm.setPos(0,12,0);

        super.prepareMobModel(entity, x, y, z);
    }

    @Override
    public void setupAnim(CorpseEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
