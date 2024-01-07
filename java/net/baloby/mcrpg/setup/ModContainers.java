package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.client.gui.CorpseScreen;
import net.baloby.mcrpg.client.gui.EquipScreen;
import net.baloby.mcrpg.client.gui.NpcContainer;
import net.baloby.mcrpg.data.CorpseContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static final RegistryObject<ContainerType<NpcContainer>> NPC_CONTAINER = Registration.CONTAINERS.register("npc", ()-> IForgeContainerType.create(((windowId, inv, data) -> {
        return new NpcContainer(windowId,inv);
    })));

    public static final RegistryObject<ContainerType<CorpseContainer>> CORPSE_CONTAINER = Registration.CONTAINERS.register("corpse", ()-> IForgeContainerType.create(((windowId, inv, data) -> {
        return new CorpseContainer(windowId,inv);
    })));

    static void register(){


    }

}
