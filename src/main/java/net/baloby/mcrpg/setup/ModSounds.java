package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModSounds {
    public static final RegistryObject<SoundEvent> BATTLETHEME = Registration.SOUNDS.register("battletheme", () ->
            new SoundEvent(new ResourceLocation(mcrpg.MODID,"battle_theme")));
    public static final RegistryObject<SoundEvent> OOH = Registration.SOUNDS.register("ooh",()->
            new SoundEvent(new ResourceLocation(mcrpg.MODID, "ooh")));

    static void register(){}

}
