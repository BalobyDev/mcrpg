package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.ailments.Ailment;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.moves.Move;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class MoveTextGui extends Screen {

    protected MoveTextGui(Move move) {
        super(move.name);
    }

    protected MoveTextGui(ITextComponent text) {
        super(text);
    }

    protected MoveTextGui(Ailment ailment){
        super(new StringTextComponent(ailment.getTextToShow()));
    }

    @Override
    public boolean isPauseScreen(){return false;}

    @Override
    public boolean shouldCloseOnEsc(){return Battle.isActive == false;}

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        drawCenteredString(matrixStack,this.font,this.title,this.width/2,50,16777215);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    public static void open(Move move){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new MoveTextGui(move));});
    }

    public static void open(Ailment ailment){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new MoveTextGui(ailment));});
    }

    public static void open(ITextComponent text){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new MoveTextGui(text));});
    }
}
