package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueFeaturesCapabilityProvider;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class SteveNpc extends BattleNpc{
    public SteveNpc(){
        super(Npcs.STEVE.get(),"Steve", new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png"),false, Items.IRON_SWORD,20,20, Moves.AQUA.get());
        this.hurtSound = ModSounds.OOH.get();
    }
}
