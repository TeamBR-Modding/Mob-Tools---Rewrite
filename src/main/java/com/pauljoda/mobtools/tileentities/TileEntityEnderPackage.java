package com.pauljoda.mobtools.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.pauljoda.mobtools.blocks.BlockManager;

public class TileEntityEnderPackage extends TileEntity implements IInventory {

	public ItemStack[] chestContents;
	public boolean isOriginal = true;
	public boolean shouldDropContents = true;

	private int ticksSinceSync = -1;
	private int numUsingPlayers;
	/**
	 * The number of players currently using this chest
	 */
	public int numPlayersUsing;
	/**
	 * Server sync counter (once per 20 ticks)
	 */
	private String customName;
	public float prevLidAngle;
	public float lidAngle;
	private static final String __OBFID = "CL_00000346";

	public TileEntityEnderPackage() {
		chestContents = new ItemStack[28];
	};

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory()
	{
		return 27;
	}

	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.chestContents[par1];
	}


	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.chestContents[par1] != null)
		{
			ItemStack itemstack;

			if (this.chestContents[par1].stackSize <= par2)
			{
				itemstack = this.chestContents[par1];
				this.chestContents[par1] = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.chestContents[par1].splitStack(par2);

				if (this.chestContents[par1].stackSize == 0)
				{
					this.chestContents[par1] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}


	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.chestContents[par1] != null)
		{
			ItemStack itemstack = this.chestContents[par1];
			this.chestContents[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.chestContents[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	/**
	 * Returns the name of the inventory
	 */
	@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.customName : "container.enderPackage";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return this.customName != null && this.customName.length() > 0;
	}

	public void func_145976_a(String p_145976_1_)
	{
		this.customName = p_145976_1_;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.chestContents = new ItemStack[this.getSizeInventory()];
		this.isOriginal = tag.getBoolean("Is Original");
		this.shouldDropContents = tag.getBoolean("Should Drop");
		this.lidAngle = tag.getFloat("Lid Angle");
		this.prevLidAngle = tag.getFloat("Prev Angle");

		if (tag.hasKey("CustomName", 8))
		{
			this.customName = tag.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length)
			{
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();
		tag.setBoolean("Is Original", this.isOriginal);
		tag.setBoolean("Should Drop", shouldDropContents);
		tag.setFloat("Lid Angle", lidAngle);
		tag.setFloat("Prev Angle", prevLidAngle);

		for (int i = 0; i < this.chestContents.length; ++i)
		{
			if (this.chestContents[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
		{
			tag.setString("CustomName", this.customName);
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.prevLidAngle = this.lidAngle;
		float var1 = 0.1F;
		if ((this.numUsingPlayers > 0) && (this.lidAngle == 0.0F))
		{
			double var8 = this.xCoord + 0.5D;
			double var11 = this.zCoord + 0.5D;

			this.worldObj.playSoundEffect(var8, this.yCoord + 0.5D, var11, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
		if (((this.numUsingPlayers == 0) && (this.lidAngle > 0.0F)) || ((this.numUsingPlayers > 0) && (this.lidAngle < 1.0F)))
		{
			float var9 = this.lidAngle;
			if (this.numUsingPlayers > 0) {
				this.lidAngle += var1;
			} else {
				this.lidAngle -= var1;
			}
			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}
			float var10 = 0.5F;
			if ((this.lidAngle < var10) && (var9 >= var10))
			{
				double var11 = this.xCoord + 0.5D;
				double var6 = this.zCoord + 0.5D;

				this.worldObj.playSoundEffect(var11, this.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int i, int j)
	{
		if (i == 1)
		{
			this.numUsingPlayers = j;
			return true;
		}
		return super.receiveClientEvent(i, j);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}


	@Override
	public void openInventory()
	{
		if (worldObj == null) return;
		numUsingPlayers++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockManager.enderPackage, 1, numUsingPlayers);
	}

	@Override
	public void closeInventory()
	{
		if (worldObj == null) return;
		numUsingPlayers--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockManager.enderPackage, 1, numUsingPlayers);
	}


	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}

}
