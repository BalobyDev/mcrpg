package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Map;


public class WinScreen extends Screen {
    private int ticks;
    public Battle battle = Battle.getInstance();
    private Button conclude;
    private String itemString = "";
    protected WinScreen() {
        super(new StringTextComponent("Congratulations! You won!"));
        battle.camera.setPosRot(1.5,102,6,0,180);
        Animation.sound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    protected void init(){
        conclude = addButton(new Button(this.width/2-30,this.height/2+height/3,60,20,new StringTextComponent("Finish"),button->battle.win()));
        conclude.active = false;
        for(Map.Entry<Item,Integer> set: battle.items.entrySet()){
            itemString += set.getValue().toString() + "x " + set.getKey().toString() + " ";
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        drawCenteredString(matrixStack,this.font,"You win!",this.width/2,50,16777215);
        if(ticks>20){
            drawString(matrixStack,this.font,"Items earned: "+itemString,this.width/2-(this.width/8),190,16777215);
        }
        if(ticks>30){
            drawString(matrixStack,this.font,"Xp earned:    "+battle.getXp(),this.width/2-(this.width/8),200,16777215);
        }
        if(ticks>40){
            conclude.active=true;
        }

        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }

    public static void open(){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new WinScreen());});}


    @Override
    public void tick() {
        ticks++;

    }
}
