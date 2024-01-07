package net.baloby.mcrpg.battle.Unit.Enemies;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.entities.custom.enemies.NewZombieEntity;
import net.baloby.mcrpg.entities.custom.enemies.NewZombieVillagerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;

public class ZombieVillagerUnit extends Unit {

    public ZombieVillagerUnit(){
        super(EntityType.ZOMBIE_VILLAGER);
    }

    public MobEntity spawn(EntityType type){
        ZombieVillagerEntity zombie = new NewZombieVillagerEntity(arena);
        if(zombie.isBaby()){
            zombie.remove();
            return spawn(type);
        }
        return zombie;
    }
}
