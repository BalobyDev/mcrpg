package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableMap;
import com.ibm.icu.impl.locale.XCldrStub;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CaveArenaPieces {

    private static final ResourceLocation PIECE = new ResourceLocation(mcrpg.MODID, "cave_arena");
    private static final Map<ResourceLocation,BlockPos> OFFSET = ImmutableMap.of(PIECE, new BlockPos(0,1,0));

    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random){
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffSet = new BlockPos(0,0,0).rotate(rotation);
        BlockPos blockPos = rotationOffSet.offset(x, pos.getY(),z);
        pieceList.add(new CaveArenaPieces.Piece(templateManager,PIECE, blockPos, rotation));
    }
    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(ModStructures.CAP, 0);
            this.resourceLocation = resourceLocationIn;
            BlockPos blockpos = CaveArenaPieces.OFFSET.get(resourceLocation);
            this.templatePosition = pos.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(ModStructures.CAP, tagCompound);
            this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }
        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, IServerWorld p_186175_3_, Random p_186175_4_, MutableBoundingBox p_186175_5_) {
            
        }

        /*
         * If you added any data marker structure blocks to your structure, you can access and modify them here.
         * In this case, our structure has a data maker with the string "chest" put into it. So we check to see
         * if the incoming function is "chest" and if it is, we now have that exact position.
         *
         * So what is done here is we replace the structure block with
         * a chest and we can then set the loottable for it.
         *
         * You can set other data markers to do other behaviors such as spawnLoad a random mob in a certain spot,
         * randomize what rare block spawns under the floor, or what item an Item Frame will have.
         */

        
    }
}
