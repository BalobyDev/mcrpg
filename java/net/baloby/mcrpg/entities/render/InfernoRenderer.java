package net.baloby.mcrpg.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.entities.custom.enemies.InfernoEntity;
import net.baloby.mcrpg.entities.models.InfernoModel;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class InfernoRenderer extends MobRenderer<InfernoEntity,InfernoModel<InfernoEntity>> {

    private static ResourceLocation TEXTURE = new ResourceLocation(mcrpg.MODID,"textures/entity/inferno.png");

    public InfernoRenderer(EntityRendererManager p_i50961_1_) {
        super(p_i50961_1_, new InfernoModel<>(), 1.5f);
    }


    @Override
    public ResourceLocation getTextureLocation(InfernoEntity p_110775_1_) {
        return TEXTURE;
    }

    protected void scale(InfernoEntity entity, MatrixStack matrixStack, float num){
        super.scale(entity,matrixStack,1.5f);
    }
}
