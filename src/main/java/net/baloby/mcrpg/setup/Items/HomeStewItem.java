package net.baloby.mcrpg.setup.Items;


import net.baloby.mcrpg.tools.Teleport;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;


public class HomeStewItem extends Item {
    public HomeStewItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity){
        if (entity instanceof  ServerPlayerEntity && world instanceof ServerWorld){
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            BlockPos pos = player.getRespawnPosition();
            if (pos==null)return stack;
            if(player.getRespawnDimension() == world.dimension()){
                Teleport.teleport(player,pos);
            }
            else {
                Teleport.teleport(player,world.getServer().getLevel(player.getRespawnDimension()),pos);
            }
            stack.shrink(1);

        }
        return stack.isEmpty()? new ItemStack(Items.BOWL): stack;
    }


}
















