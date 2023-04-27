package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;

public class DialogueSet {

    private ResourceLocation npc;
    private ResourceLocation dialogue;

    public static Codec<DialogueSet> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("npc").forGetter(DialogueSet::getNpc),
            ResourceLocation.CODEC.fieldOf("dialogue").forGetter(DialogueSet::getDialogue))
            .apply(instance,DialogueSet::new));

    public DialogueSet(ResourceLocation npc, ResourceLocation dialogue){
        this.npc = npc;
        this.dialogue = dialogue;
    }

    public ResourceLocation getNpc(){
        return npc;
    }

    public ResourceLocation getDialogue(){
        return dialogue;
    }
}
