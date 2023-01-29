package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.setup.Registration;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;

public class Moves {

    public static final RegistryObject<MoveType<?>> AQUA = Registration.MOVES.register("aqua", ()->new MoveType<>(Aqua::new));
    public static final RegistryObject<MoveType<?>> CHICKEN = Registration.MOVES.register("chicken",()->new MoveType<>(Chicken::new));
    public static final RegistryObject<MoveType<?>> ENDRA = Registration.MOVES.register("endra",()->new MoveType<>(Endra::new));
    public static final RegistryObject<MoveType<?>> FLORA = Registration.MOVES.register("flora",()->new MoveType<>(Flora::new));
    public static final RegistryObject<MoveType<?>> IGNI = Registration.MOVES.register("igni",()->new MoveType<>(Igni::new));
    public static final RegistryObject<MoveType<?>> VOLTAGE = Registration.MOVES.register("voltage",()->new MoveType<>(Voltage::new));
    public static final RegistryObject<MoveType<?>> HEAL = Registration.MOVES.register("heal",()->new MoveType<>( Heal::new));

    public static void register(){}


}
