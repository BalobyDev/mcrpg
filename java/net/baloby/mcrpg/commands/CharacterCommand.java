package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.baloby.mcrpg.commands.arguments.NpcArgument;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.SuggestionProviders;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class CharacterCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("character").requires((source)->{
            return source.hasPermission(2);
        }).then(Commands.argument("npc",NpcArgument.id()).executes((context) -> {
            return addNpc(context.getSource(),NpcArgument.getSummonableNpc(context,"npc"));
        })));
    }

    private static int addNpc(CommandSource source, ResourceLocation type) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        Registration.NPC_REGISTRY.get().getValue(type).listCreate().spawnLoad(player.getLevel(),player.position());
        return 0;
    }

    public static SuggestionProvider<CommandSource> SUMMONABLE_NPCS = SuggestionProviders.register(new ResourceLocation(mcrpg.MODID,"summonable_npcs"), (p_197495_0_, p_197495_1_) -> {
        return ISuggestionProvider.suggestResource(p_197495_0_.getSource().getAvailableSoundEvents(), p_197495_1_);
    });

}