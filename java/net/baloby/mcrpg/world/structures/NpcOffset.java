package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class NpcOffset {

    private BlockPos pos;
    private Building building;
    private Rotation rotation;

    public NpcOffset(BlockPos pos, Building building, Rotation rotation){
        this.pos = pos;
        this.building = building;
        this.rotation = rotation;
    }



    public BlockPos getPos() {
        return pos;
    }

    public Building getBuilding(){
        return building;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
