package net.baloby.mcrpg.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;

public class NpcArgument implements ArgumentType<ResourceLocation> {
    public static final DynamicCommandExceptionType NPC_NOT_FOUND = new DynamicCommandExceptionType((resourceLocation)->{
        return new StringTextComponent("Character not found");
    });

    private static ResourceLocation canSummon(ResourceLocation resourceLocation) throws CommandSyntaxException{
        return resourceLocation;
    }

    public static ResourceLocation getSummonableNpc(CommandContext<CommandSource> source, String str) throws CommandSyntaxException{
        ResourceLocation rl = canSummon(source.getArgument(str,ResourceLocation.class));
        return new ResourceLocation(mcrpg.MODID,rl.getPath());
    }

    public static NpcArgument id(){return new NpcArgument();}
    @Override
    public ResourceLocation parse(StringReader stringReader) throws CommandSyntaxException {

        return ResourceLocation.read(stringReader);
    }
}
