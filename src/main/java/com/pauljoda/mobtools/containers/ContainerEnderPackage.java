package com.pauljoda.mobtools.containers;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.pauljoda.mobtools.blocks.BlockManager;
import com.pauljoda.mobtools.rendering.ParticleHelper;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPackage;

public class ContainerEnderPackage extends Container {

	protected TileEntityEnderPackage enderPackage;

	public ContainerEnderPackage(InventoryPlayer playerInventory, TileEntityEnderPackage ender)
	{
		enderPackage = ender;
		

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(enderPackage, j + i * 9, 8 + j * 18, 32 + i * 18));
			}
		}

		for (int j = 0; j < 3; ++j)
		{
			for (int k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 100 + j * 18));
			}
		}

		for (int j = 0; j < 9; ++j)
		{
			this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 158));
		}

	}


	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if(par1EntityPlayer.worldObj.isRemote)
				System.out.print(par2);
			if (par2 < 27)
			{
				if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, 27, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		if(!this.enderPackage.getWorldObj().isRemote)
			enderPackage.closeInventory();
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return enderPackage.isUseableByPlayer(var1);
	}

	public boolean setNewPackage(String userName, String sender)
	{
		World world = this.enderPackage.getWorldObj();
		List<EntityPlayerMP> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		for(int i = 0; i < players.size(); i++)
		{
			EntityPlayerMP recipient = players.get(i);
			if(recipient.getDisplayName().equals(userName))
			{

				ItemStack contents[] = enderPackage.chestContents;

				int goup;
				if(recipient.worldObj.getBlock((int)recipient.posX, (int)recipient.posY, (int)recipient.posZ) == Blocks.air)
				{
					recipient.worldObj.setBlock((int)recipient.posX, (int)recipient.posY, (int)recipient.posZ, BlockManager.enderPackage);
					goup = 0;
				}
				else
				{
					recipient.worldObj.setBlock((int)recipient.posX, (int)recipient.posY + 1, (int)recipient.posZ, BlockManager.enderPackage);
					goup = 1;
				}

				TileEntityEnderPackage newPackage = (TileEntityEnderPackage) recipient.worldObj.getTileEntity((int)recipient.posX, (int)recipient.posY + goup, (int)recipient.posZ);

				if(contents != null && contents.length > 0)
				{
					newPackage.chestContents = contents;
				}

				newPackage.isOriginal = false;
				enderPackage.shouldDropContents = false;
				world.setBlockToAir(enderPackage.xCoord, enderPackage.yCoord, enderPackage.zCoord);
				ParticleHelper.spawnEnderCircles(enderPackage.getWorldObj(), enderPackage.xCoord, enderPackage.yCoord, enderPackage.zCoord);

				recipient.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + sender + " has sent you a package"));
				return true;
			}	
		}

		return false;
	}
}

