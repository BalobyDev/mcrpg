package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.custom.partyMembers.RanaEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class RanaRenderer extends MobRenderer<RanaEntity, PlayerModel<RanaEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(mcrpg.MODID,"textures/entity/rana.png");

    public RanaRenderer(EntityRendererManager manager) {
        super(manager, new PlayerModel(0,true), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(RanaEntity entity) {
        return TEXTURE;
    }
}
