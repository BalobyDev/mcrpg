package net.baloby.mcrpg.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

public class ModBlocks {

    public static final RegistryObject<Block> RUBY_ORE = Registration.BLOCKS.register("ruby_ore", ()->
            new Block(AbstractBlock.Properties.of(Material.STONE)));

    public static final RegistryObject<Block> SLIME_CHEST = Registration.BLOCKS.register("slime_chest", ()->
            new Block(AbstractBlock.Properties.of(Material.SPONGE).sound(SoundType.SLIME_BLOCK)));

    static void register() {}

}
