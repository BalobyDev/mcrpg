package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.data.IPlayerProfile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class PartyManagerScreen extends Screen {

    private PlayerEntity player;
    private IPlayerProfile profile;
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID,"textures/gui/party.png");
    private HashMap<Integer,Profile> profiles = new HashMap<>();

    protected PartyManagerScreen(ServerPlayerEntity player) {
        super(new StringTextComponent("Manage ur dudes/dudettes lol"));
        this.player = player;
        this.profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        this.profiles.put(0,Profile.fromPlayer(player));
        for (int i = 1; i < 8; i++) {
            if(profile.getPartyMembers().contains(""+i)){
                if(profile.getPartyMembers().getString(""+i)!="-"){
                    profiles.put(i,Profile.fromNpc(profile.getPartyMember(i),player.getLevel()));
                }
            }
        }

    }

    @Override
    public boolean isPauseScreen(){return false;}

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack,this.width/2-124,this.height/2-83,0,0,256,166);
        for (int i = 0; i < profiles.size(); i++) {
            if(profiles.containsKey(i))
            renderProfile(profiles.get(i),matrixStack,this.width/2-87+((i+4)%4*60),((i+4)/4)*78+40);
            else{
                drawCenteredString(matrixStack, this.font,"-",this.width/2-87+((i+4)%4*60),((i+4)/4)*78+40,16777215);
            }
        }
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    private void renderProfile(Profile profile, MatrixStack matrixStack, int x, int y){
        drawCenteredString(matrixStack,this.font,profile.name,x,y,16777215);
        ResourceLocation resourceLocation = new ResourceLocation(mcrpg.MODID,"textures/entity/steve");
        this.minecraft.getTextureManager().bind(resourceLocation);
        this.blit(matrixStack,x-16,y-56,0,0,32,48);


    }





    public static void open(ServerPlayerEntity player){
        Minecraft.getInstance().submitAsync(()->{Minecraft mc = Minecraft.getInstance();
            mc.submitAsync(() -> {mc.setScreen(new PartyManagerScreen(player));});});
    }
}
