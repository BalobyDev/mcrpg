package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import java.util.*;

public class DialogueChain {

    private List<DialogueInstance> list;
    private HashMap<String,DialogueInstance> instances = new HashMap<>();

    public static final Codec<DialogueChain> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    DialogueInstance.CODEC.listOf().fieldOf("instances").forGetter(DialogueChain::getList))
            .apply(instance, DialogueChain::new));

    public DialogueChain(List<DialogueInstance> list){
        this.list = list;
        for (DialogueInstance instance : list){
            this.instances.put(instance.getId(),instance);
            instance.chain = this;
        }
    }

    public IDialogueElement getNext(DialogueInstance instance){
        if(instance.getResponses().isPresent()){
            ResponseElement responseElement = new ResponseElement(instance.getResponses().get());
            responseElement.setChain(this);
            return responseElement;
        }
        return instances.get(instance.getNext());
    }

    public void openIndex(ServerPlayerEntity player,String index){
        boolean flag = true;
        if(instances.containsKey(index)){
            if(instances.get(index).getPartyPresent().isPresent()){
                for(ResourceLocation location : instances.get(index).getPartyPresent().get()) {
                    if (!player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts().contains(location.toString())) flag = false;
                }
            }
            if(instances.get(index).getPartyAbsent().isPresent()){
                for(ResourceLocation location : instances.get(index).getPartyAbsent().get()){
                    if (player.getServer().overworld().getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts().contains(location.toString())) flag = false;

                }
            }
            if(flag) {
                instances.get(index).open(player);
            }
            else if(instances.containsKey(instances.get(index).getNext())){
                instances.get(instances.get(index).getNext()).open(player);
            }
        }
    }


    private List<DialogueInstance> getList(){
        return this.list;
    }


    public HashMap<String, DialogueInstance> getInstances(){
        return instances;
    }

    public DialogueInstance getFirst(){
        return instances.get("000");
    }

    public void start(ServerPlayerEntity player){
        this.getFirst().open(player);
    }
}
