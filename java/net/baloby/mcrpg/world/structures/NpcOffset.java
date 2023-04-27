package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class NpcOffset {

    private BlockPos pos;
    private BlockPos structurePos;
    private Rotation rotation;

    public NpcOffset(BlockPos pos, BlockPos structurePos, Rotation rotation){
        this.pos = pos;
        this.structurePos = structurePos;
        this.rotation = rotation;
    }



    public BlockPos getPos() {
        return pos;
    }

    public BlockPos getStructurePos(){
        return structurePos;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
