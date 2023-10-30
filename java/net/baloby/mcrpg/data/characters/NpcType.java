package net.baloby.mcrpg.data.characters;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class NpcType<T extends Npc> implements IForgeRegistryEntry<NpcType<?>>{



    private T npc;
    private final NpcType.IFactory<T> factory;
    public static final Class<NpcType<?>> NPC_GENERIC = (Class<NpcType<?>>) ((Class<?>)NpcType.class);
    private ResourceLocation registryName;
    private int id;

    public NpcType(IFactory factory){
        this.factory = factory;
    }
    
    @Override
    public NpcType setRegistryName(ResourceLocation name) {
        this.registryName = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public Class<NpcType<?>> getRegistryType() {
        return null;
    }

    public Npc create(){
        return factory.create();
    }

    public Npc listCreate(){
        Npc npc = create();
        npc.addToList();
        return npc;
    }

    public interface IFactory<T extends Npc>{
        T create();
    }
}
