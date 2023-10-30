package net.baloby.mcrpg.setup;

import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraftforge.fml.RegistryObject;

public class ModBiomes {
    public static RegistryObject<Biome> NETHER_BIOME = Registration.BIOMES.register("nether_biome",()->
            new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.NETHER).depth(0.1f)
                    .scale(0.1f).temperature(0.2f).downfall(0.0f)
                    .specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(110177225).build())
                    .mobSpawnSettings(MobSpawnInfo.EMPTY)
                    .generationSettings(BiomeGenerationSettings.EMPTY)
                    .build());

    static void register(){}
}
