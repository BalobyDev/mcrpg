package net.baloby.mcrpg.quest;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemReward {

    private ResourceLocation item;
    private int amount;
    private Optional<List<Enchantment>> enchantments;

    public static Codec<ItemReward> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("item").forGetter(ItemReward::getItem),
            Enchantment.CODEC.listOf().optionalFieldOf("enchantments").forGetter(ItemReward::getEnchantments),
            Codec.INT.optionalFieldOf("amount").orElseGet(Optional::empty).forGetter((item -> {
                return Optional.of(item.getAmount());
            }))
    ).apply(instance, ItemReward::new));

    public ItemReward(ResourceLocation item, Optional<List<Enchantment>> enchantments, Optional<Integer> amount){
        this.item = item;
        this.enchantments = enchantments;
        this.amount = 1;
        if(amount.isPresent()){
            this.amount = amount.get();
        }
    }

    public ItemStack createItem(){
        ItemStack stack = new ItemStack(Registry.ITEM.get(item),amount);
        if(enchantments.isPresent()){
            for(Enchantment enchant : enchantments.get()){
                stack.enchant(Registry.ENCHANTMENT.get(enchant.getEnchantment()),enchant.getLevel());
            }
        }
        return stack;
    }

    public ResourceLocation getItem() {
        return item;
    }

    public int getAmount(){
        return amount;
    }

    public Optional<List<Enchantment>> getEnchantments() {
        return enchantments;
    }
}
