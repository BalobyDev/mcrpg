package net.baloby.mcrpg.tools;

import jdk.nashorn.internal.ir.Block;
import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.tools.Dice;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class Animation {

    public static void turn(Unit unit, int degrees){
        unit.entity.setYBodyRot(degrees);
    }

    public static void hurt(Unit unit){
        Entity entity = unit.entity;
        entity.animateHurt();
    }

    public static void sound(SoundEvent sound){
        Minecraft.getInstance().player.playSound(sound,1,1);
    }

    private static void particle(Unit unit, IParticleData type){
        BlockPos pos = new BlockPos(unit.station.x,unit.station.y,unit.station.z);
        Minecraft.getInstance().level.addParticle(type,pos.getX()+((Dice.roll(10)+1)/10),pos.getY()+ Dice.roll()/10,pos.getZ()+((Dice.roll(10)+1)/10),0,0.05,0);
    }

    public static void particles(Unit unit, IParticleData type, int x){
        for(int i = 0; i < x; i++){
            particle(unit, type);
        }
    }

    public static void particles(Unit unit, IParticleData type,int x, SoundEvent sound){
        particles(unit, type,x);
        sound(sound);
    }
}
