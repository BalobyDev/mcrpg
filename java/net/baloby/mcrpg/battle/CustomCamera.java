package net.baloby.mcrpg.battle;

import net.baloby.mcrpg.battle.Party.Party;
import net.baloby.mcrpg.battle.Party.PlayerParty;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class CustomCamera {

    public Entity camera;
    public Minecraft minecraft = Minecraft.getInstance();
    public Vector3d pos;
    public Battle battle;

    public CustomCamera(ServerWorld world){
        //camera.setPos(0,2,-1);
        this.battle = Battle.getInstance();
        this.camera = createCamera(world);

        this.minecraft.submitAsync(()->{
            minecraft.setCameraEntity(camera);
        });

        Unit unit = battle.activeUnit;
        setBehind();
        String a = "a";

    }


    private void changePos(BlockPos pos){
        this.camera.teleportTo(pos.getX(),pos.getY(),pos.getZ());
    }

    public Vector3d startingPos(){
        Vector3d pos = battle.playerParty.player.station;
        Vector3d newPos = new Vector3d(pos.x-1,pos.y+1,pos.z-1);
        return newPos;
    }

    public Entity createCamera(ServerWorld world){
        this.camera = new ArmorStandEntity(world,0,72,-1);
        camera.xRotO = 0;
        camera.yRotO = 0;
        camera.setYBodyRot(0);
        camera.xRot = 0;
        camera.xo = 0;
        camera.yo = 2;
        camera.zo = -1;
        camera.inChunk = true;
        camera.onAddedToWorld();
        camera.setInvisible(true);

        return camera;
    }

    public void setFacingParty(Party party){
        if(party instanceof PlayerParty){
            battle.camera.setPosRot(1.5,102,6,0,180);
        }
        else {
            battle.camera.setPosRot(1.5,103,3,0,0);
        }
    }

    public void setPos(double x, double y, double z) {
        Minecraft.getInstance().submitAsync(()->{
            this.camera.teleportTo(x, y, z);

                });
    }

    public void setFacing(Unit unit){
        Vector3d pos = unit.entity.position();
        setPosRot(pos.x, pos.y, pos.z+2,0,180);
    }

    public void setBehind(){
        Vector3d pos = battle.activeUnit.station;
        setPosRot(pos.x-1,pos.y+1,pos.z-2,0,0);
    }

    public void setFacingAngled(Unit unit){
        Vector3d pos = unit.entity.position();
        if(unit.playerControl){
            setPosRot(pos.x - 1, pos.y+unit.entity.getEyeHeight()-1.5, pos.z + 2, 25, -145);
        }
        else {
            setPosRot(pos.x + 1, pos.y+unit.entity.getEyeHeight()-1.5, pos.z - 2, 25, 45);

        }
    }

    public void setRot(float xr, float yr){
        Minecraft.getInstance().submitAsync(()->{
            camera.xRot = xr;
            camera.xRotO = xr;
            camera.yRot = yr;
            camera.yRotO = yr;
            camera.setYHeadRot(yr);
        });
    }

    public void setPosRot(double x, double y, double z, float xr, float yr){
        setPos(x,y,z);
        setRot(xr, yr);
    }
}
