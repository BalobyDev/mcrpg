package net.baloby.mcrpg.cutscene;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.baloby.mcrpg.data.dialouge.DialogueChain;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CutsceneManager extends JsonReloadListener {

    private static Gson GSON = new GsonBuilder().create();

    protected Map<ResourceLocation, Cutscene> data;

    public Cutscene getData(ResourceLocation id){
        return this.data.get(id);
    }

    public CutsceneManager() {
        super(GSON, "cutscene");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> p_212853_1_, IResourceManager p_212853_2_, IProfiler p_212853_3_) {
        Map<ResourceLocation, Cutscene> newMap = new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : p_212853_1_.entrySet()){
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();

            Cutscene.CODEC.decode(JsonOps.INSTANCE, element)
                    .get()
                    .ifLeft(result -> newMap.put(key, result.getFirst()));

        }
        this.data = newMap;
    }
}
