package net.baloby.mcrpg.entities.custom.enemies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.world.World;

public class NewEndermanEntity extends EndermanEntity {
    public NewEndermanEntity(EntityType type, World world) {
        super(EntityType.ENDERMAN, world);
    }


    protected boolean teleport() {
        return false;
    }

}
