package net.baloby.mcrpg.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.network.PacketTest;
import net.baloby.mcrpg.network.RpgNetwork;
import net.baloby.mcrpg.setup.ModDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.server.ServerWorld;
import org.lwjgl.system.CallbackI;

public class ArenaChunkGenerator extends ChunkGenerator {

    public static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("base").forGetter(Settings::getBaseHeight),
            Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::getVerticalVariance),
            Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::getHorizontalVariance),
            Codec.STRING.fieldOf("arena").forGetter(Settings::getArena),
            Codec.INT.fieldOf("xoffset").forGetter(Settings::getXOffset),
            Codec.INT.fieldOf("zoffset").forGetter(Settings::getZOffset)
    ).apply(instance,Settings::new));

    public static final Codec<ArenaChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(ArenaChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(ArenaChunkGenerator::getArenaSettings)
            ).apply(instance,ArenaChunkGenerator::new));

    private final Settings settings;
    private final Registry<Biome> biomes;

    public ArenaChunkGenerator(Registry<Biome> registry,Settings settings) {
        super(new SingleBiomeProvider(registry.getOrThrow(Biomes.MUSHROOM_FIELDS)), new DimensionStructuresSettings(false));
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
        world.getServer().submitAsync(()->{StructureGen.placeManually(new BlockPos(-9+settings.getXOffset(),100,-6+settings.getZOffset()),world, settings.arena);
        });



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

    private static class Settings {
        private final int baseHeight;
        private final float verticalVariance;
        private final float horizontalVariance;
        private final String arena;
        private final int xOffset;
        private final int zOffset;

        private Settings(int baseHeight, float verticalVariance, float horizontalVariance, String arena, int xOffset, int zOffset) {
            this.baseHeight = baseHeight;
            this.verticalVariance = verticalVariance;
            this.horizontalVariance = horizontalVariance;
            this.arena = arena;
            this.xOffset = xOffset;
            this.zOffset = zOffset;
        }

        public int getBaseHeight(){return baseHeight;}

        public float getVerticalVariance(){return verticalVariance;}

        public float getHorizontalVariance(){return horizontalVariance;}


        public String getArena() {return arena;}

        public int getXOffset(){return xOffset;}

        public int getZOffset(){return zOffset;}

    }
}
