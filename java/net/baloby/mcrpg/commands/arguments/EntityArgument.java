package net.baloby.mcrpg.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;

public class EntityArgument implements ArgumentType<ResourceLocation> {

    public static ResourceLocation getEntity(CommandContext<CommandSource> source, String str){
        return new ResourceLocation(source.getArgument(str, ResourceLocation.class).toString());
    }

    public static EntityArgument id(){
        return new EntityArgument();
    }


    @Override
    public ResourceLocation parse(StringReader stringReader) throws CommandSyntaxException {
        return ResourceLocation.read(stringReader);
    }
}
