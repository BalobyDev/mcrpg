package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.baloby.mcrpg.data.characters.*;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.entities.models.HumanoidModel;
import net.baloby.mcrpg.entities.render.HumanoidRenderer;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BookModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;

import java.util.Optional;

public class EquipScreen extends ContainerScreen<NpcContainer> {

    private EntityModel model;
    private EntityRenderer renderer;
    private BattleNpc npc;
    private HumanoidEntity entity;
    private static final ResourceLocation ENCHANTING_BOOK_LOCATION = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private static final BookModel BOOK_MODEL = new BookModel();



    public EquipScreen(NpcContainer container, PlayerInventory inventory, ITextComponent textComponent) {
        super(container,inventory,textComponent);
        this.titleLabelX = 100;
//        this.entity = new HumanoidSlimEntity(ModEntities.HUMANOID_SLIM.get(),this.minecraft.level,npc);
//        this.world = new ClientWorld(this.minecraft.getConnection(), this.minecraft.level.dimension(), this.minecraft.level.dimensionType(), 16, ()->minecraft.getProfiler(), minecraft.levelRenderer, false, 0 );
        this.model = new HumanoidModel(0.0F, true);
        this.npc = (BattleNpc) container.getNpc().create();
    }

    @Override
    protected void init() {
        super.init();
        this.npc = (BattleNpc) this.getMenu().getNpc().create();
        this.renderer = new HumanoidRenderer(this.minecraft.getEntityRenderDispatcher(),true);
    }

    protected void renderBg(MatrixStack mStack, float p_230450_2_, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);

