package net.baloby.mcrpg.data.UniqueFeatures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UniqueStructuresCapabilityProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IUniqueStructures.class)

    public static Capability<IUniqueStructures> STRUCTURES_CAP = null;

    private LazyOptional<IUniqueStructures> instance = LazyOptional.of(()-> STRUCTURES_CAP.getDefaultInstance());



    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == STRUCTURES_CAP ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        if(STRUCTURES_CAP !=null)nbt = (CompoundNBT) STRUCTURES_CAP.getStorage().writeNBT(STRUCTURES_CAP,this.getInstance(),null);
        return nbt;    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if (STRUCTURES_CAP != null){
            STRUCTURES_CAP.getStorage().readNBT(STRUCTURES_CAP, this.getInstance(),null,nbt);
        }

    }

    private IUniqueStructures getInstance(){
        return this.instance.orElseThrow(()->new IllegalStateException("Unable to obtain capability instance"));
    }
}
