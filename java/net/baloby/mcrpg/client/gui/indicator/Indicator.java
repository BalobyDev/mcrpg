package net.baloby.mcrpg.client.gui.indicator;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.OverlayGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderLivingEvent;

public class Indicator {
    public ITextComponent text;
    public FontRenderer font;
    public Unit unit;
    public int color = -1;
    public ITextComponent extra;
    public boolean showHp;
    public Indicator(String text){
        this.text = new StringTextComponent(text);
        this.font = Minecraft.getInstance().font;
    }
    public Indicator(String text,int color){
        this.text = new StringTextComponent(text);
        this.font = Minecraft.getInstance().font;
        this.color = color;
    }
    public Indicator(String text,String extra, int color){}
    public static Indicator dmgIndicator(int dmg){return new Indicator(dmg+"",16733525);}
    public static Indicator missIndicator(){return new Indicator("MISS!");}
    public static Indicator blockIndicator(){return new Indicator("BLOCK!");}
    public static Indicator healIndicator(int hp){return new Indicator(hp+"",5635925);}
    public static Indicator mpIndicator(int mp){return new Indicator(mp+"",5636095);}

    public void render(RenderLivingEvent.Post event, MatrixStack stack, ResourceLocation OVERLAY, int WIDTH, int BARWIDTH, int HEIGHT, OverlayGui gui){
        Minecraft mc = Minecraft.getInstance();
        stack.translate(0.0D, (double)unit.entity.getBbHeight(), 0.0D);
        stack.mulPose(event.getRenderer().getDispatcher().cameraOrientation());
        stack.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = stack.last().pose();
        FontRenderer font = this.font;
        float f2 = (float)(-font.width(text) / 2);
        font.drawInBatch(text,f2,0,color,false,matrix4f,event.getBuffers(),true,0,160);
        Vector4f pos = new Vector4f(0,0,0,1);
        pos.transform(matrix4f);
        float x = (pos.x()+1)/100*mc.getWindow().getGuiScaledWidth();
        float y = (1-pos.y())/100*mc.getWindow().getGuiScaledHeight();
        mc.getTextureManager().bind(OVERLAY);
        float oneUnit = (float)BARWIDTH/unit.MAX_HP;
        int currentHealth = (int)(Math.ceil(oneUnit*unit.HP));
        gui.blit(stack, (int) x-26, (int) y+20,0,0,WIDTH,HEIGHT);
        gui.blit(stack, (int) x-26, (int) y+21,0,7,currentHealth,5);
    }
}

