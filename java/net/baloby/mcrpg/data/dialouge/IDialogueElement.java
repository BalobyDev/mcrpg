package net.baloby.mcrpg.data.dialouge;

import net.minecraft.entity.player.ServerPlayerEntity;

public interface IDialogueElement {

    void open(ServerPlayerEntity player);

    DialogueChain getChain();

}
