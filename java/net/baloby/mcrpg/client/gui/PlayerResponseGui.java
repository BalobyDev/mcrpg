package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.NpcRegistry;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.ShopNpc;
import net.baloby.mcrpg.data.dialouge.Response;
import net.baloby.mcrpg.data.dialouge.ResponseElement;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class PlayerResponseGui extends Screen {

    private static final int WIDTH = 256;
    private static final int HEIGHT = 107;
    private ServerPlayerEntity player;
    private ResponseElement element;
    private List<Response> responses = new ArrayList<>();
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/responsegui.png");

    protected PlayerResponseGui(ServerPlayerEntity player, ResponseElement element) {
        super(new StringTextComponent("Player Response"));
        this.player = player;
        this.element = element;
        for(Response response : element.getOptions()){
            if(response.getInsertDialogue().isPresent()) {
                CompoundNBT nbt = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts().getCompound(response.getInsertDialogue().get().toString());
                if (nbt.contains("insert") && !(nbt.getCompound("insert").getString("response").equals("")))
                    response.text = nbt.getCompound("insert").getString("response");
                    responses.add(response);
            }
            else {
                responses.add(response);
            }
        }
    }

    public boolean isPauseScreen(){return Battle.isActive == false;}

    public boolean shouldCloseOnEsc(){return false;}

    public void addButtons(){
        for (int i = 0; i < responses.size(); i++) {
            Response response = responses.get(i);
            if(response.getInsertDialogue().isPresent()){
                CompoundNBT nbt = (CompoundNBT) player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts().get(response.getInsertDialogue().get().getPath());
            }

            this.addButton(new Button(this.width / 2 - 100, this.height - HEIGHT + 100 + responses.size() *  -25 + i * 25, 200, 20, new StringTextComponent(response.text), button -> {
                response.select(player);
            }
            ));
        }
    }

    @Override
    protected void init(){
        this.addButtons();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1,1,1,1);
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(GUI);
        FontRenderer font = this.minecraft.font;
        int relX = (this.width - WIDTH)/2;
        int relY = this.height - HEIGHT - 3;
        this.blit(matrixStack,relX,relY+50,0,50,WIDTH,HEIGHT);
        this.blit(matrixStack,relX,relY+90-responses.size()*25,0,0,WIDTH,15*responses.size());
        super.render(matrixStack,mouseX,mouseY,partialTicks );
    }

    public static void open(ServerPlayerEntity player, ResponseElement element){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new PlayerResponseGui(player, element));});}
}
