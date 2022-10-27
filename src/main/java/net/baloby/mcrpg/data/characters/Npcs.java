package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.mcrpg;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
public class Npcs {
    public static Npc ALEX = new BattleNpc("Alex", new ResourceLocation("textures/entity/alex.png"),true, Items.BOW,20,20);
    public static Npc BILLIE = new BillieNpc();
    public static Npc RANA = new BattleNpc("Rana", new ResourceLocation(mcrpg.MODID, "textures/entity/rana.png"),true, Items.TRIDENT,20,20);
    public static Npc STEVE = new BattleNpc("Steve", new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png"),false, Items.IRON_SWORD,20,20);
    public static ArrayList<Npc> npcs = new ArrayList<>();
    public static void register(){
        npcs.add(ALEX);
        npcs.add(BILLIE);
        npcs.add(RANA);
        npcs.add(STEVE);
    }
}