package net.baloby.mcrpg.setup;

import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
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

    public static RegistryObject<Biome> OVERWORLD_STAGE_BIOME = Registration.BIOMES.register("overworld_stage_biome",()->
            new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.NETHER).depth(0.1f)
                    .scale(0.1f).temperature(0.2f).downfall(0.0f)
                    .specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(calculateSkyColor(0.8f)).build())
                    .mobSpawnSettings(MobSpawnInfo.EMPTY)
                    .generationSettings(BiomeGenerationSettings.EMPTY)
                    .build());


    private static int calculateSkyColor(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    static void register(){}
}
