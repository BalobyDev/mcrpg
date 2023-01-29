package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.data.characters.Npc;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface ICharProfile {


    CompoundNBT getNbts();

    void setNbts(CompoundNBT nbt);

    CompoundNBT saveNpc(Npc npc);

    CompoundNBT saveNpcs();

    void loadNpc(CompoundNBT nbt, Npc npc);

    void loadNpcs(CompoundNBT nbt);


}
