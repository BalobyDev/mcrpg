package net.baloby.mcrpg.client.gui.indicator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.client.event.RenderLivingEvent;

public class DmgIndicator extends Indicator {

    public String extra = "";
    public int dmg;
    public int oldHp;
    public DmgIndicator(int dmg, int oldHp) {
        super(dmg+"", 16733525);
        this.dmg = dmg;
        this.oldHp = oldHp;
        this.bar = 7;
    }

    @Override
    public void render(AbstractGui gui, MatrixStack mStack) {
        this.renderBar(gui,mStack);
        super.render(gui,mStack);
        if(matrix4f!=null) {
            mStack.pushPose();
            mStack.last().pose().set(matrix4f.copy());

            Minecraft mc = Minecraft.getInstance();
            mStack.translate(0, this.height, 0);
            mStack.mulPose(renderer.getDispatcher().cameraOrientation());
            mStack.scale(-0.035F, -0.035F, 0.035F);

            float currentHealth = 0;
            if (unit != null) {
                this.oneUnit = 50 / unit.MAX_HP;
                currentHealth = oneUnit * unit.HP;
            }

            RenderSystem.disableDepthTest();
            mc.getTextureManager().bind(OVERLAY);
            gui.blit(mStack, -26,-7, 0, 0, 52, 7);
            gui.blit(mStack, -26, -6, 0, 22, (int) (Math.ceil(oneUnit * getOldHp())) + 1, 5);
            gui.blit(mStack, -26, -6, 0, 7, (int) (currentHealth + 1), 5);
            RenderSystem.enableDepthTest();
            mStack.popPose();
        }

    }

    @Override
    public void renderText(MatrixStack mStack) {
        super.renderText(mStack);
        mStack.pushPose();
        RenderSystem.disableDepthTest();
        mStack.last().pose().set(matrix4f.copy());
        mStack.translate(0, this.height, 0);
        mStack.mulPose(renderer.getDispatcher().cameraOrientation());
        mStack.scale(-0.035F, -0.035F, 0.035F);
        float f2 = (float)(-font.width(extra) / 2);
        IRenderTypeBuffer.Impl buffer = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
        font.drawInBatch(extra ,f2,9,-1,false,mStack.last().pose().copy(), buffer,true,0,160);
        buffer.endBatch();
        RenderSystem.enableDepthTest();
        mStack.popPose();

    }

    @Override
    public int getDamage() {
        return dmg;
    }

    @Override
    public int getOldHp() {
        return oldHp;
    }

    public void tick(){
        super.tick();
        if(ticks>10){
            this.oldHp--;
        }
    }
}
