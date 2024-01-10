package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class DisclaimerScreen extends Screen {

    private int ticks;

    protected DisclaimerScreen() {
        super(new StringTextComponent("disclaimer"));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderDirtBackground(0);
        this.font.drawWordWrap(new StringTextComponent("This mod is a work of fan-fiction."),this.width/5,50,240,-1);
        this.font.drawWordWrap(new StringTextComponent("Similarities between characters or events in the real world are purely coincidental."),this.width/5,100,240,-1);
        this.font.drawWordWrap(new StringTextComponent("Characters and events depicted are not canon to the base game."),this.width/5,150,240,-1);
    }

    @Override
    public void tick() {
        super.tick();
        ticks++;
    }

    public static void open(){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new DisclaimerScreen());});}
}
