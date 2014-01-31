package com.pauljoda.mobtools.containers;

import com.pauljoda.mobtools.handlers.GeneralSettings;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;
import com.pauljoda.mobtools.tools.MobToolsItem;
import com.pauljoda.mobtools.tools.ToolManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class SlotRepairAlter extends Slot {

	private int type;

	private TileEntityRepairAlter alter;
	public SlotRepairAlter(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k, int type)
	{
		super(iinventory, i, j, k);
		this.type = type;
	}


	public int getSlotStackLimit()
	{
		return 1;
	}

	public boolean isItemValid(ItemStack par1ItemStack)
	{

		//		|| par1ItemStack.itemID == ToolManager.spidium.itemID || par1ItemStack.itemID == ToolManager.blazium.itemID)

		//Types
		//0 Creepium
		//1 Endium
		//2 Spidium
		//3 Blazium
		switch(type)
		{
		case 0 : 
			if(par1ItemStack.itemID == ToolManager.creepium.itemID)
				return true;
			else 
				return false;

		case 1 : 
			if(par1ItemStack.itemID == ToolManager.endium.itemID)
				return true;
			else
				return false;
		case 2 :
			if(par1ItemStack.itemID == ToolManager.spidium.itemID)
				return true;
			else
				return false;
		case 3 : 
			if(par1ItemStack.itemID == ToolManager.blazium.itemID)
				return true;
			else
				return false;
			
		
		default : return false;
		}

	}

	public Icon getBackgroundIconIndex()
	{
		return MobToolsItem.backGround;
	}

}
