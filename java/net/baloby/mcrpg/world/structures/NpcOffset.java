package net.baloby.mcrpg.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.quest.ItemRequirement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Optional;
import java.util.Random;

public class NpcOffset {

    public static Codec<NpcOffset> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("npc").forGetter(NpcOffset::getNpc),
            Codec.DOUBLE.fieldOf("x").forGetter(NpcOffset::getX),
            Codec.DOUBLE.fieldOf("y").forGetter(NpcOffset::getY),
            Codec.DOUBLE.fieldOf("z").forGetter(NpcOffset::getZ)
            ).apply(instance, NpcOffset::new));

    private ResourceLocation npc;
    private Vector3d pos;
    private boolean rotated = false;
    private double x;
    private double y;
    private double z;


    public NpcOffset(ResourceLocation npc, double x, double y, double z){
        this.npc = npc;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pos = new Vector3d(x,y,z);
    }

    public Vector3d getPos() {
        return pos;
    }

    public void rotatePos(Rotation rotation){
        this.rotated = true;
        if(rotation.equals(Rotation.CLOCKWISE_90)) {
            pos = new Vector3d(-pos.z(), pos.y(), pos.x());
            return;
        }

        else if(rotation.equals(Rotation.CLOCKWISE_180)) {
            pos = new Vector3d(-pos.x(), pos.y(), -pos.z());
            return;
        }
        else if (rotation.equals(Rotation.COUNTERCLOCKWISE_90)) {
            pos = new Vector3d(pos.z(), pos.y(), -pos.x());
            return;
        }

        else {
            pos = new Vector3d(pos.x(), pos.y(), pos.z());
            return;
        }

    }


    public ResourceLocation getNpc() {
        return npc;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public boolean isRotated() {
        return rotated;
    }
}
