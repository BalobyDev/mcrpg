package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

public class OverlayGui extends AbstractGui implements IRenderable {

    private final ResourceLocation OVERLAY = new ResourceLocation(mcrpg.MODID, "textures/gui/overlay_gui.png");
    private final int WIDTH = 54, HEIGHT=7, BARWIDTH = 50, BARHEIGHT = 4;


    public OverlayGui (){

    }
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event){
        if(Battle.isActive == false)return;
        Battle battle = Battle.instance;
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.color4f(1,1,1,1);
        for (int i = 0; i<battle.playerParty.members.size(); i++){
            renderBars(event.getMatrixStack(),battle.playerParty.members.get(i));
        }
    }

    @SubscribeEvent
    public void renderIndicators(RenderLivingEvent.Post event){
        MatrixStack stack = event.getMatrixStack();
        if(!Battle.isActive) {
            stack.pushPose();
            stack.popPose();
            return;
        }
        stack.pushPose();
        if(Battle.getInstance().entityMap.containsKey(event.getEntity())){
            Unit unit = Battle.instance.entityMap.get(event.getEntity());


            if(unit.indicator==null) {
                stack.popPose();
                return;
            }
            //this needs to be "clear" so we can proceed
            /*if (!(event.getMatrixStack().clear())) {
                stack.popPose();
                return;
            }*/
            event.getMatrixStack().translate(0.0D, (double)event.getEntity().getBbHeight(), 0.0D);
            event.getMatrixStack().mulPose(event.getRenderer().getDispatcher().cameraOrientation());
            event.getMatrixStack().scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = stack.last().pose();
            FontRenderer font = unit.indicator.font;
            float f2 = (float)(-font.width(unit.indicator.text) / 2);
            font.drawInBatch(unit.indicator.text,f2,0,unit.indicator.color,false,matrix4f,event.getBuffers(),true,0,160);

        }
        stack.popPose();
    }

    private void renderBars(MatrixStack matrixStack, Unit unit){
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(OVERLAY);
        float oneUnit = (float)BARWIDTH/unit.MAX_HP;
        int currentHealth = (int)(Math.ceil(oneUnit*unit.HP));
        int currentMp = (int)(Math.ceil(oneUnit*unit.MP));
        int y = unit.party.members.indexOf(unit)*40;
        this.blit(matrixStack,450,y+2,0,0,WIDTH,HEIGHT);
        this.blit(matrixStack,450,y+10,0,0,WIDTH,HEIGHT);
        this.blit(matrixStack, 450,y+3,0,7,currentHealth+1,5);
        this.blit(matrixStack, 450,y+11,0,12, currentMp +1,5);
        drawString(matrixStack,mc.font,(int)unit.HP+"",436,y+2,16777215);
        drawString(matrixStack,mc.font,(int)unit.MP+"",436,y+10,16777215);
    }

    public Matrix4f getMatrix(MatrixStack matStack, LivingEntity entity, float posX, float posY, float posZ){
        Matrix4f m4trix = matStack.last().pose();
        return m4trix;
    }



    @Override
    public void render(MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        Battle battle = Battle.instance;
        renderBars(matrixStack,battle.playerParty.members.get(0));
    }
}
