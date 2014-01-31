package com.pauljoda.mobtools.containers;

import com.pauljoda.mobtools.handlers.GeneralSettings;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerRepairAlter extends Container {


	protected TileEntityRepairAlter alter;

	public ContainerRepairAlter(InventoryPlayer inventoryPlayer, TileEntityRepairAlter alter)
	{
		this.alter = alter;

		addSlotToContainer(new SlotRepairAlter(inventoryPlayer.player, alter, 0, 48, 18, 0));
		addSlotToContainer(new SlotRepairAlter(inventoryPlayer.player, alter, 1, 112, 18, 1));
		addSlotToContainer(new SlotRepairAlter(inventoryPlayer.player, alter, 2, 48, 52, 2));
		addSlotToContainer(new SlotRepairAlter(inventoryPlayer.player, alter, 3, 112, 52, 3));
		addSlotToContainer(new Slot(alter, 4, 80, 35));

		bindPlayerInventory(inventoryPlayer);
	}

	private void bindPlayerInventory(InventoryPlayer playerInventory)
	{
		// Inventory
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 9; x++)
				addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));

		// Action Bar
		for(int x = 0; x < 9; x++)
			addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142));
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 4)
			{
				if (!this.mergeItemStack(itemstack1, 5, 41, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 >= 5 && par2 < 32)
			{
				if (!this.mergeItemStack(itemstack1, 32, 41, false))
				{
					return null;
				}
			}
			else if (par2 >= 32 && par2 < 41)
			{
				if (!this.mergeItemStack(itemstack1, 5, 32, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 5, 41, false))
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

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}


	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
