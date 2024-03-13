package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class Actor {

    private ResourceLocation npc;
    private double x;
    private double y;
    private double z;
    private double yRot;

    public static Codec<Actor> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("npc").forGetter(Actor::getNpc),
            Codec.DOUBLE.fieldOf("x").forGetter(Actor::getX),
            Codec.DOUBLE.fieldOf("y").forGetter(Actor::getY),
            Codec.DOUBLE.fieldOf("z").forGetter(Actor::getZ),
            Codec.DOUBLE.fieldOf("y_rot").forGetter(Actor::getYRot)
    ).apply(instance,Actor::new));

    public Actor(ResourceLocation npc, double x, double y, double z, double yRot){
        this.npc = npc;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yRot = yRot;
    }

    public ResourceLocation getNpc(){
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

    public double getYRot() {
        return yRot;
    }
}
