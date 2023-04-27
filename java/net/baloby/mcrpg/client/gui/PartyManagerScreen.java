package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.moves.INonBattleMove;
import net.baloby.mcrpg.client.gui.profile.PlayerProfile;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;
import java.util.Map;

public class PartyManagerScreen extends Screen {

    private PlayerEntity player;
    private IPlayerData profile;
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID,"textures/gui/party.png");
    private HashMap<Integer, Profile> profiles = new HashMap<>();
    private INonBattleMove move;
    private Profile moveUser;
    private Profile held;
    private Profile hovered;
    private int HEIGHT = 199;
    private int BOX_SIZE = 53;

    private boolean shifting;

    protected PartyManagerScreen(ServerPlayerEntity player) {
        super(new StringTextComponent("Manage ur dudes/dudettes lol"));
        this.player = player;
        this.profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        this.profiles.put(0,Profile.fromPlayer(player));
        for (int i = 1; i < 8; i++) {
            if(profile.getPartyMembers().contains(""+i)){
                if(profile.getPartyMembers().getString(""+i)!="-"){
                    profiles.put(i,Profile.fromNpc(profile.getPartyMember(i),player));
                }
            }
        }
    }

    protected PartyManagerScreen(ServerPlayerEntity player, INonBattleMove move, Profile user) {
        this(player);
        this.move = move;
        this.moveUser = user;

    }

    @Override
    public boolean keyPressed(int key, int p_231046_2_, int p_231046_3_) {
        if(key == 256&&moveUser!=null){
            SingleCharacterScreen.open(moveUser);
            for (Map.Entry<Integer, Profile> profileEntry : this.profiles.entrySet()){
                profileEntry.getValue().save();

            }
            return true;
        }
        if(key==340){
            this.shifting=true;
        }

        return super.keyPressed(key, p_231046_2_, p_231046_3_);
    }

    @Override
    public boolean keyReleased(int key, int p_223281_2_, int p_223281_3_) {
        if(key==340){
            this.shifting=false;
        }
        return super.keyReleased(key, p_223281_2_, p_223281_3_);
    }

    @Override
    public boolean isPauseScreen(){return false;}

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);

        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.renderBg(matrixStack,partialTicks,mouseX,mouseY);


        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = this.width/2-124;
        int relY = this.height/2-HEIGHT/2;
        this.blit(matrixStack,relX,relY,0,0,256,HEIGHT);

        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++){
                this.blit(matrixStack,relX+(j*60)+9,relY+8+(i*95),0,200,54,54);
            }
        }

        int q = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(((mouseX>relX+(j*60)+9)&&(mouseX<relX+(j*60)+63))
                        &&((mouseY>relY+8+(i*95))&&(mouseY<relY+8+(i*95)+BOX_SIZE))
                        &&profiles.containsKey(q)){
                    this.hovered = profiles.get(q);
                }
                else {
                    this.hovered = null;
                }
                q++;
            }
        }

        int k = 0;
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++){
                if(profiles.containsKey(k)) {
                    if(profiles.get(k).equals(this.hovered)){
                        this.blit(matrixStack,relX+(j*60)+9,relY+8+(i*95),0,200,54,54);
                    }
                    renderProfile(profiles.get(k), matrixStack, this.width / 2 - 87 + (j * 60), relY+8+(i*95));
                }
                else{
                    drawCenteredString(matrixStack,this.font,"-",this.width/2-87 + (j * 60),relY+13+(i*95)+BOX_SIZE,16777215);
                }
                k++;
            }
        }

        if(this.held!=null){
            this.minecraft.getTextureManager().bind(this.held.getSkin());
            this.blit(matrixStack,mouseX-16,mouseY-16,32,32,32,32);
            this.blit(matrixStack,mouseX-16,mouseY-16,160,32,32,32);
        }
    }

    @Override
    public boolean mouseDragged(double x, double y, int key, double p_231045_6_, double p_231045_8_) {
        int relX = this.width/2-124;
        int relY = this.height/2-HEIGHT/2;
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(((x>relX+(j*60)+9)&&(x<relX+(j*60)+63))
                        &&((y>relY+8+(i*95))&&(y<relY+8+(i*95)+39))
                        &&profiles.containsKey(k)){
                    if(this.move==null&&shifting){
                        this.held = profiles.get(k);
                    }
                }
                k++;
            }
        }
        return super.mouseDragged(x, y, key, p_231045_6_, p_231045_8_);
    }

    @Override
    public boolean isMouseOver(double x, double y) {
        int relX = this.width/2-124;
        int relY = this.height/2-HEIGHT/2;
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(((x>relX+(j*60)+9)&&(x<relX+(j*60)+63))
                        &&((y>relY+8+(i*95))&&(y<relY+8+(i*95)+BOX_SIZE))
                        &&profiles.containsKey(k)){
                        this.hovered = profiles.get(k);
                }
                else {
                    this.hovered = null;
                }
                k++;
            }
        }
        return super.isMouseOver(x, y);
    }

    @Override
    public boolean mouseClicked(double x, double y, int key){
        int relX = this.width/2-124;
        int relY = this.height/2-HEIGHT/2;
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(((x>relX+(j*60)+9)&&(x<relX+(j*60)+63))
                &&((y>relY+8+(i*95))&&(y<relY+8+(i*95)+BOX_SIZE))
                        &&profiles.containsKey(k)){
                    if(this.move!=null){
                        this.move.nonBattleExecute(this.moveUser, profiles.get(k));

                    } else if(shifting&&!(profiles.get(k) instanceof PlayerProfile)){
                        this.held = profiles.get(k);
                    }
                    else if(held!=null&&!(profiles.get(k) instanceof PlayerProfile)){
                        swtich(held,profiles.get(k));
                    }
                    else {
                        SingleCharacterScreen.open(profiles.get(k));
                    }
                }
                    k++;
            }
        }
        return super.mouseClicked(x, y, key);
    }

    private void renderProfile(Profile profile, MatrixStack matrixStack, int x, int y){
        ResourceLocation resourceLocation = profile.getSkin()!= null? profile.getSkin() : new ResourceLocation(mcrpg.MODID,"textures/entity/steve");
        this.minecraft.getTextureManager().bind(resourceLocation);
        if(profile!=this.held) {
            this.blit(matrixStack, x - 17, y + 10, 32, 32, 32, 32);
            this.blit(matrixStack, x - 17, y + 10, 160, 32, 32, 32);
        }
        drawCenteredString(matrixStack,this.font,profile.name,x,y+BOX_SIZE+5,16777215);

        this.minecraft.getTextureManager().bind(new ResourceLocation(mcrpg.MODID, "textures/gui/overlay_gui.png"));
        float hpUnit = (float)50/profile.maxHp;
        float mpUnit = (float)50/profile.maxMp;
        int currentHp = (int)(Math.ceil(hpUnit* profile.hp));
        int currentMp = (int)(Math.ceil(mpUnit* profile.mp));
        this.blit(matrixStack,x-26,y+70,1,2,50,2);
        this.blit(matrixStack,x-26,y+70,1,8,currentHp,2);
        this.blit(matrixStack,x-26,y+75,1,2,50,2);
        this.blit(matrixStack,x-26,y+75,1,13,currentMp,2);
    }

    private void swtich(Profile profile, Profile old){
        Integer oldKey =0;
        Integer profileKey=0;
        for(Map.Entry<Integer, Profile> entry: profiles.entrySet()){
            if(entry.getValue().equals(old)){
                oldKey = entry.getKey();
            }
            if(entry.getValue().equals(profile)){
                profileKey = entry.getKey();
            }
        }
        this.held=null;
        profiles.put(oldKey,profile);
        profiles.put(profileKey,old);
        this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().setPartyMembers(this.profiles);
        Animation.sound(SoundEvents.ITEM_PICKUP);
    }


    public static void open(ServerPlayerEntity player){
        Minecraft.getInstance().submitAsync(()->{Minecraft mc = Minecraft.getInstance();
            mc.submitAsync(() -> {mc.setScreen(new PartyManagerScreen(player));});});
    }

    public static void open(ServerPlayerEntity player, INonBattleMove move, Profile user){
        Minecraft.getInstance().submitAsync(()->{Minecraft mc = Minecraft.getInstance();
            mc.submitAsync(() -> {mc.setScreen(new PartyManagerScreen(player,move,user));});});
    }
}
