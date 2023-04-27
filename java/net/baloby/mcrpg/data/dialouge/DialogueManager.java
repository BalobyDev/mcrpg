package net.baloby.mcrpg.data.dialouge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.sun.org.apache.bcel.internal.classfile.Code;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DialogueManager extends JsonReloadListener {

    private static Gson GSON = new GsonBuilder().create();

    protected Map<ResourceLocation, DialogueChain> data;

    public DialogueChain getData(ResourceLocation id){
        return this.data.get(id);
    }

    public DialogueManager() {
        super(GSON, "dialogue");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> p_212853_1_, IResourceManager p_212853_2_, IProfiler p_212853_3_) {
        Map<ResourceLocation, DialogueChain> newMap = new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : p_212853_1_.entrySet()){
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();

            DialogueChain.CODEC.decode(JsonOps.INSTANCE, element)
                    .get()
                    .ifLeft(result -> newMap.put(key, result.getFirst()));

        }
        this.data = newMap;
    }
}
