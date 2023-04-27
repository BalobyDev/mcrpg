package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.HumanoidEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.model.PiglinModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class HumanoidPiglinRenderer extends PiglinRenderer {
    public HumanoidPiglinRenderer(EntityRendererManager p_i232472_1_, boolean p_i232472_2_) {
        super(p_i232472_1_, p_i232472_2_);
    }

    @Override
    public ResourceLocation getTextureLocation(MobEntity entity) {
        ResourceLocation resourceLocation = new ResourceLocation("textures/entity/piglin/piglin.png");
        if(entity instanceof  HumanoidEntity) {
            resourceLocation = ((HumanoidEntity) entity).getResourceLocation();
        }

        return resourceLocation;
    }
}
