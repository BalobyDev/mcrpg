package net.baloby.mcrpg.quest;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class ItemRequirement implements IRequirement{

    private ResourceLocation item;
    private int amount;

    public static Codec<ItemRequirement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("item").forGetter(ItemRequirement::getItem),
            Codec.INT.optionalFieldOf("amount").orElseGet(Optional::empty).forGetter((item -> {
                return Optional.of(item.getAmount());
            }))

            ).apply(instance, ItemRequirement::new));

    public ItemRequirement(ResourceLocation item, Optional<Integer> amount){
        this.item = item;
        this.amount = 1;
        if(amount.isPresent()){
            this.amount = amount.get();
        }
    }


    public ResourceLocation getItem() {
        return item;
    }

    public int getAmount(){
        return amount;
    }

    @Override
    public boolean hasRequirement() {
        return false;
    }
}
