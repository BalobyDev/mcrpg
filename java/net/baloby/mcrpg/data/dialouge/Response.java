package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.client.gui.ShopScreen;
import net.baloby.mcrpg.data.*;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.data.characters.ShopNpc;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

public class Response {

    public static final Codec<Response> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("text").forGetter(Response::getText),
            Codec.STRING.optionalFieldOf("next").forGetter(Response::getNext),
            ResourceLocation.CODEC.optionalFieldOf("quest").forGetter(Response::getQuest),
            ResourceLocation.CODEC.optionalFieldOf("next_dialogue").forGetter(Response::getNextDialogue),
            ResourceLocation.CODEC.optionalFieldOf("insert_dialogue").forGetter(Response::getInsertDialogue),
            DialogueInsert.CODEC.optionalFieldOf("next_insert").forGetter(Response::getNextInsert),
            ResourceLocation.CODEC.optionalFieldOf("dialogue").forGetter(Response::getDialogue),
            ResourceLocation.CODEC.optionalFieldOf("shop").forGetter(Response::getShop),
            ResourceLocation.CODEC.optionalFieldOf("party_add").forGetter(Response::getPartyAdd),
            QuestCheck.CODEC.optionalFieldOf("quest_check").forGetter(Response::getQuestCheck)
    ).apply(instance, Response::new));


    public String text;
    private Optional<String> next;
    private Optional<ResourceLocation> quest;
    private Optional<ResourceLocation> nextDialogue;
    private Optional<ResourceLocation> dialogue;
    private Optional<ResourceLocation> insertDialogue;
    private Optional<DialogueInsert> nextInsert;
    private Optional<ResourceLocation> shop;
    private Optional<ResourceLocation> partyAdd;
    private Optional<QuestCheck> questCheck;
    public DialogueChain chain;
    public DialogueInstance instance;

    public Response(String text, Optional<String> next, Optional<ResourceLocation> quest, Optional<ResourceLocation> nextDialogue,
                    Optional<ResourceLocation> insertDialogue, Optional<DialogueInsert> nextInsert, Optional<ResourceLocation> dialogue,
                    Optional<ResourceLocation> shop, Optional<ResourceLocation> partyAdd, Optional<QuestCheck> questCheck){
        this.text = text;
        this.next = next;
        this.quest = quest;
        this.nextDialogue = nextDialogue;
        this.dialogue = dialogue;
        this.insertDialogue = insertDialogue;
        this.nextInsert = nextInsert;
        this.shop = shop;
        this.partyAdd = partyAdd;
        this.questCheck = questCheck;
    }

    public void select(ServerPlayerEntity player){
        if(this.next.isPresent()) {
            this.chain.openIndex(player, this.getNext().get());
        } else if (questCheck.isPresent()) {
            QuestCheck check = questCheck.get();
            String tsugi = check.getQuest().check(player) ? check.getPass() : check.getFail();
            this.chain.openIndex(player,tsugi);
            if(check.getQuest().check(player)){
                Npc npc = Registration.NPC_REGISTRY.get().getValue(this.instance.getNpc()).create();
                INpcData npcData = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
                CompoundNBT nbt = npcData.getNbts();
                npc.load((CompoundNBT) nbt.get(npc.getType().getRegistryName().getPath()));
                npc.setDialogueIndex(questCheck.get().getPassNextDialogue());
                npcData.saveNpc(npc);
                npcData.loadNpcs();
                if(questCheck.get().getInsert().isPresent()){
                    this.nextInsert = questCheck.get().getInsert();
                }

            }
        } else if (shop.isPresent()) {
            Npc npc = Registration.NPC_REGISTRY.get().getValue(shop.get()).create();
            if(npc instanceof ShopNpc){
                ShopScreen.open(((ShopNpc) npc).getShop(),player);
            }
        }
        if(quest.isPresent()){
            Quest newQuest = ModSetup.QUEST_MANAGER.getData(quest.get());
            newQuest.assign(player);
        }
        if(nextDialogue.isPresent()){
            Npc npc = Registration.NPC_REGISTRY.get().getValue(this.instance.getNpc()).create();
            INpcData npcData = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            CompoundNBT nbt = npcData.getNbts();
            npc.load((CompoundNBT) nbt.get(npc.getType().getRegistryName().getPath()));
            npc.setDialogueIndex(nextDialogue.get());
            npcData.saveNpc(npc);
            npcData.loadNpcs();
        }
        if(partyAdd.isPresent()){
            Npc npc = Registration.NPC_REGISTRY.get().getValue(this.partyAdd.get()).create();
            if(npc instanceof BattleNpc) {
                BattleNpc battleNpc = (BattleNpc) npc;
                player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().addPartyMember(battleNpc.getType());
            }
        }

        if(nextInsert.isPresent()){
            Npc npc = Registration.NPC_REGISTRY.get().getValue(instance.getNpc()).create();
            INpcData npcData = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            CompoundNBT nbt = npcData.getNbts();
            npc.load((CompoundNBT) nbt.get(nextInsert.get().getNpc().getPath()));
            npc.setDialogueInsert(nextInsert.get());
            npcData.saveNpc(npc);
            npcData.loadNpcs();
        }

        if(insertDialogue.isPresent()){
            CompoundNBT nbt = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts().getCompound(insertDialogue.get().getPath()).getCompound("insert");
            ResourceLocation location = new ResourceLocation(nbt.getString("dialogue"));
            Map<ResourceLocation, DialogueChain> map = ModSetup.DIALOGUE_MANAGER.data;
            if(map.containsKey(location)) map.get(location).start(player);
        }

    }

    public String getText(){return text;}

    public Optional<String> getNext(){
        return next;
    }

    public Optional<ResourceLocation> getQuest(){return this.quest;}
    
    public Optional<ResourceLocation> getNextDialogue(){return nextDialogue;}

    public Optional<ResourceLocation> getInsertDialogue(){return insertDialogue;}

    public Optional<DialogueInsert> getNextInsert(){return nextInsert;}

    public Optional<ResourceLocation> getShop(){return shop;}

    public Optional<ResourceLocation> getPartyAdd(){return partyAdd;}

    public Optional<QuestCheck> getQuestCheck(){return this.questCheck;}

    public Optional<ResourceLocation> getDialogue() {
        return dialogue;
    }
}
