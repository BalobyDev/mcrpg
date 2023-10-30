package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.PronounConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.gui.widget.button.Button;

import java.awt.*;
import java.util.ArrayList;

public class PronounSelectScreen extends Screen {

    private ServerPlayerEntity player;
    private ArrayList<PronounConfig> pronounConfigs = new ArrayList<PronounConfig>();
    private PronounConfig selected;
    private Button pronouns;

    protected PronounSelectScreen(ServerPlayerEntity player) {
        super(new StringTextComponent("of course you have blue hair and pronouns"));
        this.player = player;
        this.pronounConfigs.add(new PronounConfig("They","Them","Theirs"));
        this.pronounConfigs.add(new PronounConfig("He","Him","His"));
        this.pronounConfigs.add(new PronounConfig("She","Her","Hers"));
        this.selected = pronounConfigs.get(0);
    }

    @Override
    protected void init() {
        Button pronouns = this.addButton(new Button(this.width/2-50,this.height/2-50,100,20,
                new StringTextComponent(this.selected.getSubjective()+"/"+this.selected.getObjective()+"/"+this.selected.getPossessive()),
                button -> this.selectNext()));
        this.pronouns = pronouns;


        Button confirm = this.addButton(new Button(this.width/2-50,this.height/2-10,100,20,new StringTextComponent("Confirm"),
                button -> this.setPronouns()));
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.drawCenteredString(matrixStack,this.font,"Please select your pronouns",this.width/2,30,-1);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

    private void setPronouns(){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("sub",this.selected.getSubjective());
        nbt.putString("obj",this.selected.getObjective());
        nbt.putString("pos",this.selected.getPossessive());
        this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().setPronouns(nbt);
        this.minecraft.setScreen(null);
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    private void selectNext(){
        int next = (this.pronounConfigs.indexOf(selected)+1)%this.pronounConfigs.size();
        this.selected = pronounConfigs.get(next);
        this.pronouns.setMessage(new StringTextComponent(this.selected.getSubjective()+"/"+this.selected.getObjective()+"/"+this.selected.getPossessive()));
    }

    private void addCustom(PronounConfig config){
        this.pronounConfigs.add(config);
    }

    public static void open(ServerPlayerEntity player){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new PronounSelectScreen(player));});}
}
