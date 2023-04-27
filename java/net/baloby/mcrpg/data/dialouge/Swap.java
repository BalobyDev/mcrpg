package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class Swap {

    private ResourceLocation dialogue;
    private String id;

    public static Codec<Swap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("dialogue").forGetter(Swap::getDialogue),
            Codec.STRING.optionalFieldOf("id").orElseGet(Optional::empty).forGetter((swap ->{
                return Optional.of(swap.getId());}))
    ).apply(instance, Swap::new));

    public Swap(ResourceLocation dialogue, Optional<String> id){
        this.dialogue = dialogue;
        this.id = "000";
        if(id.isPresent()){
            this.id = id.get();
        }
    }

    public ResourceLocation getDialogue(){return dialogue;}

    public String getId(){return id;}
}
