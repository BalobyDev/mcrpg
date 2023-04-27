package net.baloby.mcrpg.data;

import net.baloby.mcrpg.data.characters.Npc;
import net.baloby.mcrpg.setup.Registration;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;

public class ModDataSerializers {
    public static final IDataSerializer<Npc> NPC_DATA = new IDataSerializer<Npc>() {
        public void write(PacketBuffer buffer, Npc npc) {
            buffer.writeResourceLocation(Registration.NPC_REGISTRY.get().getKey(npc.getType()));
            buffer.writeBytes(npc.getName().getBytes());
            buffer.writeRegistryId(npc.getEntityType());
            buffer.writeResourceLocation(npc.getSkin());
            buffer.writeFloat(npc.getSize());
        }

        public Npc read(PacketBuffer buffer) {
            Npc npc = new Npc(Registration.NPC_REGISTRY.get().getValue(buffer.readResourceLocation()),buffer.readUtf(),buffer.readRegistryId(),buffer.readResourceLocation());
            return npc;
        }

        public Npc copy(Npc npc) {
            return npc;
        }
    };

    static{
        DataSerializers.registerSerializer(NPC_DATA);}
}
