package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.ShopNpc;
import net.baloby.mcrpg.data.dialouge.DialogueInstance;
import net.baloby.mcrpg.data.dialouge.ResponseElement;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.models.HumanoidModel;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.codehaus.plexus.util.dag.Vertex;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class DialougeGui extends Screen {

    public String name;
    public ITextComponent message;
    public ServerPlayerEntity player;
    public EntityType type;
    public Npc npc;
    public int speed;
    private ArrayList<ITextComponent> lines;
    private ArrayList<ITextComponent> words;
    private ArrayList<ITextComponent> shownLines = new ArrayList<>();
    private boolean cutsceneOrBattle;
    private DialogueInstance instance;
    private int lineOn = 0;
    private int index;
    private boolean typing = true;
    private ITextComponent[] messages;
    private StringTextComponent textComponent;
    private static final int WIDTH = 256;
    private static final int HEIGHT = 85;
    private ResourceLocation GUI = new ResourceLocation(mcrpg.MODID, "textures/gui/dialougegui.png");

    protected DialougeGui(ServerPlayerEntity player, DialogueInstance instance) {
        super(new StringTextComponent("Dialouge"));
        this.instance = instance;
        this.message = new StringTextComponent(instance.getMessage());
        this.message = new StringTextComponent(message.getString()+" ");
        this.player = player;
        this.npc = Registration.NPC_REGISTRY.get().getValue(instance.getNpc()).create();
        this.lineOn=0;
        this.name = npc.getName();
        this.speed = 1;
        this.cutsceneOrBattle = Battle.isActive;
        this.words = this.carveWords();
        this.lines = this.carveLines();
        for (int i = 0; i < lines.size(); i++) {
            shownLines.add(new StringTextComponent(""));
        }
    }

    protected DialougeGui(ServerPlayerEntity player, DialogueInstance instance,int index) {
        this(player,instance);
        this.index = index;
    }

    public void newLine(){
        lines.add(new StringTextComponent(""));
        lineOn+=1;
    }

    @Override
    protected void init(){

    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen(){return !cutsceneOrBattle;}

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        RenderSystem.color4f(1,1,1,1);

        FontRenderer font = this.minecraft.font;
        if(!cutsceneOrBattle){
            this.renderBackground(matrixStack);
        }
        int relX = (this.width - WIDTH)/2;
        int relY = this.height - HEIGHT - 3;
        this.renderCharacter(matrixStack, relX+18, relY-50);
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack,relX,relY,0,0,WIDTH,HEIGHT);
        this.blit(matrixStack,relX+8,relY+15,8,94,242,63);

        for (int i = 0; i < lines.size(); i++) {
            drawString(matrixStack,this.font,shownLines.get(i),relX+13,relY+20+(i*10),-1);
        }
        font.draw(matrixStack,name , (float)(relX+28 - font.width(name) / 2),relY+5,5592405);

        super.render(matrixStack,mouseX,mouseY,partialTicks);

    }

    public void tick(){
        String str = this.lines.get(lineOn).getString();
        String toShow = this.shownLines.get(lineOn).getString();
        if(typing) {
            if (this.shownLines.get(lineOn).getString().length() < this.lines.get(lineOn).getString().length()) {
                toShow += str.charAt(toShow.length());
                shownLines.set(lineOn, new StringTextComponent(toShow));
            } else {
                toShow = "";
                this.startNewLine();
            }
        }
    }

    public ArrayList<ITextComponent> carveWords(){

        ArrayList<ITextComponent> texts = new ArrayList<>();
        String str = message.getString();
        String toAdd = "";
        for (int i = 0; i < str.length()-1; i++) {
            if(str.substring(i,i+1).equals(" ")){
                texts.add(new StringTextComponent(toAdd));
                toAdd = "";
            }
            else {
                toAdd = toAdd + str.substring(i,i+1);
            }
        }
        texts.add(new StringTextComponent(toAdd));
        return texts;
    }

    public ArrayList<ITextComponent> carveLines() {
        ArrayList<ITextComponent> lines = new ArrayList<>();
        String str = "";
        for (int i = 0; i < words.size(); i++) {
            if((str + words.get(i).getString()).length()<44){
                str += words.get(i).getString()+" ";
            }
            else {
                lines.add(new StringTextComponent(str));
                str = "";
                str += words.get(i).getString()+" ";

            }

        }
        lines.add(new StringTextComponent(str));
        return lines;
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if(typing){
            for (int i = 0; i < lines.size(); i++) {
                shownLines.set(i,lines.get(i));
            }

            typing = false;
        }
        else {
            if(instance.getQuestComplete().isPresent()){
                Quest quest = ModSetup.QUEST_MANAGER.getData(instance.getQuestComplete().get());
                QuestAwardScreen.open(instance,player,quest);
            }
            else if(instance.chain.getInstances().containsKey(instance.getNext())){
                instance.chain.openIndex(player,instance.getNext());
            } else if (instance.getResponses().isPresent()) {
                ResponseElement responseElement = new ResponseElement(instance.getResponses().get());
                responseElement.setChain(instance.chain);
                responseElement.open(player);
            } else if (instance.getSwap().isPresent()) {
                ModSetup.DIALOGUE_MANAGER.getData(instance.getSwap().get().getDialogue()).openIndex(player,instance.getSwap().get().getId());

            } else {
                Minecraft.getInstance().setScreen(null);
            }
        }
        return super.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    public void startNewLine(){
        if(this.lineOn<lines.size()-1) this.lineOn+=1;
        else {
            this.typing = false;
        }
    }

    public void renderCharacter(MatrixStack stack,int k, int j){

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.minecraft.getTextureManager().bind(npc.getSkin());
            RenderSystem.enableBlend();
        this.blit(stack, k, j, 32.0F, 32.0F, 32, 32, 256, 256);
        this.blit(stack, k, j, 160.0F, 32.0F, 32, 32, 256, 256);

        this.blit(stack, k, j+32, 80.0F, 80.0F, 32, 48, 256, 256);
        this.blit(stack, k, j+32, 80.0F, 144.0F, 32, 48, 256, 256);

        if(npc.getSlim())
        {
            this.blit(stack, k-12, j+32, 176.0F, 80.0F, 12, 48, 256, 256);
            this.blit(stack, k-12, j+32, 176.0F, 144.0F, 12, 48, 256, 256);
        }

        else
        {
            this.blit(stack, k-16, j+32, 176.0F, 80.0F, 16, 48, 256, 256);
            this.blit(stack, k-16, j+32, 176.0F, 144.0F, 16, 48, 256, 256);
        }

        if(npc.getEntityType().equals(ModEntities.HUMANOID_SLIM.get()))
        {
            this.blit(stack, k+32, j+32, 144.0F, 208.0F, 12, 48, 256, 256);
            this.blit(stack, k+32, j+32, 208.0F, 208.0F, 12, 48, 256, 256);
        }
        else
        {
            this.blit(stack, k+32, j+32, 144.0F, 208.0F, 16, 48, 256, 256);
            this.blit(stack, k+32, j+32, 208.0F, 208.0F, 16, 48, 256, 256);
        }

        this.blit(stack, k+16, j+80, 16.0F, 80.0F, 16, 48, 256, 256);
        this.blit(stack, k+16, j+80, 16.0F, 144.0F, 16, 48, 256, 256);

        this.blit(stack, k, j+80, 80.0F, 208.0F, 16, 48, 256, 256);
        this.blit(stack, k, j+80, 16.0F, 208.0F, 16, 48, 256, 256);

        RenderSystem.disableBlend();

    }


    public static void open(ServerPlayerEntity player, DialogueInstance instance){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(()->{mc.setScreen(new DialougeGui(player, instance));});
    }

    public static void open(ServerPlayerEntity player, DialogueInstance instance, int index){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(()->{mc.setScreen(new DialougeGui(player, instance, index));});
    }



}
