package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestGui extends Screen {

    private int WIDTH = 198;
    private int HEIGHT = 241;
    private int BAR_WIDTH = 179;
    private int BAR_HEIGHT = 25;
    private Quest selectedQuest;
    private HashMap<Integer, Quest> quests = new HashMap<>();
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID,"textures/gui/shop.png");
    private ResourceLocation BAR = new ResourceLocation(mcrpg.MODID, "textures/gui/quest_bar.png");

    protected QuestGui(ServerPlayerEntity player) {
        super(new StringTextComponent("quest_gui"));
        CompoundNBT compoundNBT = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().getQuests();
        int i = 0;
        for (String string : compoundNBT.getAllKeys()) {
            CompoundNBT nbt = compoundNBT.getCompound(string);
            quests.put(i, ModSetup.QUEST_MANAGER.getData(new ResourceLocation(nbt.getString("resource_location"))));
        }
    }

    protected void init(){
        super.init();
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        int relX = 35;
        int relY = this.height/2-HEIGHT/2;
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack,this.width/2-WIDTH/2,relY,0,0,WIDTH-5,HEIGHT);
        this.blit(matrixStack,relX,relY,0,0,WIDTH-5,HEIGHT);
        this.blit(matrixStack,this.width-WIDTH-relX+5,relY,+5,0,WIDTH-5,HEIGHT);
        this.renderAvailableQuests(matrixStack);
        if(selectedQuest!=null){
            this.font.draw(matrixStack,selectedQuest.getTitle(),this.width/2+relX/2,this.height/2,-1);
            this.font.drawWordWrap(new StringTextComponent(selectedQuest.getDescription()),this.width/2+relX/2,this.height/2+15,160,-1);
        }


        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public void renderAvailableQuests(MatrixStack matrixStack){
        int relX = 35;
        int relY = this.height/2-HEIGHT/2;
        for (int i = 0; i < 8; i++) {
            this.minecraft.getTextureManager().bind(BAR);
            this.blit(matrixStack,relX+7,relY+7+i*28,0,0,BAR_WIDTH, 33);
            if(this.quests.containsKey(i)){
                this.font.draw(matrixStack,quests.get(i).getTitle(),relX+16,relY+16+i*28,-1);
            }
            else {
                this.font.draw(matrixStack,"-",relX+16,relY+16+i*28,-1);
            }
        }

    }

    public boolean mouseClicked(double x, double y, int p_231044_5_){
        int relX = 35;
        int relY = this.height/2-HEIGHT/2;
        if(x<relX+7&&relX>relY+7+BAR_WIDTH)return true;
        for (int i = 0; i < 8; i++) {
            if((y>relY+7+i*28&&y<relY+7+i*28+BAR_HEIGHT)&&quests.containsKey(i)){
                this.selectedQuest = quests.get(i);
            }

        }
        return super.mouseClicked(x, y, p_231044_5_);
    }

    public static void open(ServerPlayerEntity player){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new QuestGui(player));});}
}
