package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.data.characters.shop.CostType;
import net.baloby.mcrpg.data.characters.shop.Shop;
import net.baloby.mcrpg.data.characters.shop.ShopElement;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ShopScreen extends Screen {

    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/shop.png");
    private int WIDTH = 198;
    private int HEIGHT = 241;
    private int BOX_SIZE = 42;
    private ServerPlayerEntity player;
    private ShopElement selectedElement;
    private ShopElement hovered;
    private Button buyButton;

    private Shop shop;

    protected ShopScreen(Shop shop, ServerPlayerEntity player) {
        super(new StringTextComponent("Shop"));
        this.shop = shop;
        this.player = player;
    }

    @Override
    protected void init(){
        Button buyButton = addButton(new Button(this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH/2+20)-BOX_SIZE,this.height-45,80,20,new StringTextComponent("Buy"),
                button -> {this.selectedElement.purchase(player);}));
        buyButton.active = false;
        this.buyButton = buyButton;

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        this.renderDirtBackground(1);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = 20;
        int relY = (this.height - HEIGHT - 5);

        this.blit(matrixStack, relX, relY,0,0, WIDTH, HEIGHT);
        this.blit(matrixStack, this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH+20), relY,0,0, WIDTH, HEIGHT);

        this.blit(matrixStack,this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH/2+20)-BOX_SIZE,relY+7,398,0,BOX_SIZE*2,BOX_SIZE*2,512,512);
        if(selectedElement!=null){
            this.renderSelected(matrixStack,selectedElement);
        }

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.minecraft.getTextureManager().bind(GUI);
                this.blit(matrixStack,relX+7+(j*47),relY+7+(i*57),199,0,BOX_SIZE,BOX_SIZE);
                if(k<shop.getShopItems().size()&&shop.getShopItems().size()>0){
                    renderItem(matrixStack,relX+(j*47),relY+i*57,shop.getShopItems().get(k));
                }
                k++;
            }
        }
        this.setHovered(matrixStack,mouseX,mouseY);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double x, double y, int key){
        int relX = 20;
        int relY = (this.height - HEIGHT - 5);
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if((x>relX+7+(j*47))&&(x<relX+7+(j*47)+42)&&
                        ((y>relY+7+(i*57))&&(y<relY+7+(i*57)+42))
                        &&this.shop.getShopItems().size()>k){
                    this.selectElement(this.shop.getShopItems().get(k));
                }
                k++;
            }
            if(hovered!=null){

            }

        }
        return super.mouseClicked(x, y, key);
    }

    @Override
    public boolean keyPressed(int key,int p_231046_2_, int p_231046_3_){

        if(key > 48&& key<58){
            int num = key-48;
            if(shop.getShopItems().size()>=num){
                this.selectElement(shop.getShopItems().get(num-1));
            }
        }
        return super.keyPressed(key,p_231046_2_,p_231046_3_);
    }

    public void selectElement(ShopElement element){
        this.selectedElement = element;
        if(element.isAffordable(this.player)) {
            this.buyButton.active = true;
        }
        else{
            this.buyButton.active = false;
        }
    }

    public void renderItem(MatrixStack matrixStack,int x, int y, ShopElement element){
        if(element.icon!=null){
            this.minecraft.getTextureManager().bind(element.icon);
            this.blit(matrixStack, x+12, y+12, 0, 0, 32, 32,32,32);
        }
        this.minecraft.getTextureManager().bind(element.getCostType().getIcon());
        this.blit(matrixStack, x+7, y+51, 0, 0, 8, 8,8,8);
        this.minecraft.font.draw(matrixStack,element.getCost()+"",x+18,y+52,element.isAffordable(player)?-1:16733525);
    }

    private void renderSelected(MatrixStack matrixStack, ShopElement element){
        int relY = (this.height - HEIGHT - 5);
        this.minecraft.getTextureManager().bind(element.icon);
        this.blit(matrixStack,this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH/2+20)-BOX_SIZE+10,relY+17,0,0,64,64,64,64);
        this.font.draw(matrixStack, element.getName(),this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH/2+20)-BOX_SIZE,relY+96,-1);
        this.font.drawWordWrap(element.getDescription(),this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH),relY+116,160,-1);
        this.font.draw(matrixStack, element.getCostType().getName().getString()+" needed: "+element.getCost(),this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH),relY+164,element.isAffordable(player)?-1:16733525);
        this.font.draw(matrixStack, element.getCostType().getName().getString()+" held: "+element.getCostType().getHeld(player),this.minecraft.getWindow().getGuiScaledWidth()-(WIDTH),relY+174,element.isAffordable(player)?-1:16733525);

    }


    private void setHovered(MatrixStack mStack,int x, int y){
        int relX = 20;
        int relY = (this.height - HEIGHT - 5);
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if((x>relX+7+(j*47))&&(x<relX+7+(j*47)+42)&&
                        ((y>relY+7+(i*57))&&(y<relY+7+(i*57)+42))
                        &&this.shop.getShopItems().size()>k){
                    this.hovered = this.shop.getShopItems().get(k);
                    this.minecraft.getTextureManager().bind(GUI);
                    this.blit(mStack, relX+7+(j*47),relY+7+(i*57),199,BOX_SIZE+1,BOX_SIZE,BOX_SIZE);

                }
                k++;
            }

        }
    }





    public static void open(Shop shop, ServerPlayerEntity player){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new ShopScreen(shop,player));});}

}
