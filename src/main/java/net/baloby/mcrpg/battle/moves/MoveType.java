package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class MoveType<T extends Move> implements IForgeRegistryEntry<MoveType<?>> {


    private T npc;
    private final MoveType.IFactory<T> factory;
    private ResourceLocation registryName;
    public static final Class<MoveType<?>> MOVE_GENERIC = (Class<MoveType<?>>) ((Class<?>)MoveType.class);

    public MoveType(IFactory factory){
        this.factory = factory;

    }

    @Override
    public MoveType setRegistryName(ResourceLocation name) {
        this.registryName = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public Class<MoveType<?>> getRegistryType() {
        return null;
    }

    public Move create(){
        return factory.create();
    }


    public interface IFactory<T extends Move> {
        T create();
    }
}
