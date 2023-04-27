package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.moves.INonBattleMove;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SingleCharacterScreen extends Screen {

    private Profile profile;
    private ServerPlayerEntity player;
    private static final ResourceLocation GUI = new ResourceLocation("textures/gui/demo_background.png");
    private int WIDTH = 248;
    private int HEIGHT = 166;
    private ResourceLocation ICONS = new ResourceLocation(mcrpg.MODID, "textures/gui/overlay_gui.png");

    protected SingleCharacterScreen(Profile profile) {
        super(new StringTextComponent("Just one fella"));
        this.profile = profile;
        this.player = profile.getPlayer();

    }

    @Override
    public boolean isPauseScreen(){return false;}

    @Override
    protected void init(){
        int relX = this.width/2-WIDTH/2;
        int relY = this.height/2-HEIGHT/2;
        this.addButton(new Button(relX-60,relY,60,20,new StringTextComponent("Learn"),
                button ->{MoveLearnScreen.open(profile,player);}));
        this.addButton(new Button(relX-60,relY+30,60,20,new StringTextComponent("Upgrade"),
                button ->{}));
        addMoveButtons(relX,relY);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        renderBg(matrixStack,partialTicks,mouseX,mouseY);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        int relX = this.width/2-WIDTH/2;
        int relY = this.height/2-HEIGHT/2;
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack, relX,this.height/2-HEIGHT/2,0,0,WIDTH,HEIGHT);
        this.minecraft.getTextureManager().bind(profile.getSkin());
        this.blit(matrixStack,relX+10,relY+10,32,32,32,32);
        this.blit(matrixStack,relX+10,relY+10,160,32,32,32);
        this.drawCenteredString(matrixStack,this.font,profile.name,relX+26,relY+48,-1);
        this.renderBars(matrixStack,relX,relY);
        this.renderMoves(matrixStack,relX,relY);


    }

    public void renderBars(MatrixStack matrixStack, int relX, int relY){
        this.font.draw(matrixStack,profile.hp+"/"+ profile.maxHp,relX+58,relY+10,-1);
        this.font.draw(matrixStack,profile.mp+"/"+ profile.maxMp,relX+58,relY+30,-1);
        this.minecraft.getTextureManager().bind(ICONS);
        float hpUnit = (float)50/profile.maxHp;
        float mpUnit = (float)50/profile.maxMp;
        int currentHp = (int)(Math.ceil(hpUnit* profile.hp));
        int currentMp = (int)(Math.ceil(mpUnit* profile.mp));
        this.blit(matrixStack,relX+58,relY+20,0,0,52,7);
        this.blit(matrixStack,relX+58,relY+40,0,0,52,7);
        this.blit(matrixStack,relX+58,relY+21,0,7,currentHp+1,5);
        this.blit(matrixStack,relX+58,relY+41,0,12,currentMp+1,5);


    }

    public void renderMoves(MatrixStack matrixStack,int relX, int relY){
        this.font.drawShadow(matrixStack,"MoveSet:",relX+7,relY+HEIGHT-60,-1);
        int i = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 4; k++) {
                if(profile.getMoves().containsKey(i)) {
                    Move move = profile.getMoves().get(i).create();
                    this.minecraft.getTextureManager().bind(move.type.getIcon());
                    this.blit(matrixStack, relX+(k*60)+7, relY + HEIGHT - 42 + (j * 20), 0, 0, 12, 12,12,12);
                    this.drawCenteredString(matrixStack, this.font,move.name, relX+(k*60)+35, relY + HEIGHT - 40 + (j * 20), -1);
                }
                else {
                    this.drawCenteredString(matrixStack, this.font,"-", relX+(k*60)+35, relY + HEIGHT - 40 + (j * 20), -1);

                }

                i++;

            }

        }

    }

    public void addMoveButtons(int relX, int relY){
        int i = 0;
        for (int j = 0; j < 8; j++) {
            if(profile.getMoves().containsKey(j)) {
                MoveType move = profile.getMoves().get(j);
                if (move.create() instanceof INonBattleMove) {
                    this.addButton(new Button(relX + WIDTH, relY + i * 25, 60, 20, move.create().name, button -> {
                        PartyManagerScreen.open(this.player, (INonBattleMove) move.create(), this.profile);
                    }));
                    i++;
                }
            }
        }

    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean keyPressed(int key, int p_231046_2_, int p_231046_3_) {
        if(key == 256){
            this.profile.save();
            PartyManagerScreen.open(player);
            return true;
        }
        return super.keyPressed(key, p_231046_2_, p_231046_3_);
    }

    public static void open(Profile profile){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new SingleCharacterScreen(profile));});}}
