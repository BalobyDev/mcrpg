package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class CameraAction {

    private Optional<Double> x_pos;
    private Optional<Double> y_pos;
    private Optional<Double> z_pos;
    private Optional<Integer> x_rot;
    private Optional<Integer> y_rot;

    public Codec<CameraAction> CODEC = RecordCodecBuilder.create(instacne -> instacne.group(
            Codec.DOUBLE.optionalFieldOf("x_pos").forGetter(CameraAction::getX_pos),
            Codec.DOUBLE.optionalFieldOf("y_pos").forGetter(CameraAction::getY_pos),
            Codec.DOUBLE.optionalFieldOf("z_pos").forGetter(CameraAction::getZ_pos),
            Codec.INT.optionalFieldOf("x_rot").forGetter(CameraAction::getX_rot),
            Codec.INT.optionalFieldOf("y_rot").forGetter(CameraAction::getY_rot)
            ).apply(instacne, CameraAction::new));

    public CameraAction(Optional<Double> x_pos, Optional<Double> y_pos, Optional<Double> z_pos,Optional<Integer> x_rot, Optional<Integer> y_rot){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.z_pos = z_pos;
        this.x_rot = x_rot;
        this.y_rot = y_rot;
    }

    public Optional<Double> getX_pos() {
        return x_pos;
    }

    public Optional<Double> getY_pos() {
        return y_pos;
    }

    public Optional<Double> getZ_pos() {
        return z_pos;
    }

    public Optional<Integer> getX_rot() {
        return x_rot;
    }

    public Optional<Integer> getY_rot() {
        return y_rot;
    }
}
