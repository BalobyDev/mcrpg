package net.baloby.mcrpg.data;

import net.baloby.mcrpg.battle.moves.Move;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.util.ArrayList;

public class CharProfile implements ICharProfile {


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
    public CompoundNBT saveNpc(Npc npc) {
        return npc.save();
    }

    @Override
    public CompoundNBT saveNpcs() {
        CompoundNBT nbt = nbts;
        for(Npc npc : Npc.allNpcs){
            if(npc.isDirty) {
                nbt.put(Registration.NPC_REGISTRY.get().getKey(npc.getType()).getPath(), npc.save());
                npc.setDirty(false);
            }
        }
        return nbt;
    }


    @Override
    public void loadNpc(CompoundNBT nbt, Npc npc){
        npc.load((CompoundNBT) nbt.get(npc.getName()));
    }

    @Override
    public void loadNpcs(CompoundNBT nbt){
        for(Npc npc : Npc.allNpcs){
            loadNpc(nbt,npc);
        }
    }
}
