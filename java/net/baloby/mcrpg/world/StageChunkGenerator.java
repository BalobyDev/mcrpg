package net.baloby.mcrpg.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;
import java.util.function.Supplier;

public class StageChunkGenerator extends ChunkGenerator {

    public static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.<Settings>create(instance ->
            instance.group(
                    RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(Settings::getBiomes),
                    Codec.INT.fieldOf("base").forGetter(Settings::getBaseHeight),
                    Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::getVerticalVariance),
                    Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::getHorizontalVariance),
                    Biome.CODEC.optionalFieldOf("biome").orElseGet(Optional::empty).forGetter((settings1) -> {
                        return Optional.of(settings1.biome);
                    })

            ).apply(instance, Settings::new));

    public static final Codec<StageChunkGenerator>  CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(StageChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(StageChunkGenerator::getArenaSettings)
            ).apply(instance, StageChunkGenerator::new));

    private final Settings settings;
    private final Registry<Biome> biomes;

    public StageChunkGenerator(Registry<Biome> registry, Settings settings) {
        super(new SingleBiomeProvider(settings.getBiome()), new DimensionStructuresSettings(false));
        this.biomes = registry;
        this.settings = settings;
    }

    public Settings getArenaSettings(){
        return settings;
    }

    public Registry<Biome> getBiomeRegistry(){
        return biomes;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return this;
    }

    @Override
    public void buildSurfaceAndBedrock(WorldGenRegion region, IChunk chunk) {
        ServerWorld world = region.getLevel();
    }

    @Override
    public void fillFromNoise(IWorld world, StructureManager structureManager, IChunk chunk) {

    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Type heightMapType) {
        return 0;
    }

    @Override
    public IBlockReader getBaseColumn(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(new BlockState[0]);
    }

    public static class Settings {
        private final int baseHeight;
        private final float verticalVariance;
        private final float horizontalVariance;
        private final Supplier<Biome> biome;
        private final Registry<Biome> biomes;


        public Settings(Registry<Biome> biomeRegistry, int baseHeight, float verticalVariance, float horizontalVariance, Optional<Supplier<Biome>> biome) {
            this.baseHeight = baseHeight;
            this.verticalVariance = verticalVariance;
            this.horizontalVariance = horizontalVariance;

            this.biomes = biomeRegistry;
            if(biome.isPresent()){
                this.biome = biome.get();
            }
            else {
                this.biome = () -> {
                    return biomeRegistry.getOrThrow(Biomes.MUSHROOM_FIELDS);
                };
            }
        }

        public Registry<Biome> getBiomes() {return biomes;}

        public int getBaseHeight(){return baseHeight;}

        public float getVerticalVariance(){return verticalVariance;}

        public float getHorizontalVariance(){return horizontalVariance;}

        public Biome getBiome(){return biome.get();}
    }
}