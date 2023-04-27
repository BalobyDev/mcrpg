package net.baloby.mcrpg.data;

import net.baloby.mcrpg.data.characters.Npc;
import net.minecraft.nbt.CompoundNBT;

public interface INpcData {


    CompoundNBT getNbts();

    void setNbts(CompoundNBT nbt);

    void saveNpc(Npc npc);

    CompoundNBT saveNpcs();

    void loadNpc(CompoundNBT nbt, Npc npc);

    void loadNpcs();


}
