package net.baloby.mcrpg.data.UniqueFeatures;

import net.baloby.mcrpg.data.ICharProfile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UniqueFeaturesCapabilityProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IUniqueFeatures.class)

    public static Capability<IUniqueFeatures> FEATURES_CAP = null;

    private LazyOptional<IUniqueFeatures> instance = LazyOptional.of(()->FEATURES_CAP.getDefaultInstance());



    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == FEATURES_CAP ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        if(FEATURES_CAP!=null)nbt = (CompoundNBT) FEATURES_CAP.getStorage().writeNBT(FEATURES_CAP,this.getInstance(),null);
        return nbt;    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if (FEATURES_CAP != null){
            FEATURES_CAP.getStorage().readNBT(FEATURES_CAP, this.getInstance(),null,nbt);
        }

    }

    private IUniqueFeatures getInstance(){
        return this.instance.orElseThrow(()->new IllegalStateException("Unable to obtain capability instance"));
    }
}
