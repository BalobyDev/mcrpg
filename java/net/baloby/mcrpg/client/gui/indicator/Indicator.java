package net.baloby.mcrpg.client.gui.indicator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderLivingEvent;


public class Indicator {

    protected final ResourceLocation OVERLAY = new ResourceLocation(mcrpg.MODID, "textures/gui/overlay_gui.png");
    public ITextComponent text;
    public FontRenderer font;
    public Unit unit;
    public int color = -1;
    public boolean showHp;
    public float oneUnit;
    protected LivingRenderer renderer;
    public AbstractGui gui;
    public Matrix3f matrix3f;
    public Matrix4f matrix4f;
    public int ticks;
    public int bar;
    public double height;

    public Indicator(String text){
        this.text = new StringTextComponent(text);
        this.font = Minecraft.getInstance().font;
    }
    public Indicator(String text, int color){
        this(text);
        this.color = color;
    }

    public Indicator(String text,String extra, int color){}
    public static Indicator dmgIndicator(int dmg){return new Indicator(dmg+"",16733525);}
    public static Indicator missIndicator(){return new Indicator("MISS!");}
    public static Indicator blockIndicator(){return new Indicator("BLOCK!");}
    public static Indicator healIndicator(int hp){return new Indicator(hp+"",5635925);}
    public static Indicator mpIndicator(int mp){return new Indicator(mp+"",5636095);}


    public void setup(RenderLivingEvent.Post event){

        this.matrix3f = event.getMatrixStack().last().normal().copy();
        this.matrix4f = event.getMatrixStack().last().pose().copy();
        this.renderer = event.getRenderer();
        this.height = event.getEntity().getBbHeight() / 2;

    }
    public void render(AbstractGui gui, MatrixStack mStack){
        if(showHp) {
            this.renderBar(gui, mStack);
        }
        this.renderText(mStack);
    }

    public void renderText(MatrixStack mStack){
        assert (matrix4f!=null);
        mStack.pushPose();
        RenderSystem.disableDepthTest();
        mStack.last().pose().set(matrix4f.copy());
        mStack.translate(0, this.height, 0);
        mStack.mulPose(renderer.getDispatcher().cameraOrientation());
        mStack.scale(-0.035F, -0.035F, 0.035F);
        float f2 = (float)(-this.font.width(text) / 2);
        IRenderTypeBuffer.Impl buffer = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
        this.font.drawInBatch(text,f2,1,color,false,mStack.last().pose().copy(),buffer,true,0,160);
        buffer.endBatch();
        RenderSystem.enableDepthTest();
        mStack.popPose();

    }

    public void renderBar(AbstractGui gui, MatrixStack mStack){
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



    public void tick(){
        ticks++;
    }

    public int getDamage(){
        return 0;
    }

    public int getOldHp() {
        return 0;
    }

}

