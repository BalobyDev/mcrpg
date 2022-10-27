package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.custom.partyMembers.SteveEntity;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class SteveRenderer extends MobRenderer<SteveEntity, PlayerModel<SteveEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png");

    public SteveRenderer(EntityRendererManager manager) {
        super(manager, new PlayerModel(0,false), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(SteveEntity entity) {
        return TEXTURE;
    }
}
