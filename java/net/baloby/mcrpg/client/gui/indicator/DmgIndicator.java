package net.baloby.mcrpg.client.gui.indicator;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.client.event.RenderLivingEvent;

public class DmgIndicator extends Indicator {

    public String extra = "";
    public DmgIndicator(int dmg) {
        super(dmg+"", 16733525);
    }

    @Override
    public void render(RenderLivingEvent.Post event, MatrixStack stack, ResourceLocation OVERLAY, int WIDTH, int BARWIDTH, int HEIGHT, OverlayGui gui) {
        super.render(event, stack, OVERLAY, WIDTH, BARWIDTH, HEIGHT, gui);
        float f2 = (float)(-font.width(extra) / 2);
        Matrix4f matrix4f = stack.last().pose();
        font.drawInBatch(extra ,f2,10,-1,false,matrix4f,event.getBuffers(),true,0,160);
    }
}
