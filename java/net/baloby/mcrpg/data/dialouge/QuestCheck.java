package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.quest.Quest;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class QuestCheck {

    private ResourceLocation questLocation;
    private ResourceLocation passNextDialogue;
    private String pass;
    private String fail;
    private Optional<DialogueInsert> insert;

    public static final Codec<QuestCheck> CODEC = RecordCodecBuilder.create(instance -> instance.group(

        ResourceLocation.CODEC.fieldOf("quest").forGetter(QuestCheck::getQuestLocation),
        ResourceLocation.CODEC.fieldOf("pass_dialogue").forGetter(QuestCheck::getPassNextDialogue),
        DialogueInsert.CODEC.optionalFieldOf("insert").forGetter(QuestCheck::getInsert),
        Codec.STRING.fieldOf("pass").forGetter(QuestCheck::getPass),
        Codec.STRING.fieldOf("fail").forGetter(QuestCheck::getFail)
    ).apply(instance,QuestCheck::new));

    public QuestCheck(ResourceLocation questLocation, ResourceLocation passNextDialogue, Optional<DialogueInsert> insert, String pass, String fail){
        this.questLocation = questLocation;
        this.passNextDialogue = passNextDialogue;
        this.insert = insert;
        this.pass = pass;
        this.fail = fail;
    }

    public ResourceLocation getQuestLocation(){
        return questLocation;
    }

    public Quest getQuest(){
        return ModSetup.QUEST_MANAGER.getData(questLocation);
    }

    public String getPass() {
        return pass;
    }

    public String getFail() {
        return fail;
    }

    public ResourceLocation getPassNextDialogue(){return passNextDialogue;}

    public Optional<DialogueInsert> getInsert(){return insert;}
}
