package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class Npc {

    public static ArrayList<Npc> allNpcs = new ArrayList<>();
    protected String name;
    public boolean slim = false;
    private ResourceLocation skin;
    public Item item;
    protected int id;
    protected NpcType type;
    public boolean isDirty;
    protected DialogueTree dialogueTree;
    protected BlockPos homePos;
    protected ServerWorld homeWorld;
//    protected boolean isHome;
    public boolean isAddedToWorld;
    public SoundEvent hurtSound;
    private HumanoidEntity entity;

    public Npc(){
        this.name = "Nobody";
        this.skin = new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png");
    }

    public Npc(NpcType<?> type, String name, boolean slim, ResourceLocation skin){
        this.type = type;
        this.name = name;
        this.slim = slim;
        this.skin = skin;
        this.hurtSound = SoundEvents.PLAYER_HURT;
//        this.isHome = false;
        this.isAddedToWorld = false;
        this.dialogueTree = new DialogueTree();
    }





    public void addToList(){
        List<Npc> toRemove = new ArrayList<>();
        for (Npc npc : allNpcs) {
            if(npc.getType() == this.getType()){toRemove.add(npc);

            }
        }

        allNpcs.removeAll(toRemove);
        allNpcs.add(this);
    }


    public String getName() {
        return name;
    }

    public Entity spawn(ServerWorld world, Vector3d pos){
        HumanoidEntity entity = slim ? new HumanoidSlimEntity(ModEntities.HUMANOID_SLIM.get(),world,this) : new HumanoidEntity(ModEntities.HUMANOID.get(),world,this);
        entity.moveTo(pos.x,pos.y,pos.z);
        //entity.finalizeSpawn(world, world.getCurrentDifficultyAt(new BlockPos(pos)),SpawnReason.SPAWN_EGG, (ILivingEntityData)null, (CompoundNBT)null);
        world.getServer().submitAsync(()->{
            world.addFreshEntity(entity);

        });
        entity.setCharacter(this);
        this.entity = entity;
        return entity;
    }

    public Entity spawnAtHome(ServerWorld world){
        Entity entity = spawnLoad(world,new Vector3d(homePos.getX(),homePos.getY(),homePos.getZ()));
        this.isAddedToWorld = true;
        return entity;

    }

    public HumanoidEntity getEntity(){return entity;}

    public Entity spawnLoad(ServerWorld world, Vector3d pos){
        CompoundNBT nbt = world.getServer().getLevel(world.OVERWORLD).getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts();
        if(nbt.contains(type.getRegistryName().getPath())){
        load((CompoundNBT) nbt.get(type.getRegistryName().getPath()));}
        return spawn(world,pos);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void saveNpc(){
        if(isDirty){

        }
    }



    public void setHome(ServerWorld world, BlockPos pos){
        this.homeWorld = world;
        this.homePos = pos;
    }

    public BlockPos getHomePos(){
        return homePos;
    }

    public ChunkPos getChunkHome(){
        return new ChunkPos(homePos);
    }

    public NpcType getType() {
        return type;
    }

    public void setType(NpcType type) {
        this.type = type;
    }

    public CompoundNBT save(){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("name",name);
        if(homePos!=null)nbt.putIntArray("homePos",new int[]{homePos.getX(), homePos.getY(), homePos.getZ()});
//        nbt.putBoolean("isHome",isHome);
        nbt.putInt("item", Item.getId(item));
        return nbt;
    }

    public void setDirty(){
        this.isDirty = true;
    }

    public void setDirty(boolean bool){
        this.isDirty = bool;
    }
    public void load(CompoundNBT nbt){
        this.name = nbt.getString("name");
        if(nbt.contains("homePos")) {
            int[] array = nbt.getIntArray("homePos");
            int x = array[0];
            int y = array[1];
            int z = array[2];
            homePos = new BlockPos(x, y, z);
        }
        item = Item.byId(nbt.getInt("item"));
    }

    public ResourceLocation getSkin(){return skin;}

    public SoundEvent getHurtSound(){
        return hurtSound;
    }

    public void setSkin(ResourceLocation skin){this.skin = skin;}

    public void tick() {
        if(entity!=null) {
            if (!this.entity.isAddedToWorld()) spawnAtHome(Minecraft.getInstance().getSingleplayerServer().overworld());
            if(!allNpcs.contains(this)){
                entity.remove();
            }

        }
    }
}
