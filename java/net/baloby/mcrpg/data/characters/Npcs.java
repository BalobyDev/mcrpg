package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.setup.Registration;
import net.minecraftforge.fml.RegistryObject;

public class Npcs {

    public static final RegistryObject<NpcType<AlexNpc>> ALEX = Registration.NPC_TYPES.register("alex", () -> new NpcType<>(AlexNpc::new));
    public static final RegistryObject<NpcType<AustinNpc>> AUSTIN = Registration.NPC_TYPES.register("austin", () -> new NpcType<>(AustinNpc::new));
    public static final RegistryObject<NpcType<BillieNpc>> BILLIE  = Registration.NPC_TYPES.register("billie",()-> new NpcType<>(BillieNpc::new));
    public static final RegistryObject<NpcType<FelinaNpc>> FELINA = Registration.NPC_TYPES.register("felina",()-> new NpcType<>(FelinaNpc::new));
    public static final RegistryObject<NpcType<GunterNpc>> GUNTER = Registration.NPC_TYPES.register("gunter", ()-> new NpcType<>(GunterNpc::new));
    public static final RegistryObject<NpcType<JeanNpc>> JEAN = Registration.NPC_TYPES.register("jean",()->new NpcType<>(JeanNpc::new));
    public static final RegistryObject<NpcType<RanaNpc>> RANA  = Registration.NPC_TYPES.register("rana",()-> new NpcType<>(RanaNpc::new));
    public static final RegistryObject<NpcType<SteveNpc>> STEVE  = Registration.NPC_TYPES.register("steve",()-> new NpcType<>(SteveNpc::new));

    public static void register(){

    }
}