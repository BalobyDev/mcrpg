package net.baloby.mcrpg.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.baloby.mcrpg.data.dialouge.DialogueInstance;
import net.baloby.mcrpg.quest.ItemRequirement;
import net.baloby.mcrpg.quest.ItemReward;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.core.jmx.Server;

public class QuestAwardScreen extends Screen{

    private DialogueInstance instance;
    private ServerPlayerEntity player;
    private Quest quest;

    protected QuestAwardScreen(DialogueInstance instance, ServerPlayerEntity player, Quest quest) {
        super(new StringTextComponent("quest_award"));
        this.instance = instance;
        this.player = player;
        this.quest = quest;
    }

    protected void init(){
        Animation.sound(SoundEvents.EXPERIENCE_ORB_PICKUP);
        if(quest.getItemRequirement().isPresent()){
            ItemRequirement requirement = quest.getItemRequirement().get();

//            ItemStack stack = new ItemStack(Registry.ITEM.get(requirement.getItem()));
//            stack.setCount(requirement.getAmount());
//            this.minecraft.submitAsync(()->{player.inventory.removeItem(stack);});

            int debt = requirement.getAmount();
            this.removeItemType(Registry.ITEM.get(requirement.getItem()),requirement.getAmount());
        }
        if(quest.getItemReward().isPresent()){
            CompoundNBT reward = quest.getItemReward().get();
            ItemStack stack = ItemStack.of(reward);
            if(player.inventory.getFreeSlot()>-1) {
                this.player.addItem(stack);
            }
            else {
                ItemEntity itemEntity = new ItemEntity(player.getLevel(),player.position().x,player.position().y,player.position().z, stack);
                this.player.getLevel().addFreshEntity(itemEntity);
            }
        }
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        if(quest.getItemReward().isPresent()){
//            Item reward = quest.getItemReward().get().get();
//            this.drawCenteredString(matrixStack, this.font, new ItemStack(Registry.ITEM.get(reward.getItem())).getDisplayName().getString()+" x "+ reward.getAmount(),this.width/2,this.height/2+32,-1);
//            this.minecraft.getTextureManager().bind(new ResourceLocation(reward.getNamespace(),"textures/item/"+reward.getPath()+".png"));
//            this.blit(matrixStack,this.width/2 - 32,this.height/2-32,0,0,64,64,64,64);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }





    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if(this.instance.chain.getInstances().containsKey(instance.getNext())){
            instance.chain.openIndex(player,instance.getNext());
        }
        return super.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    public static void open(DialogueInstance instance, ServerPlayerEntity player, Quest quest){
        Minecraft mc = Minecraft.getInstance();
        mc.submitAsync(() -> {mc.setScreen(new QuestAwardScreen(instance, player, quest));});
    }

    public ItemStack removeItemType(Item p_223374_1_, int p_223374_2_) {
        ItemStack itemstack = new ItemStack(p_223374_1_, 0);

        for(int i = 35; i >= 0; --i) {
            ItemStack itemstack1 = this.player.inventory.getItem(i);
            if (itemstack1.getItem().equals(p_223374_1_)) {
                int j = p_223374_2_ - itemstack.getCount();
                ItemStack itemstack2 = itemstack1.split(j);
                itemstack.grow(itemstack2.getCount());
                if (itemstack.getCount() == p_223374_2_) {
                    break;
                }
            }
        }

        if (!itemstack.isEmpty()) {
            this.player.inventory.setChanged();
        }

        return itemstack;
    }
}
