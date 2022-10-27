package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.Move;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class UnitSelectionScreen extends Screen {

    private Party party;
    private Battle battle;
    private Move move;
    private Unit selected;


    protected UnitSelectionScreen(Party party, Move move) {
        super(new StringTextComponent("select"));
        this.party = party;
        this.battle = party.battle;
        this.move = move;
        this.selected = party.active.get(0);
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
        selected = party.active.get((party.active.indexOf(selected)+1)%party.active.size());
        selected.highlight();
    }
    public void changeBackwards(){
        selected.highlight();
        if(party.active.indexOf(selected)>0){
            selected = party.active.get(party.active.indexOf(selected)-1);
        }
        else {
            selected = party.active.get(party.active.size()-1);
        }
        selected.highlight();

    }


    public void setCamera(){
        if(party instanceof PlayerParty){
            battle.camera.setPosRot(1.5,2,6,0,180);
        }
        else {
            battle.camera.setPosRot(1.5,3,3,0,0);
        }
    }

    public void action(){
        selected.highlight();
        battle.camera.setBehind();
        battle.activeUnit.action(move,selected);

    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        if(party!=null){
            drawCenteredString(matrixStack, this.font, selected.name, this.width / 2, 50, 16777215);
        }
        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }

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
}
