package net.baloby.mcrpg.setup;

import net.baloby.mcrpg.client.gui.NpcContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static final RegistryObject<ContainerType<NpcContainer>> NPC_CONTAINER = Registration.CONTAINERS.register("npc", ()-> IForgeContainerType.create(((windowId, inv, data) -> {
        return new NpcContainer(windowId,inv);
    })));

    static void register(){}

}
