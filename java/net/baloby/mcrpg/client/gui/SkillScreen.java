package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.Element;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

public class SkillScreen extends Screen {
    private static final ResourceLocation GUI = new ResourceLocation("textures/gui/demo_background.png");
    private int WIDTH = 248;
    private int HEIGHT = 166;
    private Unit unit;
    private ArrayList<Move> moveSet;
    protected SkillScreen(Unit unit) {
        super(new StringTextComponent("shows skills"));
        this.unit = unit;
        this.moveSet = unit.moveSet;

    }

    private void createButton(int num){
        int i = (this.width - WIDTH)/20+((num+2)%2*82);
        int j = (this.height - HEIGHT)/2+85+((num+2)/2*22);
        ITextComponent txt = num >= moveSet.size() ?  new StringTextComponent("-") : new StringTextComponent(moveSet.get(num).name.getString()+"   MP: "+(int)moveSet.get(num).cost);
        Runnable runnable = ()->BattleGui.open();
        if(num<moveSet.size()){
            Move move = moveSet.get(num);
            runnable = ()->UnitSelectionScreen.open(move.type == Element.SUPPORT? unit.party : unit.battle.enemyParty,move);}
        Runnable finalRunnable = runnable;
        Button skillButton = addButton(new Button(i+5,j,80,20,txt, button-> {
            finalRunnable.run();}));
        if(num>=moveSet.size()||moveSet.get(num).cost>unit.MP){
            skillButton.active = false;
        }


    }

    protected void init(){
        int i = (this.width - WIDTH)/20;
        int j = (this.height - HEIGHT)/2+105;
        Button x = addButton(new Button(i+WIDTH-45,j,40,20,new StringTextComponent("Back"),button->BattleGui.open()));
        for (int k = 0; k<8;k++){
            createButton(k);

        }

    }

    @Override
    public boolean shouldCloseOnEsc(){return false;}

    @Override
    public boolean isPauseScreen(){return false;}

    public void render(MatrixStack matrixStack, int mouseX, int mouseY,float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = (this.width - WIDTH)/20;
        int j = (this.height - HEIGHT)/2+100;
        this.blit(matrixStack, i,j+5,0,62,WIDTH,HEIGHT);
        this.blit(matrixStack, i,j,0,0,WIDTH,HEIGHT);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public static void open(Unit unit){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new SkillScreen(unit));});}
}
