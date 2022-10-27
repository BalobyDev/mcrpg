package net.baloby.mcrpg.setup.Items;

import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EtherItem extends Item {
    public EtherItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity){

        if(entity instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().setMp(20);
            stack.shrink(1);
        }

        return stack.isEmpty()? new ItemStack(Items.GLASS_BOTTLE): stack;

    }

    public int getUseDuration(ItemStack stack){return  32;}

    public UseAction getUseAnimation(ItemStack stack){return UseAction.DRINK;}

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
        return DrinkHelper.useDrink(world, player, hand);
    }

}
