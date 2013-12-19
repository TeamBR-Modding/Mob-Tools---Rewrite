package com.pauljoda.mobtools.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMobToolsSpawner extends TileEntity implements IInventory {

	public ItemStack[] inv;
	public int cooldown = 0;
	public int maxCoolDown = 600;
	public int spawnRate = 1;
	public String dimension = "Overworld";
	public int tier = 0;
	public String mobName = "Pig";
	public boolean isActive = false;

	public TileEntityMobToolsSpawner()
	{
		inv = new ItemStack[1];
	}


	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote && isActive && this.worldObj != null)
		{
			if(cooldown < 0 && MobToolsSpawnerLogic.canSpawn(this, worldObj, xCoord, yCoord, zCoord, tier, mobName, dimension))
			{
				for(int i = 0; i < spawnRate; i++)
					MobToolsSpawnerLogic.randomSpawn(MobToolsSpawnerLogic.getEntityByName(mobName, worldObj), worldObj, xCoord, yCoord, zCoord);
				cooldown = maxCoolDown;
			}
			--cooldown;
		}

		if(tier == 5 && worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) != 0 && inv[0] != null)
			this.isActive = false;
		else if(tier == 5 && worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) == 0 && inv[0] != null)
			this.isActive = true;
		else if(tier != 5 && inv[0] != null)
			this.isActive = true;
		if(worldObj.isRemote && isActive)
			spawnParticles();

	}

	@SideOnly(Side.CLIENT)
	private void spawnParticles()
	{
		double d1 = (double)((float)xCoord + worldObj.rand.nextFloat());
		double d2 = (double)((float)yCoord + worldObj.rand.nextFloat());
		double d0 = (double)((float)zCoord + worldObj.rand.nextFloat());
		worldObj.spawnParticle("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
		worldObj.spawnParticle("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);
	}


	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		cooldown = tagCompound.getInteger("cooldown");
		spawnRate = tagCompound.getInteger("spawnRate");
		dimension = tagCompound.getString("dimension");
		mobName = tagCompound.getString("mobName");
		isActive = tagCompound.getBoolean("isActive");


		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);

		tagCompound.setInteger("cooldown", cooldown);
		tagCompound.setInteger("spawnRate", spawnRate);
		tagCompound.setString("dimension", dimension);
		tagCompound.setString("mobName", mobName);
		tagCompound.setBoolean("isActive", isActive);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}

	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		this.readFromNBT(pkt.data);
	}


	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}               
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
				player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}


}
