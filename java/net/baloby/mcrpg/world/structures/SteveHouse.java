package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SteveHouse extends UniqueStructure {


    public SteveHouse() {
        super(NoFeatureConfig.CODEC);
        this.location = new ResourceLocation(mcrpg.MODID,"steve_house");
    }


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return (structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn) -> new Start((UniqueStructure) structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }
}
