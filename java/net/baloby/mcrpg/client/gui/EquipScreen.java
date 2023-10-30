package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.PlayerUnit;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class EquipScreen extends ContainerScreen {


    public Optional<Battle> battle;
    public PlayerEntity player;
    public LivingEntity entity;
    public Optional<BattleNpc> npc;
    public ServerPlayerEntity splayer;


    protected EquipScreen(ServerPlayerEntity splayer, Battle battle, PlayerEntity player, BattleNpc npc) {
        super(npc!=null?npc.createMenu(0,player.inventory,player):player.containerMenu,player.inventory,new TranslationTextComponent("container.crafting"));
        this.titleLabelX = 97;
        this.battle = Optional.ofNullable(battle);
        this.player = player;
        this.npc = Optional.ofNullable(npc);
        this.player.containerMenu = this.getMenu();
        this.getMenu().addSlotListener(splayer);
        this.splayer = splayer;
    }

    @Override
    protected void init() {
        super.init();

    }

    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
        int i = this.leftPos;
        int j = this.topPos;
        boolean synched = this.getMenu().isSynched(player);
        this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if(npc.isPresent()){
            this.blit(p_230450_1_,i+75,j+42,75,60,19,19);
         }
        if(getEntity().isPresent()) InventoryScreen.renderEntityInInventory(leftPos + 51, topPos + 75, 30, (float)(leftPos + 51) - mouseX, (float)(topPos + 75 - 50) - mouseY, this.getEntity().isPresent()? this.getEntity().get():this.getEntity().get());
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
        int relX = (this.width - leftPos)/2;
        int relY = (this.height - topPos)/2;
        this.blit(matrixStack, leftPos, topPos,0,0, imageWidth, imageHeight);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

    }

    @Override
    public boolean keyPressed(int key, int a, int b){
        if(key==256){
            if(battle.isPresent()) {
                BattleGui.open();
                return true;
            }
        }
        return super.keyPressed(key, a, b);
    }

    public Optional<LivingEntity> getEntity(){
        if(battle.isPresent()) {
            if (!(battle.get().activeUnit instanceof PlayerUnit)) {
                return Optional.ofNullable(battle.get().activeUnit.entity);
            }
        }
        return Optional.ofNullable(this.minecraft.player);
    }



    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return !battle.isPresent();
    }

    public static void open(ServerPlayerEntity splayer, Battle battle, PlayerEntity player, BattleNpc npc){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {
            mc.setScreen(new EquipScreen(splayer,battle,player,npc));
        });
    }
}
