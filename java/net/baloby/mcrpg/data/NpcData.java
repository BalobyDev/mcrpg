package net.baloby.mcrpg.data;

import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.nbt.CompoundNBT;

public class NpcData implements INpcData {


    private CompoundNBT nbts = new CompoundNBT();

    @Override
    public CompoundNBT getNbts() {
        return nbts;
    }

    @Override
    public void setNbts(CompoundNBT nbt) {
        this.nbts = nbt;
    }

    @Override
    public void saveNpc(Npc npc) {
        CompoundNBT nbt = nbts;
        nbt.put(npc.getType().getRegistryName().toString(),npc.save());
        for(Npc npc1 : Npc.allNpcs){
            if(npc.getType().equals(npc1.getType())){
                npc1.load(npc.save());
            }
        }
    }

    @Override
    public CompoundNBT saveNpcs() {
        CompoundNBT nbt = nbts;
        for(Npc npc : Npc.allNpcs){
            if(npc.isDirty) {
                nbt.put(npc.getType().getRegistryName().toString(), npc.save());
                npc.setDirty(false);
            }
        }
        return nbt;
    }


    @Override
    public void loadNpc(CompoundNBT nbt, Npc npc){

        npc.load((CompoundNBT) nbt.get(npc.getType().getRegistryName().toString()));
    }

    @Override
    public void loadNpcs(){
        CompoundNBT nbt = nbts;
        for(Npc npc : Npc.allNpcs){
            npc.load((CompoundNBT) nbt.get(npc.getType().getRegistryName().toString()));
        }
    }
}
