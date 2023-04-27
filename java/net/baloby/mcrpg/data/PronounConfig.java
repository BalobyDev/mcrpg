package net.baloby.mcrpg.data;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

public class PronounConfig {

    private String subjective;
    private String objective;
    private String possessive;

    public PronounConfig(String subjective,String objective, String possessive){
        this.subjective = subjective;
        this.objective = objective;
        this.possessive = possessive;

    }

    public String getSubjective(){
        return subjective;
    }

    public String getObjective() {
        return objective;
    }

    public String getPossessive(){
        return possessive;
    }

    public void save(ServerPlayerEntity player){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("sub",this.subjective);
        nbt.putString("obj",this.objective);
        nbt.putString("pos",this.possessive);
        IPlayerData playerData = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        playerData.setPronouns(nbt);
    }
}
