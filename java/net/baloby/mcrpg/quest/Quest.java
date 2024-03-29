package net.baloby.mcrpg.quest;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.baloby.mcrpg.data.PlayerCapabilityProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class Quest {

    private String id;
    private String title;
    private String description;
    private Status status;
    private Optional<ItemRequirement> itemRequirement;
    private Optional<ItemReward> itemReward;
    private ResourceLocation resourceLocation;

    public static final Codec<Quest> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(Quest::getId),
            Codec.STRING.fieldOf("title").forGetter(Quest::getTitle),
            Codec.STRING.fieldOf("description").forGetter(Quest::getDescription),
            ItemRequirement.CODEC.optionalFieldOf("item_requirement").orElseGet(Optional::empty).forGetter(Quest::getItemRequirement),
            ItemReward.CODEC.optionalFieldOf("item_reward").orElseGet(Optional::empty).forGetter(Quest::getItemReward)
    ).apply(instance,Quest::new));


    public Quest(String id,String title, String description, Optional<ItemRequirement> itemRequirement, Optional<ItemReward> itemReward){
        this.id = id;
        this.title = title;
        this.description = description;
        this.itemRequirement = itemRequirement;
        this.itemReward = itemReward;
        this.status = Status.LOCKED;
    }


    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void assign(ServerPlayerEntity player){
        player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().updateQuest(this, Status.UNLOCKED);
    }

    public Optional<ItemRequirement> getItemRequirement(){
        return itemRequirement;
    }

    public Optional<ItemReward> getItemReward(){
        return itemReward;
    }

    public boolean check(ServerPlayerEntity player){
        if(itemRequirement.isPresent()){
            ItemRequirement requirement = itemRequirement.get();
            int count = player.inventory.countItem(Registry.ITEM.get(requirement.getItem()));
            if(count >= requirement.getAmount()){
                complete(player);
                return true;
            }
        }
        return false;
    }

    public void complete(ServerPlayerEntity player){
        player.getCapability(PlayerCapabilityProvider.CHAR_CAP).resolve().get().updateQuest(this,Status.COMPLETE);
    }

    public Status getStatus() {
        return status;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }
}
