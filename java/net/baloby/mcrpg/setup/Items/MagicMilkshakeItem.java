package net.baloby.mcrpg.setup.Items;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.battle.moves.INonBattleMove;
import net.baloby.mcrpg.client.gui.PartyManagerScreen;
import net.baloby.mcrpg.client.gui.profile.Profile;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.baloby.mcrpg.tools.Animation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MagicMilkshakeItem extends Item implements BattleItem, INonBattleMove {
    public MagicMilkshakeItem(Properties properties) {
        super(properties);
    }

    public SoundEvent sound = SoundEvents.EXPERIENCE_ORB_PICKUP;

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity){

        if(entity instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().setMp(20);
            stack.shrink(1);
        }

        return stack.isEmpty()? new ItemStack(Items.GLASS_BOTTLE): stack;

    }


    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
        if(player instanceof ServerPlayerEntity){
            PartyManagerScreen.open((ServerPlayerEntity) player,this,null);
        }
        return DrinkHelper.useDrink(world, player, hand);
    }



    @Override
    public void use(Unit user, Unit target) {
        target.MP += 20;
        if(target.MP > target.MAX_MP) target.MP = target.MAX_MP;
    }

    public ITextComponent getDescription(){
        return new StringTextComponent("Restores 20 mp to a single party member");
    }

    @Override
    public void nonBattleExecute(Profile user, Profile target) {
        target.addMp(20);
        Animation.sound(sound);
        target.save();

    }
}
