package net.baloby.mcrpg.world.structures;

import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

public class NpcOffset {

    private Vector3d pos;
    private Building building;
    private Rotation rotation;

    public NpcOffset(Vector3d pos, Building building, Rotation rotation){
        this.pos = pos;
        this.building = building;
        this.rotation = rotation;
    }

    public Vector3d getPos() {
        return pos;
    }

    public void rotatePos(Rotation rotation){
        switch(rotation) {
        case NONE:
        default:
            return;
        case CLOCKWISE_90:
            pos = new Vector3d(-pos.z(), pos.y(), pos.x());
        case CLOCKWISE_180:
            pos = new Vector3d(-pos.x(), pos.y(), -pos.z());
        case COUNTERCLOCKWISE_90:
            pos = new Vector3d(pos.z(), pos.y(), -pos.x());
    }}

    public Building getBuilding(){
        return building;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
