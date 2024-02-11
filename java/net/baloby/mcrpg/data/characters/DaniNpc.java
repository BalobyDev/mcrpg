package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Affinity;
import net.baloby.mcrpg.battle.moves.Element;
import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class DaniNpc extends BattleNpc{

    public DaniNpc(){
        super(Npcs.DANI.get(), new StringTextComponent("Dani"), new ResourceLocation(mcrpg.MODID, "textures/entity/danny.png"), ModEntities.HUMANOID_SLIM.get(), Items.AIR, 80, 100);
        this.addMove(Moves.FLORA.get());
        this.addMove(Moves.HEAL.get());
        this.addMove(Moves.POISON.get());
        this.addAffinity(Element.LIFE, Affinity.STRONG);
    }
}
