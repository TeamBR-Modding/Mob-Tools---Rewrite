package com.pauljoda.mobtools.containers;

import com.pauljoda.mobtools.tileentities.TileEntityMobToolsSpawner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMobSpawner extends Container {
	
	private TileEntityMobToolsSpawner tileEntity;

	public ContainerMobSpawner(InventoryPlayer playerInventory, TileEntityMobToolsSpawner tileEntity)
    {
		 this.tileEntity = tileEntity;
	        
	        // Input
	        addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

}