        int i = this.leftPos;
        int j = this.topPos;
        this.blit(mStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(mStack,i+75,j+42,75,60,19,19);
        this.blit(mStack,leftPos+97, topPos+5,97,53,75,30);
        this.blit(mStack,leftPos+97, topPos+35,97,53,75,30);
        this.font.draw(mStack,"STR: "+npc.STR,leftPos+100,topPos+25,4210752);
        this.font.draw(mStack,"MAG: "+npc.MAG,leftPos+100,topPos+35,4210752);
        this.font.draw(mStack,"DEF: "+npc.DEF,leftPos+100,topPos+45,4210752);
        this.font.draw(mStack,"SPD: "+npc.ENDURANCE,leftPos+100,topPos+55,4210752);
        this.font.draw(mStack,"LOAD: 00/"+npc.ENDURANCE*2,leftPos+100,topPos+65,4210752);

        this.renderCharacter(mStack, (i + 51) - mouseX, (j + 75 - 50) - mouseY);
    }




    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
        int relX = (this.width - leftPos)/2;
        int relY = (this.height - topPos)/2;

//        this.blit(matrixStack, leftPos, topPos,0,0, imageWidth, imageHeight);

//        this.blit(matrixStack, );
        super.render(matrixStack, mouseX, mouseY, partialTicks);
//        this.renderCharacter(mStack, mouseX, mouseY);


    }

    @Override
    public boolean keyPressed(int key, int a, int b){
//        if(key==256){
//            if(battle.isPresent()) {
//                BattleGui.open();
//                return true;
//            }
//        }
        return super.keyPressed(key, a, b);
    }

    public Optional<LivingEntity> getEntity(){
//        if(battle.isPresent()) {
//            if (!(battle.get().activeUnit instanceof PlayerUnit)) {
//                return Optional.ofNullable(battle.get().activeUnit.entity);
//            }
//        }
        return Optional.ofNullable(this.minecraft.player);
    }

    protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
        this.font.draw(p_230451_1_, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    //    @Override
//    public boolean shouldCloseOnEsc() {
//        return !battle.isPresent();
//    }

    public void renderBook(MatrixStack mStack,float partialTicks){
        RenderSystem.matrixMode(5889);
        RenderSystem.pushMatrix();
        RenderSystem.loadIdentity();
        int k = (int)this.minecraft.getWindow().getGuiScale();
        //IT'S VIEWPORT DUDE!!!! THAT'S THE KEY! WE DID IT!!!!
        RenderSystem.viewport((this.width - 280) / 2 * k, (this.height - 240) / 2 * k, 320 * k, 240 * k);
        RenderSystem.translatef(-0.34F, 0.23F, 0.0F);
        RenderSystem.multMatrix(Matrix4f.perspective(90.0D, 1.3333334F, 9.0F, 80.0F));
        RenderSystem.matrixMode(5888);
        mStack.pushPose();
        MatrixStack.Entry matrixstack$entry = mStack.last();
        matrixstack$entry.pose().setIdentity();
        matrixstack$entry.normal().setIdentity();
        mStack.translate(0.0D, 0.0D, 1984.0D);
        mStack.scale(10.0F, 10.0F, 10.0F);
        mStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        mStack.mulPose(Vector3f.XP.rotationDegrees(200.0F));
        RenderSystem.enableRescaleNormal();
        BOOK_MODEL.setupAnim(0.0F, 0,0,0);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
        IVertexBuilder ivertexbuilder = irendertypebuffer$impl.getBuffer(BOOK_MODEL.renderType(ENCHANTING_BOOK_LOCATION));
        BOOK_MODEL.renderToBuffer(mStack, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        irendertypebuffer$impl.endBatch();
        mStack.popPose();
        RenderSystem.matrixMode(5889);
        RenderSystem.viewport(0, 0, this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
        RenderSystem.popMatrix();
        RenderSystem.matrixMode(5888);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderCharacter(MatrixStack mStack, int mouseX, int mouseY){

//        InventoryScreen.renderEntityInInventory(i + 51, j + 75, 30, (float)(i + 51) - this.xMouse, (float)(j + 75 - 50) - this.yMouse, this.minecraft.player);

        float f = (float)Math.atan((double) (mouseX / 40.0F));
        float f1 = (float)Math.atan((double)(mouseY / 40.0F));

        RenderSystem.matrixMode(5889);
        RenderSystem.pushMatrix();
        RenderSystem.loadIdentity();
        int k = (int)this.minecraft.getWindow().getGuiScale();
        RenderSystem.viewport((this.width - 280) / 2 * k, (this.height - 240) / 2 * k, 320 * k, 240 * k);
        RenderSystem.translatef(-0.34F, 0.17F, 0.0F);
        RenderSystem.multMatrix(Matrix4f.perspective(90.0D, 1.3333334F, 9.0F, 80.0F));
        RenderSystem.matrixMode(5888);
        mStack.pushPose();
        MatrixStack.Entry matrixstack$entry = mStack.last();
        matrixstack$entry.pose().setIdentity();
        matrixstack$entry.normal().setIdentity();
        mStack.translate(-0.4D, (double)3.3F, 1986.0D);
        mStack.scale(3.3F,3.3F,3.3F);
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        mStack.mulPose(quaternion);

        mStack.mulPose(Vector3f.YP.rotationDegrees(180+f*40));
        quaternion1.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion1);
        RenderSystem.enableRescaleNormal();
        if(this.model instanceof HumanoidModel){
            ((HumanoidModel)model).setupAnim(f,0,0,0);
        }
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
        IRenderTypeBuffer.Impl irendertypebuffer$impl1 = Minecraft.getInstance().renderBuffers().bufferSource();
        IVertexBuilder ivertexbuilder = irendertypebuffer$impl.getBuffer(RenderType.entityCutout(npc.getSkin()));
        IVertexBuilder ivertexbuilder1 = irendertypebuffer$impl1.getBuffer(RenderType.entityCutout(npc.getSkin()));
        this.model.renderToBuffer(mStack,ivertexbuilder,15728880, OverlayTexture.NO_OVERLAY,1,1,1,1);
        irendertypebuffer$impl.endBatch();
        entityrenderermanager.setRenderShadow(true);

        mStack.popPose();

        RenderSystem.matrixMode(5889);
        RenderSystem.viewport(0, 0, this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
        RenderSystem.popMatrix();
        RenderSystem.matrixMode(5888);
        RenderHelper.setupFor3DItems();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);


    }

    @Override
    public void tick() {
        super.tick();
        this.npc = (BattleNpc) this.getMenu().getNpc().create();
    }
}
