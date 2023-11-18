package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.client.gui.DialougeGui;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.INpcData;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Optional;

public class DialogueInstance implements IDialogueElement  {

    public DialogueChain chain;
    private String id;
    private ResourceLocation npc;
    private String text;
    private String next;
    private Optional<ResourceLocation> questComplete;
    private Optional<List<Response>> responses;
    private Optional<List<ResourceLocation>> partyPresent;
    private Optional<List<ResourceLocation>> partyAbsent;
    private Optional<List<ResourceLocation>> shopUpdate;
    private Optional<Swap> swap;
    private Optional<DialogueSet> set;

    public static final Codec<DialogueInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("id").forGetter(DialogueInstance::getId),
                    ResourceLocation.CODEC.fieldOf("speaker").forGetter(DialogueInstance::getNpc),
                    ResourceLocation.CODEC.optionalFieldOf("quest_complete").forGetter(DialogueInstance::getQuestComplete),
                    Codec.STRING.fieldOf("text").forGetter(DialogueInstance::getMessage),
                    Codec.STRING.optionalFieldOf("next").orElseGet(Optional::empty).forGetter((generator -> {
                        return Optional.of(generator.getNext());})),
                    Response.CODEC.listOf().optionalFieldOf("responses").forGetter(DialogueInstance::getResponses),
                    ResourceLocation.CODEC.listOf().optionalFieldOf("party_present").forGetter(DialogueInstance::getPartyPresent),
                    ResourceLocation.CODEC.listOf().optionalFieldOf("party_absent").forGetter(DialogueInstance::getPartyAbsent),
                    ResourceLocation.CODEC.listOf().optionalFieldOf("shop_update").forGetter(DialogueInstance::getPartyAbsent),
                    Swap.CODEC.optionalFieldOf("swap").forGetter(DialogueInstance::getSwap),
                    DialogueSet.CODEC.optionalFieldOf("set").forGetter(DialogueInstance::getSet))
            .apply(instance,DialogueInstance::new));


    public DialogueInstance(String id, ResourceLocation npc, Optional<ResourceLocation> questComplete, String text, Optional<String> next, Optional<List<Response>> responses,
                            Optional<List<ResourceLocation>> partyPresent, Optional<List<ResourceLocation>> partyAbsent, Optional<List<ResourceLocation>> shopUpdate,
                            Optional<Swap> swap, Optional<DialogueSet> set){
        this.id = id;
        this.text = text;
        this.questComplete = questComplete;
        if(next.isPresent()){
            this.next = next.get();
        }
        else {
            this.next = "end";
        }
        this.npc = npc;
        this.responses = responses;
        if(responses.isPresent()){
            for(Response response : responses.get()){
                response.instance = this;
            }
        }
        this.partyPresent = partyPresent;
        this.partyAbsent = partyAbsent;
        this.shopUpdate = shopUpdate;
        this.swap = swap;
        this.set = set;
    }

    public void open(ServerPlayerEntity player){
        if(set.isPresent()){
            Npc npc = Registration.NPC_REGISTRY.get().getValue(set.get().getNpc()).create();
            INpcData npcData = player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get();
            CompoundNBT nbt = npcData.getNbts();
            npc.load((CompoundNBT) nbt.get(npc.getType().getRegistryName().toString()));
            npc.setDialogueIndex(set.get().getDialogue());
            npcData.saveNpc(npc);
            npcData.loadNpcs();
        }
        DialougeGui.open(player,this);
    }

    public String getId(){return id;}

    public ResourceLocation getNpc() {
        return npc;
    }

    public String getMessage() {
        return text;
    }

    public String getNext() {
        return next;
    }

    public Optional<List<Response>> getResponses(){
        return responses;
    }

    @Override
    public DialogueChain getChain() {
        return chain;
    }

    public Optional<ResourceLocation> getQuestComplete() {
        return questComplete;
    }

    public Optional<List<ResourceLocation>> getPartyPresent(){return partyPresent;}

    public Optional<List<ResourceLocation>> getPartyAbsent(){return partyAbsent;}

    public Optional<List<ResourceLocation>> getShopUpdate(){return shopUpdate;}

    public Optional<Swap> getSwap(){return swap;}

    public Optional<DialogueSet> getSet(){return set;}

}
