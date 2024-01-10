package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.profile.PlayerProfile;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;


public class UpgradeScreen extends Screen {

    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/upgrade.png");
    private int WIDTH = 256;
    private int HEIGHT = 207;
    private Button confirm;
    private Profile profile;
    private int xp;
    private int cost;
    private int vigor;
    private ServerPlayerEntity player;

    protected UpgradeScreen(Profile profile) {
        super(new StringTextComponent("Level up"));
        this.profile = profile;
        this.player = profile.getPlayer();

    }

    @Override
    protected void init() {
        super.init();
        this.confirm = addButton(new Button(this.width/2+WIDTH/2-90,this.height/2-HEIGHT/2+170,60,20,new StringTextComponent("Confirm"),button->{}));
        for (int i = 0; i < 6; i++) {
            addButton(new Button(this.width/2-WIDTH/2+100,this.height/2-HEIGHT/2+43+i*23,12,20,new StringTextComponent("<"),button->{}));
            addButton(new Button(this.width/2-WIDTH/2+130,this.height/2-HEIGHT/2+43+i*23,12,20,new StringTextComponent(">"),button->{}));
        }

        confirm.active = false;
    }

    @Override
    public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        renderBackground(mStack);
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(mStack,this.width/2-WIDTH/2,this.height/2-HEIGHT/2,0,0,WIDTH,HEIGHT);

        this.font.draw(mStack,"Exp Held:",this.width/2-WIDTH/2+12,this.height/2-HEIGHT/2+12,4210752);
        this.font.draw(mStack,"Exp needed:",this.width/2-WIDTH/2+12,this.height/2-HEIGHT/2+24,4210752);

        this.font.draw(mStack,""+profile.getXp(),this.width/2-WIDTH/2+80,this.height/2-HEIGHT/2+12,4210752);
        this.font.draw(mStack,"5",this.width/2-WIDTH/2+80,this.height/2-HEIGHT/2+24,4210752);
        this.drawCenteredString(mStack,this.font,"Vigor",this.width/2-WIDTH/2+62,this.height/2-HEIGHT/2+50,-1);
        this.drawCenteredString(mStack,this.font,profile.maxHp+"",this.width/2-WIDTH/2+121 ,this.height/2-HEIGHT/2+50,-1);
        this.drawCenteredString(mStack,this.font,"Strength",this.width/2-WIDTH/2+62,this.height/2-HEIGHT/2+73,-1);
        this.drawCenteredString(mStack,this.font,profile.STR+"",this.width/2-WIDTH/2+121 ,this.height/2-HEIGHT/2+73,-1);
        this.drawCenteredString(mStack,this.font,"Mind",this.width/2-WIDTH/2+62,this.height/2-HEIGHT/2+96,-1);
        this.drawCenteredString(mStack,this.font,profile.maxMp+"",this.width/2-WIDTH/2+121 ,this.height/2-HEIGHT/2+96,-1);
        this.drawCenteredString(mStack,this.font,"Spirit",this.width/2-WIDTH/2+62,this.height/2-HEIGHT/2+119,-1);
        this.drawCenteredString(mStack,this.font,profile.MAG+"",this.width/2-WIDTH/2+121 ,this.height/2-HEIGHT/2+119,-1);
        this.drawCenteredString(mStack,this.font,"Resistance",this.width/2-WIDTH/2+62,this.height/2-HEIGHT/2+142,-1);
        this.drawCenteredString(mStack,this.font,profile.DEF+"",this.width/2-WIDTH/2+121 ,this.height/2-HEIGHT/2+142,-1);
        this.drawCenteredString(mStack,this.font,"Endurance",this.width/2-WIDTH/2+62,this.height/2-HEIGHT/2+165,-1);
        this.drawCenteredString(mStack,this.font,profile.SPD+"",this.width/2-WIDTH/2+121 ,this.height/2-HEIGHT/2+165,-1);


        this.font.draw(mStack,"LVL: "+profile.lvl,this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+12,4210752);

        this.font.draw(mStack,"Max HP:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+50,4210752);
        this.font.draw(mStack,"Max MP:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+60,4210752);
        this.font.draw(mStack,"Strength:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+70,4210752);
        this.font.draw(mStack,"Spirit:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+80,4210752);
        this.font.draw(mStack,"Defense:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+90,4210752);
        this.font.draw(mStack,"Poise:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+100,4210752);
        this.font.draw(mStack,"Speed:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+110,4210752);
        this.font.draw(mStack,"Max load:",this.width/2+WIDTH/2-88,this.height/2-HEIGHT/2+120,4210752);

        this.font.draw(mStack,profile.maxHp+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+50,4210752);
        this.font.draw(mStack,profile.maxMp+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+60,4210752);
        this.font.draw(mStack,profile.STR+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+70,4210752);
        this.font.draw(mStack,profile.MAG+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+80,4210752);
        this.font.draw(mStack,profile.DEF+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+90,4210752);
        this.font.draw(mStack,"10",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+100,4210752);
        this.font.draw(mStack,profile.SPD+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+110,4210752);
        this.font.draw(mStack,profile.SPD*2+"",this.width/2+WIDTH/2-40,this.height/2-HEIGHT/2+120,4210752);
        super.render(mStack, mouseX,  mouseY, partialTicks);
    }

    public static void open(Profile profile){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new UpgradeScreen(profile));});}
}
