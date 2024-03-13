package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.cutscene.Cutscene;
import net.baloby.mcrpg.cutscene.CutsceneManager;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModSetup;
import net.baloby.mcrpg.tools.Teleport;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.serializers.StringArgumentSerializer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.io.IOException;

public class CutsceneCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("cutscene").requires((source)->{
            return source.hasPermission(2);
        }).then(Commands.literal("start").then(Commands.argument("scene", StringArgumentType.string()).executes((context)->{
            return startCutscene(context.getSource(),StringArgumentType.getString(context,"scene"));
        }))).then(Commands.literal("finish").executes((context)->{
            return endCutscene(context.getSource());
        })));
    }

    public static int startCutscene(CommandSource source,String string) throws CommandSyntaxException{
        ServerPlayerEntity player = source.getPlayerOrException();
        RegistryKey<World> key  = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID,"cutscene"));
        Cutscene cutscene = ModSetup.CUTSCENE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"party_banter"));
        cutscene.loadCutscene(player);

        player.sendMessage(new StringTextComponent("Loading cutscene..."),player.getUUID());
        return 0;
    }

    public static int endCutscene(CommandSource source) throws CommandSyntaxException{
        ServerPlayerEntity player = source.getPlayerOrException();
        RegistryKey<World> key  = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(mcrpg.MODID,"cutscene"));
        Cutscene cutscene = Cutscene.cutsceneMap.get(player);
        if(cutscene == null){
            player.sendMessage(new StringTextComponent("No cutscene active!"),player.getUUID());
            return 0;
        }
        try {
            cutscene.endCutscene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.sendMessage(new StringTextComponent("Cutscene ended"),player.getUUID());
        return 0;
    }
}
