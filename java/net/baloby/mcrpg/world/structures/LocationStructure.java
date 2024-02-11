package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.UniqueFeatures.UniqueStructuresCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.baloby.mcrpg.world.StructureGen;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class LocationStructure extends UniqueStructure {

    protected Map<NpcType, NpcOffset> positions = new HashMap<>();
    protected ArrayList<Building> buildings = new ArrayList<>();


    public LocationStructure() {
        super(NoFeatureConfig.CODEC);
    }

    public Map<NpcType, NpcOffset> getPositions(){return positions;}

    public void addBuilding(Building building){
        this.buildings.add(building);
    }

    public IStartFactory getStartFactory() {
        return (structureIn, chunkX, chunkZ, boundingBox, reference, seed) -> new Start((LocationStructure) structureIn, chunkX, chunkZ, boundingBox, reference, seed);
    }



    public static class Start extends UniqueStructure.Start{
        public Start(LocationStructure structureIn, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
            super(structureIn, chunkX, chunkZ, boundingBox, reference, seed);

        }
    }
}
