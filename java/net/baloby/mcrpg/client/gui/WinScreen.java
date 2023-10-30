package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.moves.BasicAttack;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.Map;


public class WinScreen extends Screen {
    private int ticks;
    public Battle battle = Battle.getInstance();
    private Button conclude;
    private String itemString = "";
    private String moveString = "";
    private ArrayList<ITextComponent> messages = new ArrayList<>();
    protected WinScreen() {
        super(new StringTextComponent("Congratulations! You won!"));
        battle.camera.setPosRot(1.5,102,6,0,180);
        Animation.sound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE);
        battle.getXp();
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
        conclude = addButton(new Button(this.width/2-30,this.height/2+height/3,60,20,new StringTextComponent("Finish"),button->conclude()));
        conclude.active = false;
        for(Map.Entry<Item,Integer> set: battle.items.entrySet()){
            itemString += set.getValue().toString() + "x " + set.getKey().toString() + " ";
        }
        for(MoveType moveType: battle.moveReward){
            moveString += moveType.create().name.getString()+" ";
        }
        if(!itemString.equals("")) messages.add(new StringTextComponent("Items earned: "+itemString));
        else {}
        if(!moveString.equals("")) messages.add(new StringTextComponent("Skills earned: "+moveString));
        messages.add(new StringTextComponent("Xp earned:    "+battle.getXp()));

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        drawCenteredString(matrixStack,this.font,"You win!",this.width/2,50,16777215);
        for (int i = 0; i < messages.size(); i++) {
            if(ticks>i*10+20){
                drawString(matrixStack,this.font,messages.get(i),this.width/2-(this.width/8),180+i*10,16777215);
            }
        }

        if(ticks>50){
            conclude.active=true;
        }

        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }

    public void conclude(){
        if(battle.arena.tickingEntities){
            return;
        }
        battle.win();

    }

    public static void open(){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new WinScreen());});}


    @Override
    public void tick() {
        ticks++;

    }
}
