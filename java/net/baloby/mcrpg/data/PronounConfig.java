package net.baloby.mcrpg.data;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

public class PronounConfig {

    private String subjective;
    private String objective;
    private String posNoun;
    private String posAdj;
    private String contraction;
    private String reflexive;

    public PronounConfig(String subjective,String objective, String posNoun, String posAdj, String contraction, String reflexive){
        this.subjective = subjective;
        this.objective = objective;
        this.posNoun = posNoun;
        this.posAdj = posAdj;
        this.contraction = contraction;
        this.reflexive = reflexive;

    }

    public String getSubjective(){
        return subjective;
    }

    public String getObjective() {
        return objective;
    }

    public String getPosNoun(){
        return posNoun;
    }

    public String getPosAdj() {return posAdj;}

    public String getContraction(){return contraction;}

    public String getReflexive() {
        return reflexive;
    }

    public void save(ServerPlayerEntity player){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("sub",this.subjective);
        nbt.putString("obj",this.objective);
        nbt.putString("pos_noun",this.posNoun);
        IPlayerData playerData = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        playerData.setPronouns(nbt);
    }



}
