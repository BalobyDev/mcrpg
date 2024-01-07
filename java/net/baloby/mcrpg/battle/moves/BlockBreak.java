package net.baloby.mcrpg.battle.moves;

import net.minecraft.block.Block;

public class BlockBreak extends Attack{


    public Block block;

    public BlockBreak(){
        this.type = Element.PHYSICAL;
        this.dmg = 12;

    }
}
