package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.custom.enemies.FairyEntity;
import net.baloby.mcrpg.entities.models.FairyModel;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class FairyRenderer extends BipedRenderer<FairyEntity, FairyModel> {

    public FairyRenderer(EntityRendererManager p_i46168_1_) {
        super(p_i46168_1_, new FairyModel(0), 0);
    }

    @Override
    public ResourceLocation getTextureLocation(FairyEntity entity) {
        return entity.getSkin();
    }
}
