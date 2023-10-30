package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableMap;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class COTDCathedralPieces {


    public static ResourceLocation MAIN_HALL = new ResourceLocation(mcrpg.MODID,"cathedral_main_hall");


    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.of(
            MAIN_HALL,new BlockPos(0,0,0));

    public static void start(LocationStructure structure, TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random){
        int x = pos.getX();
        int z = pos.getZ();
        BlockPos rotationOffSet = new BlockPos(0,-1,0).rotate(rotation);
        BlockPos blockPos = rotationOffSet.offset(x,pos.getY(),z);
        pieceList.add(new COTDCathedralPieces.Piece(templateManager,MAIN_HALL, blockPos, rotation));
//        structure.getPositions().put(Npcs.JEAN.get(),new NpcOffset(new BlockPos(10,3,35),blockPos,rotation));
    }
    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(ModStructures.CATHEDRAL_PIECE, nbt);
            this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.setupPiece(templateManager);
        }

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(ModStructures.CATHEDRAL_PIECE, 0);
            this.resourceLocation = resourceLocationIn;
            BlockPos blockPos = OFFSET.get(resourceLocation);
            this.templatePosition = pos.offset(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT nbt) {
            super.addAdditionalSaveData(nbt);
            nbt.putString("Template", this.resourceLocation.toString());
            nbt.putString("Rot", this.rotation.name());
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.get(this.resourceLocation);
            PlacementSettings placementSettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementSettings);
        }

        @Override
        protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, IServerWorld p_186175_3_, Random p_186175_4_, MutableBoundingBox p_186175_5_) {

        }
    }
}
