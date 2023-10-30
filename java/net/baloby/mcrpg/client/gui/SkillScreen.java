package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

public class SkillScreen extends Screen {
    private static final ResourceLocation GUI = new ResourceLocation("textures/gui/demo_background.png");
    private ResourceLocation MOVEBAR = new ResourceLocation(mcrpg.MODID, "textures/gui/move_bar.png");
    private int WIDTH = 248;
    private int HEIGHT = 166;
    private int MOVE_BAR_WIDTH = 89;
    private int MOVE_BAR_HEIGHT = 25;
    private Unit unit;
    private Move hovered;
    private ArrayList<Move> moveSet;
    protected SkillScreen(Unit unit) {
        super(new StringTextComponent("shows skills"));
        this.unit = unit;
        this.moveSet = unit.moveSet;

    }

    private void createButton(int num,MatrixStack stack){
        int i = (this.width - WIDTH)/20;
        int j = (this.height - HEIGHT)/2+95+(num*28);
        Runnable runnable = ()->BattleGui.open();
        this.minecraft.getTextureManager().bind(MOVEBAR);
        this.blit(stack,i+5,j,0,0,MOVE_BAR_WIDTH-4,MOVE_BAR_HEIGHT);
        this.blit(stack,i+5+MOVE_BAR_WIDTH-4,j,4,0,MOVE_BAR_WIDTH,MOVE_BAR_HEIGHT);
        if(num<moveSet.size()){
            Move move = moveSet.get(num);
            this.minecraft.getTextureManager().bind(move.type.getIcon());
            this.blit(stack,i+10,j+5,0,0,16,16,16,16);
            this.font.draw(stack,move.name,i+30,j+9,move.cost<unit.MP? -1 : 16733525);
            this.font.draw(stack,(int)move.cost+" MP",i+135,j+9,move.cost<unit.MP? -1 : 16733525);
            runnable = ()->UnitSelectionScreen.open(move.friendly ? unit.party : unit.battle.enemyParty,move);}
        else {
            this.font.draw(stack,"-",i+30,j+9,-1);
        }
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if(hovered!=null){
            UnitSelectionScreen.open(hovered.friendly ? unit.party : unit.battle.enemyParty,hovered);
        }
        return super.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    protected void init(){
        int i = (this.width - WIDTH)/20;
        int j = (this.height - HEIGHT)/2+95;
        Button x = addButton(new Button(i+WIDTH-45,j,40,20,new StringTextComponent("Back"),button->BattleGui.open()));

    }

    @Override
    public boolean keyPressed(int key, int p_231046_2_, int p_231046_3_) {
        if(key == 256){
            BattleGui.open();
            return true;
        }
        return super.keyPressed(key, p_231046_2_, p_231046_3_);
    }

    @Override
    public boolean shouldCloseOnEsc(){return false;}

    @Override
    public boolean isPauseScreen(){return false;}

    public void render(MatrixStack matrixStack, int mouseX, int mouseY,float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = (this.width - WIDTH)/20;
        int j = (this.height - HEIGHT)/2+90;
//        this.blit(matrixStack, i,j+5,0,62,WIDTH,HEIGHT);
        this.blit(matrixStack, i,j,0,0,WIDTH,HEIGHT);
        for (int k = 0; k<4;k++){
            createButton(k,matrixStack);
        }
        this.setHovered(mouseX,mouseY);
        if(this.hovered!=null){
            this.minecraft.getTextureManager().bind(MOVEBAR);
            int y = moveSet.indexOf(hovered);
            this.blit(matrixStack,i+5,j+5+y*28,0,MOVE_BAR_HEIGHT,MOVE_BAR_WIDTH-4,MOVE_BAR_HEIGHT*2);
            this.blit(matrixStack,i+5+MOVE_BAR_WIDTH-4,j+5+y*28,4,MOVE_BAR_HEIGHT,MOVE_BAR_WIDTH,MOVE_BAR_HEIGHT*2);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    protected void setHovered(int x, int y){
        int i = (this.width - WIDTH)/20;
        for (int k = 0; k < moveSet.size(); k++) {
            int j = (this.height - HEIGHT)/2+95+(k*28);
            if((x>i&&x<i+MOVE_BAR_WIDTH*2)&&(y>j&&y<j+25)) {
                this.hovered = moveSet.get(k);
                return;
            }
            else {
                this.hovered=null;
            }
        }
    }

    public static void open(Unit unit){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new SkillScreen(unit));});}
}
