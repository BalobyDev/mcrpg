package net.baloby.mcrpg.tools;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiDrawing {

    private static final ResourceLocation LIGHT = new ResourceLocation("textures/gui/demo/png");


    public static void drawLightBox(MatrixStack matrixStack,int x, int y,int width, int height){
        Minecraft.getInstance().getTextureManager().bind(LIGHT);
    }

    public static void drawDarkBox(MatrixStack matrixStack,int x, int y,int width, int height){}




}
