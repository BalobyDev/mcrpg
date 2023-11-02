package net.baloby.mcrpg.cutscene;

import net.baloby.mcrpg.client.gui.DialougeGui;
import net.baloby.mcrpg.data.dialouge.DialogueInstance;
import net.minecraft.entity.player.ServerPlayerEntity;

public class VNCutsceneGui extends DialougeGui {
    protected VNCutsceneGui(ServerPlayerEntity player, DialogueInstance instance) {
        super(player, instance);
    }
}
