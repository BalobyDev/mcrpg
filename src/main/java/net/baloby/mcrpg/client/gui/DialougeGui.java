package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

public class DialougeGui extends Screen {

    public String name;
    public ITextComponent message;
    public StringTextComponent shownMessage;
    public Entity entity;
    public ServerPlayerEntity player;
    public EntityType type;
    public int speed;
    private ArrayList<ITextComponent> lines = new ArrayList<ITextComponent>();
    private int ticks;
    private int charOn;
    private int lineOn;
    private ITextComponent[] messages;
    private StringTextComponent textComponent;
    private static final int WIDTH = 256;
    private static final int HEIGHT = 85;
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/dialougegui.png");

    protected DialougeGui(ServerPlayerEntity player, Entity entity) {
        super(new StringTextComponent("Dialouge"));
        this.message = new StringTextComponent("We're no strangers to love, You know the  rules and so do I, A full commitment's what  I'm thinking of, You wouldn't get this from   any other guy, I just wanna tell you how   I'm feeling, Gotta make you understand");
        this.player = player;
        this.entity = entity;
        this.shownMessage = new StringTextComponent("");
        this.lineOn=0;
        this.type = entity.getType();
        this.name = entity.getDisplayName().getString();
        this.speed = 1;
        lines.add(new StringTextComponent(""));
    }

    public void newLine(){
        lines.add(new StringTextComponent(""));
        lineOn+=1;
    }

    @Override
    protected void init(){

    }

    @Override
    public boolean isPauseScreen(){return Battle.isActive == false;}

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1,1,1,1);
        this.minecraft.getTextureManager().bind(GUI);
        FontRenderer font = this.minecraft.font;
        int relX = (this.width - WIDTH)/2;
        int relY = this.height - HEIGHT - 3;
        this.blit(matrixStack,relX,relY,0,0,WIDTH,HEIGHT);
        if(entity instanceof HumanoidEntity){
            if(((HumanoidEntity) entity).character instanceof BattleNpc)addButton(new Button(width-width/4,relY-30,80,20,new StringTextComponent("Add to party"), button->{
                BattleNpc battleNpc = (BattleNpc) ((HumanoidEntity) entity).character;
                this.player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().addPartyMember(battleNpc.getType());

            }));
        }
        for(int i  = 0; i<lines.size();i++){
            font.drawWordWrap(shownMessage,relX+13,relY+20+(i*6),220,16777215);

        }

        //drawString(matrixStack,Minecraft.getInstance().font,shownMessage,relX+13,relY+20,16777215);
        font.draw(matrixStack,entity instanceof HumanoidEntity ? ((HumanoidEntity) entity).character.getName() : entity.getDisplayName().getString(),
                (float)(relX+28 - font.width(entity instanceof HumanoidEntity ? ((HumanoidEntity) entity).character.getName() : entity.getDisplayName().getString()) / 2),relY+5,5592405);
        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }

    public void tick(){
        if(this.speed == 0)shownMessage = new StringTextComponent(message.getString());
        if(this.speed != 1)return;
        if(shownMessage.getString().length()<message.getString().length()){
            String tmp = shownMessage.getString();
            tmp += message.getString().charAt(shownMessage.getString().length());
            shownMessage = new StringTextComponent(tmp);
            lines.get(lineOn);
            if(shownMessage.getString().length()%3 == 0){
                Minecraft.getInstance().player.playSound(SoundEvents.STONE_BUTTON_CLICK_ON,1,1.2f);
            }
        }
    }

    public void addTo (String str, char chr){
        str += chr;
    }



    public static void open(ServerPlayerEntity player, Entity entity){
        Minecraft mc = Minecraft.getInstance();
        Minecraft.getInstance().submitAsync(()->{Minecraft.getInstance().setScreen(new DialougeGui(player, entity));});

    }

}
