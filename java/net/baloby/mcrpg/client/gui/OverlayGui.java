package net.baloby.mcrpg.client.gui;

import com.ibm.icu.impl.Pair;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.NpcUnit;
import net.baloby.mcrpg.battle.Unit.PlayerUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.ailments.PoisonAilment;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.util.Vector;

public class OverlayGui extends AbstractGui implements IRenderable {

    private final ResourceLocation OVERLAY = new ResourceLocation(mcrpg.MODID, "textures/gui/overlay_gui.png");
    private final int WIDTH = 52, HEIGHT=7, BARWIDTH = 50, BARHEIGHT = 4;

    public OverlayGui (){

    }
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event){
        if(Minecraft.getInstance().screen instanceof ShopScreen){
            ShopScreen shopScreen = (ShopScreen) Minecraft.getInstance().screen;

        }
        if(Battle.isActive == false)return;
        Battle battle = Battle.instance;
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.color4f(1,1,1,1);
        for (int i = 0; i<battle.playerParty.members.size(); i++){
            renderBars(event.getMatrixStack(),battle.playerParty.members.get(i));
            renderFace(event.getMatrixStack(),battle.playerParty.members.get(i));
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
            unit.indicator.render(event, stack, OVERLAY, WIDTH, BARWIDTH,HEIGHT,this);
        }
        stack.popPose();
    }

    private void renderBars(MatrixStack matrixStack, Unit unit){
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(OVERLAY);
        float oneUnit = (float)BARWIDTH/unit.MAX_HP;
        int currentHealth = (int)(Math.ceil(oneUnit*unit.HP));
        int currentMp = (int)(Math.ceil(oneUnit*unit.MP));
        int y = unit.party.members.indexOf(unit)*40+15;
        int width = mc.getWindow().getGuiScaledWidth();
        int heightUnit = mc.getWindow().getHeight()/256;
            this.blit(matrixStack, width-55, y + 2, 0, 0, WIDTH, HEIGHT);
            this.blit(matrixStack, width-55, y + 10, 0, 0, WIDTH, HEIGHT);
            if(unit.getAilment()==null) {
                this.blit(matrixStack, width - 55, y + 3, 0, 7, currentHealth + 1, 5);
            }
            else if(unit.getAilment() instanceof PoisonAilment) {
                this.blit(matrixStack, width - 55, y + 3, 0, 17, currentHealth + 1, 5);
            }
            this.blit(matrixStack, width-55, y + 11, 0, 12, currentMp + 1, 5);
            drawString(matrixStack, mc.font, (int) unit.HP + "", width-70, y + 2, 16777215);
            drawString(matrixStack, mc.font, (int) unit.MP + "", width-70, y + 10, 16777215);
    }

    public Matrix4f getMatrix(MatrixStack matStack, LivingEntity entity, float posX, float posY, float posZ){
        Matrix4f m4trix = matStack.last().pose();
        return m4trix;
    }

    public void renderFace(MatrixStack matrixStack, Unit unit){
        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        if(unit instanceof PlayerUnit){
            mc.getTextureManager().bind(new ResourceLocation(mcrpg.MODID,"textures/entity/camryn.png"));
            int y = unit.party.members.indexOf(unit)*40+17;
            if(mc.getWindow().isFullscreen()) {
                this.blit(matrixStack, width-88, y, 16, 16, 16, 16, 128, 128);
            }
            else {
                this.blit(matrixStack, width-88, y, 16, 16, 16, 16, 128, 128);
            }

//            this.blit(matrixStack, 180,y,160,128,128,128,128,128);
        }

        else if(unit instanceof NpcUnit){
            mc.getTextureManager().bind(((NpcUnit) unit).character.getSkin());
            int y = unit.party.members.indexOf(unit)*40+17;
            if(mc.getWindow().isFullscreen()) {
                this.blit(matrixStack, width-88, y, 16, 16, 16, 16, 128, 128);
            }
            else {
                this.blit(matrixStack, width-88, y, 16, 16, 16, 16, 128, 128);
            }
            //this.blit(matrixStack, 400,y-8,160,32,32,32);
        }
    }


    @Override
    public void render(MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        Battle battle = Battle.instance;
        renderBars(matrixStack,battle.playerParty.members.get(0));
    }
}
