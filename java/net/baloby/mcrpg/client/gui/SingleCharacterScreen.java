package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.moves.INonBattleMove;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.NpcProfile;
import net.baloby.mcrpg.client.gui.profile.PlayerProfile;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.characters.BattleNpc;
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
    private BattleNpc npc;

    protected SingleCharacterScreen(Profile profile) {
        super(new StringTextComponent("Just one fella"));
        this.profile = profile;
        this.player = profile.getPlayer();
        if(profile instanceof NpcProfile){
            this.npc = ((NpcProfile) profile).npc;
        }
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
                button ->{UpgradeScreen.open(profile);}));
        this.addButton(new Button(relX-60,relY+60,60,20,new StringTextComponent("Equip"),
                button ->{

            try {
                player.openMenu(npc);
            }
            catch (UnsupportedOperationException exception){};


        }));
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
        this.font.draw(matrixStack,profile.name,relX+12,relY+48,4210752);
        this.renderBars(matrixStack,relX,relY);
        this.renderStats(matrixStack,relX,relY);

        this.renderMoves(matrixStack,relX,relY);
    }

    public void renderStats(MatrixStack matrixStack, int x, int y){
        int relX = x + 12;
        int relY = y + 70;
        this.font.draw(matrixStack,"LVL: "+profile.lvl,relX,relY,4210752);
        this.font.draw(matrixStack,"STR: "+profile.STR,relX,relY+20,4210752);
        this.font.draw(matrixStack,"MAG: "+ profile.MAG,relX+50,relY+20,4210752);
        this.font.draw(matrixStack,"DEF: "+ profile.DEF,relX,relY+40,4210752);
        this.font.draw(matrixStack,"SPD: "+ profile.SPD,relX+50,relY+40,4210752);

        }


    public void renderBars(MatrixStack matrixStack, int relX, int relY){
        this.font.draw(matrixStack,profile.hp+"/"+ profile.maxHp*2,relX+58,relY+10,4210752);
        this.font.draw(matrixStack,profile.mp+"/"+ profile.maxMp*2,relX+58,relY+30,4210752);
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
        int i = 0;
        for (int j = 0; j < 8; j++) {
                if(profile.getMoves().containsKey(i)) {
                    Move move = profile.getMoves().get(i).create();
                    this.minecraft.getTextureManager().bind(move.type.getIcon());
                    this.blit(matrixStack, relX + WIDTH/2 +20, relY + 6 + (j * 20), 0, 0, 12, 12, 12, 12);
                    this.font.draw(matrixStack, move.name, relX + WIDTH/2 +35, relY +  8 + (j * 20), 4210752);
                }
                else {
                    this.font.draw(matrixStack, "-", relX + WIDTH/2 +35, relY + 10 + (j * 20), 4210752);
                }
                i++;
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
