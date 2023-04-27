package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.setup.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class Theme extends TickableSound {
    private Battle battle;
    private ClientPlayerEntity player;

    public Theme(Battle battle) {
        super(ModSounds.BATTLETHEME.get(), SoundCategory.MUSIC);
        this.battle = battle;
        this.looping = true;
        this.volume = 0.4F;
        this.pitch = 1.0F;
        this.player = Minecraft.getInstance().player;

    }

    public boolean canPlaySound(){return !battle.isOver;}

    public boolean canStartSilent() {
        return true;
    }


    @Override
    public void tick() {
        if(battle.isOver)stop();
        else {
            this.x = player.position().x;
            this.y = player.position().y;
            this.z = player.position().z;
        }
    }
}
