package net.baloby.mcrpg.client.gui;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.moves.UseItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class BattleInventoryScreen extends ContainerScreen {

    private static final ResourceLocation INVENTORY_TEXTURE = new ResourceLocation("textures/gui/container/inventory.png");

    protected BattleInventoryScreen() {
        super(Battle.getInstance().sp.inventoryMenu, Battle.getInstance().sp.inventory, new StringTextComponent("Inventory"));
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {

    }



    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
        int relX = (this.width - leftPos)/2;
        int relY = (this.height - topPos)/2;
        this.blit(matrixStack, leftPos, topPos,0,0, imageWidth, imageHeight);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int key, int a, int b){
        if(key==256){
            BattleGui.open();
            return true;
        }
        else {
            return false;
        }
    }


    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_){
        if(hoveredSlot==null)return true;
        else{
            UnitSelectionScreen.open(Battle.instance.playerParty, new UseItem(hoveredSlot.getItem()));
        }
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public static void open(){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new BattleInventoryScreen());});}
}
