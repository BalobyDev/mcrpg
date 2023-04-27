package net.baloby.mcrpg.data;

import com.mojang.serialization.Lifecycle;
import net.baloby.mcrpg.data.characters.NpcType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NpcRegistry extends DefaultedRegistry<NpcType<?>> implements IForgeRegistry<NpcType<?>> {

    public NpcRegistry(String p_i232506_1_, RegistryKey<? extends Registry<NpcType<?>>> p_i232506_2_, Lifecycle p_i232506_3_) {
        super(p_i232506_1_, p_i232506_2_, p_i232506_3_);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }

    @Override
    public Class<NpcType<?>> getRegistrySuperType() {
        return null;
    }

    @Override
    public void register(NpcType<?> value) {

    }

    @Override
    public void registerAll(NpcType<?>... values) {

    }

    @Override
    public boolean containsKey(ResourceLocation key) {
        return false;
    }

    @Override
    public boolean containsValue(NpcType<?> value) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nullable
    @Override
    public NpcType<?> getValue(ResourceLocation key) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getKey(NpcType<?> value) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getDefaultKey() {
        return null;
    }

    @Nonnull
    @Override
    public Set<ResourceLocation> getKeys() {
        return null;
    }

    @Nonnull
    @Override
    public Collection<NpcType<?>> getValues() {
        return null;
    }

    @Nonnull
    @Override
    public Set<Map.Entry<RegistryKey<NpcType<?>>, NpcType<?>>> getEntries() {
        return null;
    }

    @Override
    public <T> T getSlaveMap(ResourceLocation slaveMapName, Class<T> type) {
        return null;
    }

    @Override
    public Iterator<NpcType<?>> iterator() {
        return null;
    }
}
