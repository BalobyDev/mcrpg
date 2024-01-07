package net.baloby.mcrpg.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;

public class MoveArgument implements ArgumentType<ResourceLocation> {

    public static ResourceLocation getMove(CommandContext<CommandSource> source, String str){

        return new ResourceLocation(mcrpg.MODID,source.getArgument(str, ResourceLocation.class).getPath());
    }

    public static MoveArgument id(){return new MoveArgument();}


    @Override
    public ResourceLocation parse(StringReader stringReader) throws CommandSyntaxException {
        return ResourceLocation.read(stringReader);
    }
}
