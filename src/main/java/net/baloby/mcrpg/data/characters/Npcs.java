package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.battle.moves.Moves;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
public class Npcs {

    public static final RegistryObject<NpcType<AlexNpc>> ALEX = Registration.NPC_TYPES.register("alex", () -> new NpcType<>(AlexNpc::new));
    public static final RegistryObject<NpcType<BillieNpc>> BILLIE  = Registration.NPC_TYPES.register("billie",()-> new NpcType<>(BillieNpc::new));
    public static final RegistryObject<NpcType<CassandraNpc>> CASSANDRA  = Registration.NPC_TYPES.register("cassandra",()-> new NpcType<>(CassandraNpc::new));
    public static final RegistryObject<NpcType<JeanNpc>> JEAN = Registration.NPC_TYPES.register("jean",()->new NpcType<>(JeanNpc::new));
    public static final RegistryObject<NpcType<RanaNpc>> RANA  = Registration.NPC_TYPES.register("rana",()-> new NpcType<>(RanaNpc::new));
    public static final RegistryObject<NpcType<SteveNpc>> STEVE  = Registration.NPC_TYPES.register("steve",()-> new NpcType<>(SteveNpc::new));

    public static void register(){


    }
}