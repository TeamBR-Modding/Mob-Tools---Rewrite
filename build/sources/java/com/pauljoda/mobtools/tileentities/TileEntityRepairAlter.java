package com.pauljoda.mobtools.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
			if(inv[0].getUnlocalizedName().equals(ToolManager.creepium.getUnlocalizedName()) && inv[1].getUnlocalizedName().equals(ToolManager.endium.getUnlocalizedName())
					&& inv[2].getUnlocalizedName().equals(ToolManager.spidium.getUnlocalizedName()) && inv[3].getUnlocalizedName().equals(ToolManager.blazium.getUnlocalizedName()))
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


		NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
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
		markDirty();
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
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
				player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

}
