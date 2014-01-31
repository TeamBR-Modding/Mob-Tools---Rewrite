package com.pauljoda.mobtools.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

import com.pauljoda.mobtools.tools.ToolManager;

public class TileEntityRepairAlter extends TileEntity implements IInventory {

	public ItemStack[] inv;
	int countdown = 20;
	public boolean isActive = false;
	Random r = new Random();

	public TileEntityRepairAlter()
	{
		inv = new ItemStack[5];
	}

	@Override
	public void updateEntity()
	{
		if(countdown < 0)
		{
			if(checkForGems())
			{
				if(inv[4] != null)
				{
					if(inv[4].isItemDamaged())
					{
						inv[4].setItemDamage(inv[4].getItemDamage() - 1);

						for(int i = 0; i < 4; i++)
							inv[i].setItemDamage(inv[i].getItemDamage() + 1);

						isActive = true;
						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

					}
					else
						isActive = false;
				}
				else
					isActive = false;
			}
			else
				isActive = false;


			countdown = 20;
		}

		--countdown;
	}


	/**
	 * Makes Sure that the gems are in the slot
	 * @return True if gems are in correct slots
	 */
	public boolean checkForGems()
	{
		if(inv[0] != null && inv[1] != null && inv[2] != null && inv[3] != null)
		{
			if(inv[0].itemID == ToolManager.creepium.itemID && inv[1].itemID == ToolManager.endium.itemID
					&& inv[2].itemID == ToolManager.spidium.itemID && inv[3].itemID == ToolManager.blazium.itemID)
			{
				for(int i = 0; i < 4; i++)
				{
					if(inv[i].getItemDamage() >= inv[i].getMaxDamage() - 1)
					{
						inv[i] = null;
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}


	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);


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
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
		readFromNBT(pkt.data);
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
		onInventoryChanged();
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
		return 64;
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public String getInvName() {
		return "mobtools.repairalter";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}


}