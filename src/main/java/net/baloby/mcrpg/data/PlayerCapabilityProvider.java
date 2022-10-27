package net.baloby.mcrpg.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerCapabilityProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(ICharProfile.class)

    public static Capability<ICharProfile> CHAR_CAP = null;

    private LazyOptional<ICharProfile> instance = LazyOptional.of(()->CHAR_CAP.getDefaultInstance());

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == CHAR_CAP ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbt = (CompoundNBT) CHAR_CAP.getStorage().writeNBT(CHAR_CAP,this.getInstance(),null);
        return nbt;
    }


    @Override
    public void deserializeNBT(INBT nbt) {
        CHAR_CAP.getStorage().readNBT(CHAR_CAP, this.getInstance(), null, nbt);

    }

    private ICharProfile getInstance(){
        return this.instance.orElseThrow(()->new IllegalStateException("Unable to obtain capability instace"));
    }
}
