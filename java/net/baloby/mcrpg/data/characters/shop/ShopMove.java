package net.baloby.mcrpg.data.characters.shop;

import net.baloby.mcrpg.battle.moves.MoveType;
import net.baloby.mcrpg.data.IPlayerData;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ShopMove extends ShopElement{

    private MoveType move;

    public ShopMove(MoveType move, int cost, int quantity, CostType type) {
        super(cost, quantity, type);
        this.move = move;
        this.icon = move.create().type.getIcon();
        this.name = move.create().name;
        this.description = move.create().desc;
    }

    @Override
    public boolean isAffordable(ServerPlayerEntity player){
        return super.isAffordable(player);
    }


    @Override
    public void purchase(ServerPlayerEntity player){
        super.purchase(player);
        IPlayerData profile = player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get();
        profile.addAvailableMove(move);
    }
}
