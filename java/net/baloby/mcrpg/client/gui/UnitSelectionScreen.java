package net.baloby.mcrpg.client.gui;

import com.ibm.icu.impl.Pair;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.client.gui.indicator.Indicator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderLivingEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UnitSelectionScreen extends Screen {

    private Party party;
    private Battle battle;
    private Move move;
    private Unit selected;
    public HashMap<Indicator, RenderLivingEvent.Post> indicators = new HashMap<>();

    protected UnitSelectionScreen(Party party, Move move) {
        super(new StringTextComponent("select"));
        this.party = party;
        this.battle = party.battle;
        this.move = move;
        this.selected = party.getRowOrder().get(0);
        this.setCamera();
        this.selected.highlight();
    }

    protected UnitSelectionScreen(Party party) {
        super(new StringTextComponent("select"));
        this.party = party;
        this.battle = party.battle;
        this.selected = party.getRowOrder().get(0);
        this.setCamera();
        this.selected.highlight();
    }

    @Override
    public boolean keyPressed(int key, int p_231046_2_, int p_231046_3_) {
        if(key == 256){
            selected.highlight();
            battle.camera.setBehind();
            BattleGui.open();
            return true;
        }
        if(key==68||key==262){
            changeSelected();
            return true;
        }
        if(key==65||key==263){
            changeBackwards();
            return true;
        }
        if(key==32){
            action();
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double x, double y, int key){
        if(key==0){
            action();
            return true;
        }
        if(key ==1){
            selected.highlight();
            battle.camera.setBehind();
            BattleGui.open();
            return true;
        }
        return false;
    }


    public void changeSelected(){
        selected.highlight();
        selected = party.getRowOrder().get((party.getRowOrder().indexOf(selected)+1)%party.getRowOrder().size());
        selected.highlight();
    }
    public void changeBackwards(){
        selected.highlight();
        if(party.getRowOrder().indexOf(selected)>0){
            selected = party.getRowOrder().get(party.getRowOrder().indexOf(selected)-1);
        }
        else {
            selected = party.getRowOrder().get(party.getRowOrder().size()-1);
        }
        selected.highlight();

    }


    public void setCamera(){
        battle.camera.setFacingParty(party);
    }

    public void action(){
        selected.highlight();
        battle.camera.setBehind();
        if(move!=null) {
            battle.activeUnit.action(move, selected);
        }
        else {
            ShowStatsScreen.open(selected);
        }

    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        ArrayList<Indicator> toRemove = new ArrayList();

        for(Map.Entry<Indicator, RenderLivingEvent.Post> entry : indicators.entrySet()){
            if(entry.getKey().unit==null)indicators.remove(entry.getKey());
            entry.getKey().render(this,matrixStack);
        }
        for(Indicator indicator : toRemove) {
            indicators.remove(indicator);
        }
        if(party!=null){
            drawCenteredString(matrixStack, this.font, selected.name, this.width / 2, 50, 16777215);
        }
        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }

    public void setIndicators(HashMap map){
        this.indicators = map;
    }

    public Unit getSelected(){return selected;}

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public static void open(Party party, Move move){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new UnitSelectionScreen(party, move));});
    }

    public static void open(Party party){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new UnitSelectionScreen(party));});
    }
}
