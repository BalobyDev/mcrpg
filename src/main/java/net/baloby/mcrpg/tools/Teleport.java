package net.baloby.mcrpg.tools;

import net.baloby.mcrpg.battle.Battle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class Teleport {

    public static void teleport(ServerPlayerEntity entity, ServerWorld dest, BlockPos pos) {
        if(!(entity.canChangeDimensions()))return;
        entity.changeDimension(dest, new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                entity.teleportTo(pos.getX(),pos.getY(),pos.getZ());
                entity.clearFire();
                return entity;
            }});
    }

    public static void teleport(ServerPlayerEntity entity, double x, double y, double z){
        entity.teleportTo(x,y+0.2,z);
    }

    public static void teleport(ServerPlayerEntity entity, BlockPos pos){
        teleport(entity,(double) pos.getX(),(double) pos.getY(),(double) pos.getZ());
    }



}
