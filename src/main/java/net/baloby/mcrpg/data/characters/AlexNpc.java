package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class AlexNpc extends BattleNpc{

    public AlexNpc(){
        super(Npcs.ALEX.get(),"Alex",new ResourceLocation("textures/entity/alex.png"),true, Items.TRIDENT, 20,20, Moves.VOLTAGE.get());
        this.id = 1;
    }
}
