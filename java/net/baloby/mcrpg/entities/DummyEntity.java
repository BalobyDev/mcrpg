package net.baloby.mcrpg.entities;

import javafx.scene.chart.Axis;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class DummyEntity extends HumanoidEntity {


    public DummyEntity(World world) {
        super(ModEntities.HUMANOID.get(), world);
    }

    @Override
    public IPacket<?> getAddEntityPacket(){
        return null;
    }



    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick(){

    }


}
