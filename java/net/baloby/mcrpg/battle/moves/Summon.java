package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.text.StringTextComponent;

public class Summon extends Move{
    private EntityType type;

    public Summon(EntityType type) {
        super(new StringTextComponent("Summon"));
        this.type = type;
    }

    public void execute(Unit user, Unit target){
        super.execute(user,target);
        if(user.party.availableStations.size()>0){
            user.party.addMember(type);
        }
    }
}
