package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DialogueInsert {

    private ResourceLocation npc;
    private ResourceLocation dialogue;
    private String response;


    public static Codec<DialogueInsert> CODEC = RecordCodecBuilder.create(instance-> instance.group(
            ResourceLocation.CODEC.fieldOf("npc").forGetter(DialogueInsert::getNpc),
            ResourceLocation.CODEC.fieldOf("dialogue").forGetter(DialogueInsert::getDialogue),
            Codec.STRING.fieldOf("response").forGetter(DialogueInsert::getResponse)
            ).apply(instance,DialogueInsert::new));

    public DialogueInsert(ResourceLocation npc, ResourceLocation dialogue, String response){
        this.npc = npc;
        this.dialogue = dialogue;
        this.response = response;
    }


    public ResourceLocation getDialogue() {
        return dialogue;
    }

    public ResourceLocation getNpc(){return npc;}

    public String getResponse() {
        return response;
    }
}
