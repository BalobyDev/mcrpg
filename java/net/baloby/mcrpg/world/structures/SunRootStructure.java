package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableList;
import com.ibm.icu.impl.Pair;
import com.mojang.serialization.Codec;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class SunRootStructure extends LocationStructure {

    public SunRootStructure() {
        super();
        Building felinaHouse = new Building(new ResourceLocation(mcrpg.MODID,"felina_house"), BlockPos.ZERO,Rotation.NONE);
        this.addBuilding(felinaHouse);
        Building smithShop = new Building(new ResourceLocation(mcrpg.MODID, "smith"), new BlockPos(25,0,0),Rotation.NONE);
        this.addBuilding(smithShop);
        Building bakery = new Building(new ResourceLocation(mcrpg.MODID, "bakery"),new BlockPos(0,0,30),Rotation.NONE);
        this.addBuilding(bakery);

        this.addNpc(Npcs.RANA.get(),new NpcOffset(new BlockPos(10,0,10), felinaHouse, Rotation.NONE));
        this.addNpc(Npcs.FELINA.get(),new NpcOffset(new BlockPos(10,11,10), felinaHouse, Rotation.NONE));
        this.addNpc(Npcs.ALEX.get(),new NpcOffset(new BlockPos(10,0,7), smithShop, Rotation.NONE));
        this.addNpc(Npcs.GUNTER.get(),new NpcOffset(new BlockPos(9,0,7), smithShop, Rotation.NONE));
    }

    @Override
    public String getName(){return "sunroot";}

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends LocationStructure.Start {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
            super(structureIn, chunkX, chunkZ, boundingBox, reference, seed, Heightmap.Type.WORLD_SURFACE);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
//            SunRootPieces.start(this.getStructure(), manager,pos,rotation,this.pieces,random);
            super.generatePieces(dynamicRegistries,generator,manager,chunkX,chunkZ,biome,noFeatureConfig);
        }
    }
}
