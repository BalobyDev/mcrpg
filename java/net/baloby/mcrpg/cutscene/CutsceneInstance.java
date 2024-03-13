package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.data.dialouge.DialogueInstance;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Optional;

public class CutsceneInstance {

    private DialogueInstance instance;
    private Optional<Double> x;
    private Optional<Double> y;
    private Optional<Double> z;
    private Vector3d pos;

    public static Codec<CutsceneInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            DialogueInstance.CODEC.fieldOf("dialogue").forGetter(CutsceneInstance::getInstance),
            Codec.DOUBLE.optionalFieldOf("x").forGetter(CutsceneInstance::getX),
            Codec.DOUBLE.optionalFieldOf("y").forGetter(CutsceneInstance::getY),
            Codec.DOUBLE.optionalFieldOf("z").forGetter(CutsceneInstance::getZ)
    ).apply(instance,CutsceneInstance::new));

    public CutsceneInstance(DialogueInstance instance, Optional<Double> x, Optional<Double> y, Optional<Double> z){
        this.instance = instance;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public DialogueInstance getInstance() {
        return instance;
    }

    public Optional<Double> getX() {
        return x;
    }

    public Optional<Double> getY() {
        return y;
    }

    public Optional<Double> getZ() {
        return z;
    }
}
