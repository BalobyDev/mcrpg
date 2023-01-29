package net.baloby.mcrpg.tools;

import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.data.ICharProfile;
import net.baloby.mcrpg.data.IPlayerProfile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.block.PortalInfo;
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
//        entity.changeDimension(dest, new ITeleporter() {
//            @Override
//            public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo){
//                return null;
//            }
//                entity = repositionEntity.apply(false);
        IPlayerProfile profile = entity.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        float teMP = profile.getMp();
        entity.teleportTo(dest, pos.getX(),pos.getY(),pos.getZ(),0,0);
        profile.setMp((int) teMP);

        entity.clearFire();
        entity.setExperiencePoints(entity.totalExperience);
            }


    public static void teleport(ServerPlayerEntity entity, double x, double y, double z){
        entity.teleportTo(x,y+0.2,z);
    }

    public static void teleport(ServerPlayerEntity entity, BlockPos pos){
        teleport(entity, pos.getX(), pos.getY(), pos.getZ());
    }
}
