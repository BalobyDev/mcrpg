package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class AutoRecoverScreen extends Screen {

    private ServerPlayerEntity player;

    protected AutoRecoverScreen(ServerPlayerEntity player) {
        super(new StringTextComponent("Would you like to perform an auto-recover?"));
        this.player = player;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    protected void init() {
        Button no = new Button(this.width/2-120,this.height/2-10,80,20,new StringTextComponent("Nah, never-mind"),button ->{
            Minecraft mc = Minecraft.getInstance();
            mc.submitAsync(() -> {mc.setScreen(null);});
        });

        Button yes = new Button(this.width/2+120,this.height/2-10,80,20,new StringTextComponent("Yeah okay sure"),button ->{
            this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().getPartyMembers();
            Minecraft mc = Minecraft.getInstance();
            mc.submitAsync(() -> {mc.setScreen(null);});

        });
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    public static void open(ServerPlayerEntity player){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new AutoRecoverScreen(player));});}

}
