package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.models.VilerWitchModel;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.util.ResourceLocation;

public class VilerWitchRenderer extends MobRenderer<WitchEntity,VilerWitchModel<WitchEntity>> {

    public static final ResourceLocation texture = new ResourceLocation(mcrpg.MODID, "textures/entity/viler_witch.png");

    public VilerWitchRenderer(EntityRendererManager p_i50961_1_) {
        super(p_i50961_1_, new VilerWitchModel<>(), 0.5f);
    }


    @Override
    public ResourceLocation getTextureLocation(WitchEntity p_110775_1_) {
        return texture;

    }
}
