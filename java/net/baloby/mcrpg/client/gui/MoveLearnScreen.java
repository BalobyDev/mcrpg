package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveLearnScreen extends Screen {

    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/shop.png");
    private ResourceLocation MOVEBAR = new ResourceLocation(mcrpg.MODID, "textures/gui/move_bar.png");
    private MoveType selectedLearned;
    private MoveType selectedToLearn;
    private int WIDTH = 198;
    private int HEIGHT = 241;
    private int BOX_SIZE = 42;
    private int MOVE_BAR_WIDTH = 89;
    private int MOVE_BAR_HEIGHT = 25;
    private Profile profile;
    private ServerPlayerEntity player;

    protected MoveLearnScreen(Profile profile,ServerPlayerEntity player) {
        super(new StringTextComponent("Learn cool stuff"));
        this.profile = profile;
        this.player = player;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        int relX = 35;
        int relY = this.height/2-HEIGHT/2;
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack,relX,relY,0,0,WIDTH,HEIGHT);
        this.blit(matrixStack,this.width-WIDTH-relX,relY,0,0,WIDTH,HEIGHT);
        this.renderCurrentMoves(matrixStack,relX,relY);
        this.renderAvailableMoves(matrixStack,relX,relY);
        if(selectedToLearn!=null){
            Move move = selectedToLearn.create();
            this.minecraft.getTextureManager().bind(move.type.getIcon());
            this.blit(matrixStack,mouseX-6,mouseY-6,0,0,12,12,12,12);
        }
        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }



    public void renderCurrentMoves(MatrixStack matrixStack, int relX, int relY){
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if(profile.getMoves().containsKey(k)){
                    this.minecraft.getTextureManager().bind(MOVEBAR);
                    this.blit(matrixStack,relX+7+(j*(MOVE_BAR_WIDTH+5)),relY+5+(i*27),0,0,MOVE_BAR_WIDTH, MOVE_BAR_HEIGHT);
                    Move move = profile.getMoves().get(k).create();
                    this.minecraft.getTextureManager().bind(move.type.getIcon());
                    this.blit(matrixStack,relX+15+(j*(MOVE_BAR_WIDTH+5)),relY+12+(i*27),0,0,12,12,12,12);
                    this.font.draw(matrixStack, move.name,relX+30+(j*(MOVE_BAR_WIDTH+5)),relY+14+(i*27),-1);
                }
                else {
                    this.minecraft.getTextureManager().bind(MOVEBAR);
                    this.blit(matrixStack,relX+7+(j*(MOVE_BAR_WIDTH+5)),relY+5+(i*27),0,0,MOVE_BAR_WIDTH, MOVE_BAR_HEIGHT);
                    this.font.draw(matrixStack, "-",relX+30+(j*(MOVE_BAR_WIDTH+5)),relY+14+(i*27),-1);
                }
                k++;
            }
        }

    }

    public void renderAvailableMoves(MatrixStack matrixStack, int relX, int relY){
        int k = 0;
        HashMap<Integer,MoveType> moveTypes = this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().availableMoveList();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if(moveTypes.containsKey(k)){
                    this.minecraft.getTextureManager().bind(MOVEBAR);
                    this.blit(matrixStack,this.width-WIDTH-relX+7+(j*(MOVE_BAR_WIDTH+5)),relY+5+(i*27),0,0,MOVE_BAR_WIDTH, MOVE_BAR_HEIGHT);
                    Move move = this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().availableMoveList().get(k).create();
                    this.minecraft.getTextureManager().bind(move.type.getIcon());
                    this.blit(matrixStack,this.width-WIDTH-relX+15+(j*(MOVE_BAR_WIDTH+5)),relY+12+(i*27),0,0,12,12,12,12);
                    this.font.draw(matrixStack, move.name,this.width-WIDTH-relX+30+(j*MOVE_BAR_WIDTH+5),relY+14+(i*27),-1);
                }
                else {
                    this.minecraft.getTextureManager().bind(MOVEBAR);
                    this.blit(matrixStack,this.width-WIDTH-relX+7+(j*(MOVE_BAR_WIDTH+5)),relY+5+(i*27),0,0,MOVE_BAR_WIDTH, MOVE_BAR_HEIGHT);
                    this.font.draw(matrixStack, "-",this.width-WIDTH-relX+30+(j*(MOVE_BAR_WIDTH+5)),relY+14+(i*27),-1);
                }
                k++;
            }
        }

    }
    @Override
    public boolean mouseClicked(double x, double y, int p_231044_5_) {
        HashMap<Integer,MoveType> moveTypes = this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().availableMoveList();
        HashMap<Integer, MoveType> moveSet = profile.getMoves();

        int k = 0;
        int relX = 35;
        int relY = this.height/2-HEIGHT/2;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if(((x>this.width-WIDTH-relX+7+(j*(MOVE_BAR_WIDTH+5)))&&
                        (x<this.width-WIDTH-relX+7+(j*(MOVE_BAR_WIDTH+5))+MOVE_BAR_WIDTH))&&
                        ((y>relY+5+(i*27))&&
                                (y<relY+5+(i*27)+MOVE_BAR_HEIGHT))
                        &&moveTypes.containsKey(k)&&selectedToLearn==null){
                    this.selectedToLearn = moveTypes.get(k);
                }
                else if (((x > relX+7+(j*(MOVE_BAR_WIDTH+5))) &&
                        (x <  relX+7+(j*(MOVE_BAR_WIDTH+5)) + MOVE_BAR_WIDTH)) &&
                        ((y > relY + 5 + (i * 27)) &&
                                (y < relY + 5 + (i * 27) + MOVE_BAR_HEIGHT))
                                && (!moveSet.containsKey(k))&&selectedToLearn!=null) {
                    if(!moveSet.containsValue(selectedToLearn)) {
                        moveSet.put(k, selectedToLearn);
                        selectedToLearn = null;
                    }
                }
                else {
                    k++;
                }
            }
        }
        return super.mouseClicked(x, y, p_231044_5_);
    }

    @Override
    public boolean mouseDragged(double x, double y, int p_231045_5_, double p_231045_6_, double p_231045_8_) {
//        HashMap<Integer,MoveType> moveTypes = this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().availableMoveList();
//        int k = 0;
//        int relX = 35;
//        int relY = this.height/2-HEIGHT/2;
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 2; j++) {
//                if(((x>this.width-WIDTH-relX+7+(j*(MOVE_BAR_WIDTH+5)))&&
//                        (x<this.width-WIDTH-relX+7+(j*(MOVE_BAR_WIDTH+5))+MOVE_BAR_WIDTH))&&
//                        ((y>relY+5+(i*27))&&
//                        (y<relY+5+(i*27)+MOVE_BAR_HEIGHT))
//                        &&moveTypes.containsKey(k)&&selectedToLearn==null){
//                    this.selectedToLearn = moveTypes.get(k);
//                }
//                else {
//                    k++;
//                }
//            }
//        }
        return super.mouseDragged(x, y, p_231045_5_, p_231045_6_, p_231045_8_);
    }


    @Override
    public boolean mouseReleased(double x, double y, int p_231048_5_) {
//       HashMap<Integer, MoveType> moveTypes = profile.getMoves();
//        int k = 0;
//        int relX = 35;
//        int relY = this.height/2-HEIGHT/2;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 2; j++) {
//                if (((x > relX+7+(j*(MOVE_BAR_WIDTH+5))) &&
//                        (x <  relX+7+(j*(MOVE_BAR_WIDTH+5)) + MOVE_BAR_WIDTH)) &&
//                        ((y > relY + 5 + (i * 27)) &&
//                                (y < relY + 5 + (i * 27) + MOVE_BAR_HEIGHT))
//                                && (!moveTypes.containsKey(k))&&selectedToLearn!=null) {
//                    moveTypes.put(k,selectedToLearn);
//                        }
//                else {
//                    k++;
//                }
//
//                    }
//                }
//        this.selectedToLearn = null;
                return super.mouseReleased(x, y, p_231048_5_);

    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean keyPressed(int key, int p_231046_2_, int p_231046_3_) {
        if(key == 256){
            SingleCharacterScreen.open(profile);
            return true;
        }
        return super.keyPressed(key, p_231046_2_, p_231046_3_);
    }


    public static void open(Profile profile, ServerPlayerEntity player){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new MoveLearnScreen(profile,player));});}



}
