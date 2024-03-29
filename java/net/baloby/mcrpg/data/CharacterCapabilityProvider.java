package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CharacterCapabilityProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(INpcData.class)

    public static Capability<INpcData> CHAR_CAP = null;

    private LazyOptional<INpcData> instance = LazyOptional.of(()->CHAR_CAP.getDefaultInstance());

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == CHAR_CAP ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        if(CHAR_CAP!=null)nbt = (CompoundNBT) CHAR_CAP.getStorage().writeNBT(CHAR_CAP,this.getInstance(),null);
        return nbt;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if(CHAR_CAP == null)return;
        CHAR_CAP.getStorage().readNBT(CHAR_CAP, this.getInstance(), null, nbt);

    }

    private INpcData getInstance(){
        return this.instance.orElseThrow(()->new IllegalStateException("Unable to obtain capability instace"));
    }
}
