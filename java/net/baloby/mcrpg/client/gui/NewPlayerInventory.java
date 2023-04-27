package net.baloby.mcrpg.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;

import net.minecraft.util.text.StringTextComponent;

public class NewPlayerInventory extends InventoryScreen {

    private PlayerEntity player;

    public NewPlayerInventory(PlayerEntity p_i1750_1_) {
        super(p_i1750_1_);
        this.player = p_i1750_1_;
    }

    @Override
    protected void init(){
        super.init();

        int widthPercent = this.width/100;
        int heightPercent = this.height/100;

        ServerPlayerEntity serverPlayer = Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayer(player.getUUID());
        if(serverPlayer==null)return;

        Button party = addButton(new Button(this.width/2-70,this.height/2+heightPercent*45,60,20,new StringTextComponent("Party"), button -> PartyManagerScreen.open(serverPlayer)));
        Button quests = addButton(new Button(this.width/2+10,this.height/2+heightPercent*45,60,20,new StringTextComponent("Quest"),button -> {QuestGui.open(serverPlayer);}));

    }

    public static void open(PlayerEntity player){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new NewPlayerInventory(player));});}


}
