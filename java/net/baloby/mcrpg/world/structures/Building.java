package net.baloby.mcrpg.world.structures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class Building{
    private ResourceLocation piece;
    private BlockPos pos;
    private Rotation rotation;

    public Building(ResourceLocation piece, BlockPos pos, Rotation rotation){
        this.piece = piece;
        this.pos = pos;
        this.rotation = rotation;
    }


    public ResourceLocation getPiece(){
        return piece;
    }
    public BlockPos getPos() {
        return pos;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
