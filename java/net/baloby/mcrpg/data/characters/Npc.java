package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.dialouge.DialogueChain;
import net.baloby.mcrpg.data.dialouge.DialogueInsert;
import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class Npc {

    public static ArrayList<Npc> allNpcs = new ArrayList<>();
    protected String name;
    public boolean slim = false;
    public boolean isDirty;
    private ResourceLocation skin;
    public Item item;
    protected float size = 0.9375F;
    protected NpcType type;
    protected DialogueTree dialogueTree;
    private ResourceLocation dialogueIndex;
    private EntityType entityType;
    private DialogueInsert dialogueInsert;
    protected BlockPos homePos;
    protected ServerWorld homeWorld;
    public boolean isAddedToWorld;
    public SoundEvent hurtSound;
    private HumanoidEntity entity;

    public Npc(){
        this.name = "Nobody";
        this.skin = new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png");
        this.entityType = ModEntities.HUMANOID.get();
    }

    public Npc(NpcType<?> type, String name, EntityType entityType, ResourceLocation skin){
        this.type = type;
        this.name = name;
        this.entityType = entityType;
        if(type.equals(ModEntities.HUMANOID_SLIM)){slim = true;}
        this.skin = skin;
        this.hurtSound = SoundEvents.PLAYER_HURT;
        this.dialogueTree = new DialogueTree();
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
        HumanoidEntity entity = new HumanoidSlimEntity(entityType,world,this);
        entity.moveTo(pos.x,pos.y,pos.z);
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

    public void talk(ServerPlayerEntity player){
        if(dialogueIndex==null||dialogueIndex.getPath().equals("")) {
            if (this.dialogueTree.chains.size() > 0) this.dialogueTree.chains.get(0).start(player);
        }
        else {
            DialogueChain chain = ModSetup.DIALOGUE_MANAGER.getData(dialogueIndex);
            chain.start(player);
        }
    }

    public void setHome(ServerWorld world, BlockPos pos){
        this.homeWorld = world;
        this.homePos = pos;
    }

    public BlockPos getHomePos(){
        return homePos;
    }

    public NpcType getType() {
        return type;
    }

    public EntityType getEntityType(){return entityType;}

    public void setType(NpcType type) {
        this.type = type;
    }

    public CompoundNBT save(){
        CompoundNBT nbt = new CompoundNBT();
        if(homePos!=null)nbt.putIntArray("homePos",new int[]{homePos.getX(), homePos.getY(), homePos.getZ()});
        nbt.putInt("item", Item.getId(item));
        if(dialogueIndex!=null) nbt.putString("dialogue_index", dialogueIndex.toString());
        CompoundNBT insert = new CompoundNBT();
        if(dialogueInsert!=null) {
            insert.putString("npc",dialogueInsert.getNpc().toString());
            insert.putString("dialogue",dialogueInsert.getDialogue().toString());
            insert.putString("response",dialogueInsert.getResponse());
            nbt.put("insert", insert);
        }

        return nbt;
    }

    public void setDirty(){
        this.isDirty = true;
    }

    public void setDirty(boolean bool){
        this.isDirty = bool;
    }
    public void load(CompoundNBT nbt){
        if(nbt==null)return;
        if(nbt.contains("homePos")) {
            int[] array = nbt.getIntArray("homePos");
            int x = array[0];
            int y = array[1];
            int z = array[2];
            homePos = new BlockPos(x, y, z);
        }
        dialogueIndex = new ResourceLocation(nbt.getString("dialogue_index"));
        dialogueInsert = new DialogueInsert(
                new ResourceLocation(nbt.getCompound("insert").getString("npc")),
                new ResourceLocation(nbt.getCompound("insert").getString("dialogue")),
                nbt.getCompound("insert").getString("response"));
        item = Item.byId(nbt.getInt("item"));
    }

    public float getSize() {
        return size;
    }

    public ResourceLocation getSkin(){return skin;}

    public boolean getSlim(){return slim;}

    public SoundEvent getHurtSound(){
        return hurtSound;
    }

    public void setSkin(ResourceLocation skin){this.skin = skin;}

    public void setDialogueIndex(ResourceLocation index){
        this.dialogueIndex = index;
    }

    public DialogueInsert getDialogueInsert() {
        return dialogueInsert;
    }

    public void setDialogueInsert(DialogueInsert dialogueInsert) {
        this.dialogueInsert = dialogueInsert;
    }

    public DialogueTree getTree(){
        return dialogueTree;
    }

    public void tick() {
        if(entity!=null) {
            if (!this.entity.isAddedToWorld()) spawnAtHome(entity.getServer().overworld());
            if(!allNpcs.contains(this)){
                entity.remove();
            }
        }
    }

}
