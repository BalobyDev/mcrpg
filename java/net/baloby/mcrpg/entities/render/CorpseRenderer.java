package net.baloby.mcrpg.entities.render;

import net.baloby.mcrpg.entities.CorpseEntity;
import net.baloby.mcrpg.entities.models.CorpseModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CorpseRenderer extends LivingRenderer<CorpseEntity, CorpseModel> {

    private static final ResourceLocation SKELETON = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public CorpseRenderer(EntityRendererManager p_i50965_1_) {
        super(p_i50965_1_, new CorpseModel(), 0.0F);
        this.addLayer(new PiercedSwordLayer(this));
    }

    @Override
    protected boolean shouldShowName(CorpseEntity p_177070_1_) {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(CorpseEntity p_110775_1_) {
        return SKELETON;
    }

}
