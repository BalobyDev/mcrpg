package net.baloby.mcrpg.world.structures;

import com.google.common.collect.ImmutableList;
import com.ibm.icu.impl.Pair;
import com.mojang.serialization.Codec;
import net.baloby.mcrpg.data.characters.NpcType;
import net.baloby.mcrpg.data.characters.Npcs;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class SunRootStructure extends LocationStructure {

    public SunRootStructure() {
        super();
        this.location = new ResourceLocation(mcrpg.MODID,"sunroot");

//        this.addNpc(Npcs.RANA.get(),new NpcOffset(new Vector3d(10,0,10), felinaHouse, Rotation.NONE));
//        this.addNpc(Npcs.FELINA.get(),new NpcOffset(new Vector3d(10,11,10), felinaHouse, Rotation.NONE));
//        this.addNpc(Npcs.ALEX.get(),new NpcOffset(new Vector3d(11.5,0,8.5), smithShop, Rotation.NONE));
//        this.addNpc(Npcs.GUNTER.get(),new NpcOffset(new Vector3d(9.5,0,8.5), smithShop, Rotation.NONE));
    }

}
