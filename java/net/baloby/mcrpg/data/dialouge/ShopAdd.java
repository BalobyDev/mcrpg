package net.baloby.mcrpg.data.dialouge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.RecordBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class ShopAdd {

    private ResourceLocation npc;
    private CompoundNBT item;
    private int cost;

    public static Codec<ShopAdd> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("npc").forGetter(ShopAdd::getNpc),
            CompoundNBT.CODEC.fieldOf("item").forGetter(ShopAdd::getItem),
            Codec.INT.fieldOf("cost").forGetter(ShopAdd::getCost)
    ).apply(instance,ShopAdd::new));


    public ShopAdd(ResourceLocation npc, CompoundNBT item, int cost){
        this.npc = npc;
        this.item = item;
    }

    public ResourceLocation getNpc(){return npc;}

    public CompoundNBT getItem() {
        return item;
    }

    public int getCost() {
        return cost;
    }
}
