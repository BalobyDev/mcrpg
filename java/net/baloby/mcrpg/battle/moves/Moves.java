package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.setup.Registration;
import net.minecraftforge.fml.RegistryObject;

public class Moves {

    public static final RegistryObject<MoveType<?>> AQUA = Registration.MOVES.register("aqua", ()->new MoveType<>(Aqua::new));
    public static final RegistryObject<MoveType<?>> CHICKEN = Registration.MOVES.register("chicken",()->new MoveType<>(ChickenMove::new));
    public static final RegistryObject<MoveType<?>> ENDRA = Registration.MOVES.register("endra",()->new MoveType<>(Endra::new));
    public static final RegistryObject<MoveType<?>> FLORA = Registration.MOVES.register("flora",()->new MoveType<>(Flora::new));
    public static final RegistryObject<MoveType<?>> FORTITUDE = Registration.MOVES.register("fortitude",()->new MoveType<>(Fortitude::new));
    public static final RegistryObject<MoveType<?>> MELODY = Registration.MOVES.register("melody",()->new MoveType<>(Melody::new));
    public static final RegistryObject<MoveType<?>> HASTE = Registration.MOVES.register("haste",()->new MoveType<>(Haste::new));
    public static final RegistryObject<MoveType<?>> HEAL = Registration.MOVES.register("heal",()->new MoveType<>( Heal::new));
    public static final RegistryObject<MoveType<?>> IGNEON = Registration.MOVES.register("igneon",()->new MoveType<>(Igneon::new));
    public static final RegistryObject<MoveType<?>> IGNI = Registration.MOVES.register("igni",()->new MoveType<>(Igni::new));
    public static final RegistryObject<MoveType<?>> LIFE_GOES_ON = Registration.MOVES.register("life_goes_on",()->new MoveType<>(Lullaby::new));
    public static final RegistryObject<MoveType<?>> LULLABY = Registration.MOVES.register("lullaby",()->new MoveType<>(Lullaby::new));
    public static final RegistryObject<MoveType<?>> MIRACLE = Registration.MOVES.register("miracle",(()->new MoveType<>(Miracle::new)));
    public static final RegistryObject<MoveType<?>> POISON = Registration.MOVES.register("poison",()->new MoveType<>(Poison::new));
    public static final RegistryObject<MoveType<?>> VITALITY = Registration.MOVES.register("vitality",()->new MoveType<>(Vitality::new));
    public static final RegistryObject<MoveType<?>> VOLTAGE = Registration.MOVES.register("voltage",()->new MoveType<>(Voltage::new));


    public static void register(){}


}
