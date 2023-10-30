package net.baloby.mcrpg.quest;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class Enchantment {

    private ResourceLocation enchantment;
    private Integer level;

    public static Codec<Enchantment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("enchantment").forGetter(Enchantment::getEnchantment),
            Codec.INT.optionalFieldOf("level").orElseGet(Optional::empty).forGetter((enchant -> {
                return Optional.of(enchant.getLevel());
            }))
        ).apply(instance,Enchantment::new));

    public Enchantment(ResourceLocation enchantment, Optional<Integer> level){
        this.enchantment = enchantment;
        this.level = 1;
        if(level.isPresent()){
            this.level = level.get();
        }

    }

    public ResourceLocation getEnchantment() {
        return enchantment;
    }

    public Integer getLevel() {
        return level;
    }
}
