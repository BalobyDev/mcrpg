package net.baloby.mcrpg.data.characters;

import net.baloby.mcrpg.data.CharacterCapabilityProvider;
import net.baloby.mcrpg.data.characters.shop.ShopElement;
import net.baloby.mcrpg.data.dialouge.DialogueChain;
import net.baloby.mcrpg.data.dialouge.DialogueInsert;
import net.baloby.mcrpg.data.dialouge.DialogueTree;
import net.baloby.mcrpg.entities.HumanoidEntity;
import net.baloby.mcrpg.entities.HumanoidSlimEntity;
import net.baloby.mcrpg.mcrpg;
import net.baloby.mcrpg.setup.ModEntities;
import net.baloby.mcrpg.setup.ModItems;
import net.baloby.mcrpg.setup.ModSetup;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class Npc {

    public static ArrayList<Npc> allNpcs = new ArrayList<>();
    protected ITextComponent name;
    public boolean slim = false;
    public boolean isDirty;
    private ResourceLocation skin;
    public ItemStack item;
    public ItemStack headItem;
    public ItemStack chestItem;
    public ItemStack legsItem;
    public ItemStack feetItem;
    public ItemStack offhandItem;
    protected float size = 0.9375F;
    protected NpcType type;
    protected DialogueTree dialogueTree;
    private ResourceLocation dialogueIndex;
    private EntityType entityType;
    private DialogueInsert dialogueInsert;
    protected Vector3d homePos;
    protected ServerWorld homeWorld;
    public boolean isAddedToWorld;
    public SoundEvent hurtSound;
    private HumanoidEntity entity;
    public int bounty;

    public Npc(){
        this.name = new StringTextComponent("Nobody");
        this.skin = new ResourceLocation(mcrpg.MODID, "textures/entity/steve.png");
        this.entityType = ModEntities.HUMANOID.get();
    }

    public Npc(NpcType<?> type, ITextComponent name, EntityType entityType, ResourceLocation skin){
        this.item = new ItemStack(Items.AIR);
        this.type = type;
        this.name = name;
        this.entityType = entityType;
        if(type.equals(ModEntities.HUMANOID_SLIM)){slim = true;}
        this.skin = skin;
        this.hurtSound = SoundEvents.PLAYER_HURT;
        this.dialogueTree = new DialogueTree();
        this.isAddedToWorld = false;
        this.dialogueTree = new DialogueTree();
        this.offhandItem = new ItemStack(Items.AIR);
        this.headItem = new ItemStack(Items.AIR);
        this.chestItem = new ItemStack(Items.AIR);
        this.legsItem = new ItemStack(Items.AIR);
        this.feetItem = new ItemStack(Items.AIR);
    }

    public Npc(NpcType<?> type, ITextComponent name, EntityType entityType, ResourceLocation skin,float size){
        this(type, name, entityType, skin);
        this.size = size;
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

    public ITextComponent getName() {
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
            entity.setArmor(EquipmentSlotType.HEAD,headItem);
            entity.setArmor(EquipmentSlotType.CHEST, chestItem);
            entity.setArmor(EquipmentSlotType.LEGS, legsItem);
            entity.setArmor(EquipmentSlotType.FEET, feetItem);
            entity.setArmor(EquipmentSlotType.OFFHAND, offhandItem);

        return entity;
    }



    public Entity spawnAtHome(ServerWorld world){
        Entity entity = spawnLoad(world,new Vector3d(homePos.x(),homePos.y(),homePos.z()));
        this.isAddedToWorld = true;
        return entity;
    }

    public HumanoidEntity getEntity(){return entity;}

    public Entity spawnLoad(ServerWorld world, Vector3d pos){
        CompoundNBT nbt = world.getServer().getLevel(world.OVERWORLD).getCapability(CharacterCapabilityProvider.CHAR_CAP).resolve().get().getNbts();
        if(nbt.contains(type.getRegistryName().toString())){
        load((CompoundNBT) nbt.get(type.getRegistryName().toString()));}
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

    public void setHome(ServerWorld world, Vector3d pos){
        this.homeWorld = world;
        this.homePos = pos;
    }

    public boolean isAddedToWorld() {
        return entity!=null&&entity.isAddedToWorld();

    }

    public Vector3d getHomePos(){
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
        CompoundNBT homeNbt = new CompoundNBT();
        if(homePos!=null) {
            nbt.put("homePos", homeNbt);
            homeNbt.putDouble("x", homePos.x());
            homeNbt.putDouble("y", homePos.y());
            homeNbt.putDouble("z", homePos.z());
        }

        if(item!=null)nbt.put("item", item.serializeNBT());
        if(offhandItem!=null)nbt.put("offhand_item", offhandItem.serializeNBT());
        if(headItem!=null)nbt.put("head_item", headItem.serializeNBT());
        if(chestItem!=null)nbt.put("chest_item", chestItem.serializeNBT());
        if(legsItem!=null)nbt.put("legs_item", legsItem.serializeNBT());
        if(feetItem!=null)nbt.put("feet_item", feetItem.serializeNBT());

        if(dialogueIndex!=null) nbt.putString("dialogue_index", dialogueIndex.toString());
        CompoundNBT insert = new CompoundNBT();

        if(dialogueInsert!=null) {
            insert.putString("npc",dialogueInsert.getNpc().toString());
            insert.putString("dialogue",dialogueInsert.getDialogue().toString());
            insert.putString("response",dialogueInsert.getResponse());
            nbt.put("insert", insert);
        }

//        if(this instanceof ShopNpc){
//            CompoundNBT shop = new CompoundNBT();
//            for (int i = 0; i < ((ShopNpc) this).getShop().getShopItems().size(); i++) {
//                CompoundNBT temp = new CompoundNBT();
//                ShopElement element = ((ShopNpc) this).getShop().getShopItems().get(i);
//                temp.putString("id",element.getId().toString());
//                temp.putInt("cost",element.getCost());
//                shop.put(i+"",temp);
//            }
//        }
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
            CompoundNBT home = nbt.getCompound("homePos");
            double x = home.getDouble("x");
            double y = home.getDouble("y");
            double z = home.getDouble("z");
            homePos = new Vector3d(x, y, z);
        }
        dialogueIndex = new ResourceLocation(nbt.getString("dialogue_index"));
        dialogueInsert = new DialogueInsert(
                new ResourceLocation(nbt.getCompound("insert").getString("npc")),
                new ResourceLocation(nbt.getCompound("insert").getString("dialogue")),
                nbt.getCompound("insert").getString("response"));
        item = ItemStack.of(nbt.getCompound("item"));
        offhandItem = ItemStack.of(nbt.getCompound("offhand_item"));
        headItem = ItemStack.of(nbt.getCompound("head_item"));
        chestItem = ItemStack.of(nbt.getCompound("chest_item"));
        legsItem = ItemStack.of(nbt.getCompound("legsItem"));
        feetItem = ItemStack.of(nbt.getCompound("feet_item"));
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
