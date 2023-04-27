package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.data.dialouge.DialogueInstance;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ShowStatsScreen extends Screen {

    private static final ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/stats.png");
    private int WIDTH = 256;
    private int HEIGHT = 207;
    private Unit unit;
    protected ShowStatsScreen(Unit unit) {
        super(new StringTextComponent("Look at this stuff lmao"));
        this.unit = unit;
    }

    protected void init(){
        super.init();
    }

    public void render(MatrixStack stack,int mouseX, int mouseY, float partialTicks){
        int relX = this.width/2-WIDTH/2;
        int relY = this.height/2-HEIGHT/2;
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(stack,this.width/2-WIDTH/2,this.height/2-HEIGHT/2,0,0,WIDTH,HEIGHT);
        this.renderEntity(relX+WIDTH-50,this.height/2+50,60,mouseX,mouseY);
        this.font.draw(stack,unit.name,relX+10, relY+10,5592405);
        int k = 0;
        for(Element element : Element.values()){
            if(element.equals(Element.SUPPORT))return;
            this.minecraft.getTextureManager().bind(element.getIcon());
            this.blit(stack, relX+10+(k*20), relY+30, 0,0,16,16,16,16);
            this.drawCenteredString(stack,font,unit.affinities.get(element).getText(),relX+18+(k*20)+unit.affinities.get(element).getText().length()/2,relY+50,-1);
            k+=1;

        }
        super.render(stack,mouseX,mouseY,partialTicks);
    }

    public void renderEntity(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_){
        LivingEntity entity = unit.entity;
        float f = 1;
        float f1 = -0.5f;
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        matrixstack.mulPose(quaternion);
        float f2 = entity.yBodyRot;
        float f3 = entity.yRot;
        float f4 = entity.xRot;
        float f5 = entity.yHeadRotO;
        float f6 = entity.yHeadRot;
        entity.yBodyRot = 180.0F + f * 20.0F;
        entity.yRot = 180.0F + f * 40.0F;
        entity.xRot = -f1 * 20.0F;
        entity.yHeadRot = entity.yRot;
        entity.yHeadRotO = entity.yRot;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.endBatch();
        entityrenderermanager.setRenderShadow(true);
        entity.yBodyRot = f2;
        entity.yRot = f3;
        entity.xRot = f4;
        entity.yHeadRotO = f5;
        entity.yHeadRot = f6;
        RenderSystem.popMatrix();
    }

    @Override
    public boolean keyPressed(int key, int p_231046_2_, int p_231046_3_) {
        if(key == 256){
            unit.battle.camera.setBehind();
            BattleGui.open();
            return true;
        }
        return super.keyPressed(key,p_231046_2_,p_231046_3_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public static void open(Unit unit){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(()->{mc.setScreen(new ShowStatsScreen(unit));});
    }
}
