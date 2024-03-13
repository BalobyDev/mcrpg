package net.baloby.mcrpg.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.baloby.mcrpg.battle.Battle;
import net.baloby.mcrpg.battle.Unit.UnitType;
import net.baloby.mcrpg.commands.arguments.ArenaArgument;
import net.baloby.mcrpg.commands.arguments.EntityArgument;
import net.baloby.mcrpg.cutscene.Stage;
import net.baloby.mcrpg.cutscene.StageManager;
import net.baloby.mcrpg.data.characters.BattleNpc;
import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.entities.custom.enemies.ICustomBattleEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModDimensions;
import net.baloby.mcrpg.setup.ModSetup;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class BattleCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("battle").requires((source)->{
            return source.hasPermission(2);
        }).then(Commands.argument("opponent", EntityArgument.id()).executes((context)->{
            return startBattle(context.getSource(),EntityArgument.getEntity(context,"opponent"),new ResourceLocation(mcrpg.MODID,"arena"));
        }).then(Commands.argument("arena", ArenaArgument.id()).executes((context)->{
            return startBattle(context.getSource(),EntityArgument.getEntity(context,"opponent"),ArenaArgument.getArena(context,"arena"));
        }))));
    }

    public static int startBattle(CommandSource source, ResourceLocation type, ResourceLocation arena) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        EntityType entityType = Registry.ENTITY_TYPE.get(type);
        Stage stage = ModSetup.STAGE_MANAGER.getData(arena);
        if(stage == null)stage = ModSetup.STAGE_MANAGER.getData(new ResourceLocation(mcrpg.MODID,"cave_arena"));
        for (ResourceLocation location : Registration.NPC_REGISTRY.get().getKeys()) {
            if(type.equals(location)){
                Npc npc = Registration.NPC_REGISTRY.get().getValue(location).listCreate();
                if(npc instanceof BattleNpc) {
                    Battle.npcStart(player, (BattleNpc) npc, stage,player.blockPosition() );
                    return 0;
                }
            }
        }
        Entity entity = entityType.create(player.getLevel());
        if(UnitType.unitMap.containsKey(entityType)||entity instanceof ICustomBattleEntity){


            Battle.mobStart(player,entity, stage,player.blockPosition());

        }
        else {
            player.sendMessage(new StringTextComponent("Not a valid enemy."),player.getUUID());
        }

        return 0;
    }
}
