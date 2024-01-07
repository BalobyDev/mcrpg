package net.baloby.mcrpg.setup.Blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class SlimeChestTileEntity extends TileEntity {

    public SlimeChestTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public int getContainerSize(){
        return 9;
    }
}
