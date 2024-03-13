package net.baloby.mcrpg.world.structures;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.data.UniqueFeatures.IUniqueStructures;
import net.baloby.mcrpg.data.UniqueFeatures.UniqueStructuresCapabilityProvider;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.Registration;
import net.baloby.mcrpg.world.ModWorldEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class RpgJigsawPiece extends SingleJigsawPiece {

    public static final Codec<RpgJigsawPiece> CODEC = RecordCodecBuilder.create((p_236817_0_) -> {
        return p_236817_0_.group(NpcOffset.CODEC.listOf().fieldOf("npcs").forGetter((p_236819_0_)->{
            return p_236819_0_.npcs;
        }), templateCodec(), processorsCodec(), projectionCodec()).apply(p_236817_0_, RpgJigsawPiece::new);
    });
    private final List<NpcOffset> npcs;

    protected RpgJigsawPiece(List<NpcOffset> npc, Either<ResourceLocation, Template> template, Supplier<StructureProcessorList> processorList, JigsawPattern.PlacementBehaviour p_i51398_1_) {
        super(template,processorList,p_i51398_1_);
        this.npcs = npc;
    }

    @Override
    public List<Template.BlockInfo> getShuffledJigsawBlocks(TemplateManager p_214849_1_, BlockPos p_214849_2_, Rotation p_214849_3_, Random p_214849_4_) {
        return super.getShuffledJigsawBlocks(p_214849_1_, p_214849_2_, p_214849_3_, p_214849_4_);
    }


    @Override
    public boolean place(TemplateManager p_230378_1_, ISeedReader p_230378_2_, StructureManager p_230378_3_, ChunkGenerator generator, BlockPos blockPos, BlockPos p_230378_6_, Rotation rotation, MutableBoundingBox p_230378_8_, Random p_230378_9_, boolean p_230378_10_) {
        ServerWorld world = ModWorldEvents.getServer().overworld();
        IUniqueStructures structures = world.getCapability(UniqueStructuresCapabilityProvider.STRUCTURES_CAP).resolve().get();
        for (NpcOffset offset : npcs){
            if(!offset.isRotated()) {
                Npc npc = Registration.NPC_REGISTRY.get().getValue(offset.getNpc()).listCreate();
                offset.rotatePos(rotation);
                Vector3d rotatedOffset = offset.getPos();
                Vector3d newPos = rotatedOffset.add(new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                npc.setHome(world, newPos);
                npc.setDirty(true);
            }
        }
        return super.place(p_230378_1_,p_230378_2_,p_230378_3_,generator,blockPos,p_230378_6_,rotation,p_230378_8_,p_230378_9_,p_230378_10_);


    }

    public static IJigsawDeserializer<RpgJigsawPiece> RPG_ELEMENT;

    @Override
    public IJigsawDeserializer<?> getType() {
        return RPG_ELEMENT;
    }

}
