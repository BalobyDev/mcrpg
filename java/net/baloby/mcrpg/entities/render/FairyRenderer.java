package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.custom.enemies.PixieEntity;
import net.baloby.mcrpg.entities.models.FairyModel;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class FairyRenderer extends BipedRenderer<PixieEntity, FairyModel> {

    public FairyRenderer(EntityRendererManager p_i46168_1_) {
        super(p_i46168_1_, new FairyModel(0), 0);
    }

    @Override
    public ResourceLocation getTextureLocation(PixieEntity entity) {
        return entity.getSkin();
    }
}
