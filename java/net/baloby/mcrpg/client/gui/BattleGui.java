package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.BossBattle;
import net.baloby.mcrpg.battle.Unit.NpcUnit;
import net.baloby.mcrpg.battle.Unit.PlayerUnit;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.UnitSelection;
import net.baloby.mcrpg.battle.moves.*;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class BattleGui extends Screen {
    public Battle battle;
    public Unit unit;
    private static final int WIDTH = 145;
    private static final int HEIGHT = 90;
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/battlegui.png");
    private BattleNpc npc;



    protected BattleGui(){
        super(new StringTextComponent("test"));
        this.battle = Battle.getInstance();
        this.battle.gui = this;
        this.unit = battle.activeUnit;
        if(unit instanceof NpcUnit){
            npc = ((NpcUnit) unit).character;
        }
    }


    @Override
    protected void init(){
        int relX = 20;
        int relY = (this.height - HEIGHT - 5);
        Button attack = addButton(new Button(relX+10, relY+10, 60 , 20, new StringTextComponent("Attack"),button -> UnitSelectionScreen.open(battle.enemyParty,new BasicAttack())));
        Button defend = addButton(new Button(relX+75, relY+10, 60 , 20, new StringTextComponent("Defend"),button -> unit.action(new Gaurd(),unit)));
        Button spells = addButton(new Button(relX+10, relY+35,60,20, new StringTextComponent("Skill"), button -> SkillScreen.open(unit)));

        Button inventory = addButton(new Button(relX+75, relY+35, 60, 20, new StringTextComponent("Inventory"), button -> {
            battle.sp.openMenu(npc);
            EquipScreen.open(battle.sp, this.battle, this.minecraft.player, npc);}));
        Button analyze = addButton(new Button(relX+10, relY+60, 60 , 20, new StringTextComponent("Tactics"),button ->{UnitSelectionScreen.open(battle.enemyParty);}));
        Button escape = addButton(new Button(relX+75, relY+60, 60 , 20, new StringTextComponent("Escape"),button -> battle.conclude()));
        if(battle instanceof BossBattle)escape.active = false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = 20;
        int relY = (this.height - HEIGHT - 5);
        this.blit(matrixStack, relX, relY,0,0, WIDTH, HEIGHT);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public static void open(){Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new BattleGui());});}

    public static void close(){Minecraft.getInstance().setScreen(null);}

}