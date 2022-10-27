package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.util.ResourceLocation;

public class NewPlayerRenderer extends PlayerRenderer {

    public NewPlayerRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayerEntity player){
        return new ResourceLocation(mcrpg.MODID,"textures/entity/cam.png");

    }
}
