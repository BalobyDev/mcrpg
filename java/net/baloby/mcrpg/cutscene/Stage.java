package net.baloby.mcrpg.cutscene;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.setup.ModDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class Stage {

    private ResourceLocation world;
    private ResourceLocation piece;
    private Optional<Integer> x_offset;
    private Optional<Integer> y_offset;
    private Optional<Integer> z_offset;

    public static Codec<Stage> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("world").forGetter(Stage::getWorld),
            ResourceLocation.CODEC.fieldOf("piece").forGetter(Stage::getPiece),
            Codec.INT.optionalFieldOf("x_offset").forGetter(Stage::getX_offset),
            Codec.INT.optionalFieldOf("y_offset").forGetter(Stage::getY_offset),
            Codec.INT.optionalFieldOf("z_offset").forGetter(Stage::getZ_offset)
    ).apply(instance, Stage::new));

    public Stage(ResourceLocation world, ResourceLocation piece, Optional<Integer> x, Optional<Integer> y, Optional<Integer> z){
        this.world = world;
        this.piece = piece;
        this.x_offset = x;
        this.y_offset = y;
        this.z_offset = z;

    }

    public ServerWorld getLevel(MinecraftServer server){
        ServerWorld world = server.getLevel(ModDimensions.OVERWORLD_STAGE);
        for (ServerWorld level : server.getAllLevels()){
            if(level.dimension().location().equals(this.getWorld())){
                world = level;
            }
        }
        return world;
    }

    public ResourceLocation getPiece() {
        return piece;
    }

    public Optional<Integer> getX_offset() {
        return x_offset;
    }

    public Optional<Integer> getY_offset() {
        return y_offset;
    }

    public Optional<Integer> getZ_offset() {
        return z_offset;
    }

    public ResourceLocation getWorld() {
        return world;
    }
}
