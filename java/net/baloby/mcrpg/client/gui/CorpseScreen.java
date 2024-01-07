package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.data.CorpseContainer;
import net.baloby.mcrpg.entities.CorpseEntity;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class CorpseScreen extends ContainerScreen<CorpseContainer> implements IHasContainer<CorpseContainer> {

    private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation("textures/gui/container/generic_54.png");



    public CorpseScreen(CorpseContainer container, PlayerInventory playerInv, ITextComponent textComponent) {
        super(container,playerInv, new StringTextComponent("Corpse"));
        this.passEvents = false;
        this.imageHeight = 114 + 4 * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.minecraft.getTextureManager().bind(CONTAINER_BACKGROUND);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, 4 * 18 + 17);
        this.blit(p_230450_1_, i, j + 4 * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}
