package net.baloby.mcrpg.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class CassandraHouse extends Structure<NoFeatureConfig> {

    public CassandraHouse() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return null;
    }
}
