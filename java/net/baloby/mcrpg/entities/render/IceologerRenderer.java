package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.custom.enemies.IceologerEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;

public class IceologerRenderer extends IllagerRenderer<IceologerEntity>  {

    private static final ResourceLocation TEXTURE = new ResourceLocation(mcrpg.MODID,"textures/entity/iceologer.png");

    public IceologerRenderer(EntityRendererManager manager) {
        super(manager, new IllagerModel<IceologerEntity>(0,0,64,64), 0.5f);
    }


    @Override
    public ResourceLocation getTextureLocation(IceologerEntity entity) {
        return TEXTURE;
    }
}
