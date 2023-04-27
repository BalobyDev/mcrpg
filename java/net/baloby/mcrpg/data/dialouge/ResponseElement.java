package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.client.gui.PlayerResponseGui;
import net.baloby.mcrpg.data.characters.Npc;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseElement implements IDialogueElement {




    private List<Response> options;
    public DialogueChain chain;



    public ResponseElement(List<Response> options){
        this.options = options;

    }


    @Override
    public void open(ServerPlayerEntity player) {
        PlayerResponseGui.open(player,this);
    }

    @Override
    public DialogueChain getChain() {
        return chain;
    }

    public void setChain(DialogueChain chain){
        this.chain = chain;
        for(Response response : options){
            response.chain = chain;
        }
    }



    public List<Response> getOptions() {
        return options;
    }
}
