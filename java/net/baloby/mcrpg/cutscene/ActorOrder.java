package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class ActorOrder {

    private ResourceLocation npc;
    private Optional<Integer> rotateY;
    private Optional<ResourceLocation> animation;
    private Optional<Double> tox;
    private Optional<Double> toz;

    public static Codec<ActorOrder> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("npc").forGetter(ActorOrder::getNpc),
            Codec.INT.optionalFieldOf("y_rot").forGetter(ActorOrder::getRotateY),
            Codec.DOUBLE.optionalFieldOf("to_x").forGetter(ActorOrder::getTox),
            Codec.DOUBLE.optionalFieldOf("to_z").forGetter(ActorOrder::getToz),
            ResourceLocation.CODEC.optionalFieldOf("anim").forGetter(ActorOrder::getAnimation)

    ).apply(instance, ActorOrder::new));

    public ActorOrder(ResourceLocation npc, Optional<Integer> rotateY, Optional<Double> tox, Optional<Double> toz, Optional<ResourceLocation> animation){
        this.npc = npc;
        this.rotateY = rotateY;
        this.tox = tox;
        this.toz = toz;
        this.animation = animation;
    }

    public ResourceLocation getNpc() {
        return npc;
    }

    public Optional<Integer> getRotateY() {
        return rotateY;
    }

    public Optional<ResourceLocation> getAnimation() {
        return animation;
    }

    public Optional<Double> getTox() {
        return tox;
    }

    public Optional<Double> getToz() {
        return toz;
    }
}
