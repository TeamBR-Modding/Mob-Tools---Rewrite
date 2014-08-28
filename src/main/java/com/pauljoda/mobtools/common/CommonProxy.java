package com.pauljoda.mobtools.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pauljoda.mobtools.containers.ContainerEnderMail;
import com.pauljoda.mobtools.containers.ContainerEnderPackage;
import com.pauljoda.mobtools.containers.ContainerInfusingFurnace;
import com.pauljoda.mobtools.containers.ContainerRepairAlter;
import com.pauljoda.mobtools.containers.InventoryMail;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPackage;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPad;
import com.pauljoda.mobtools.tileentities.TileEntityInfusingFurnace;
import com.pauljoda.mobtools.tileentities.TileEntityMobToolsSpawner;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {
	
	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityInfusingFurnace.class, "tileEntityInfusingFurnace");
		GameRegistry.registerTileEntity(TileEntityEnderPad.class, "tileEntityEnderPad");
		GameRegistry.registerTileEntity(TileEntityMobToolsSpawner.class, "tileEntityMobToolsSpawner");
		GameRegistry.registerTileEntity(TileEntityRepairAlter.class, "repairAlter");
		GameRegistry.registerTileEntity(TileEntityEnderPackage.class, "enderPackage");
	}

	public Object getServerGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z)
	{
		
		if(guiID == 6)
		{
			return new ContainerEnderMail(player.inventory, new InventoryMail(player.getHeldItem()), world);
		}

		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null && tileEntity instanceof TileEntityInfusingFurnace)
		{
			return new ContainerInfusingFurnace(player.inventory, (TileEntityInfusingFurnace) tileEntity);
		}
		
		if(tileEntity != null && tileEntity instanceof TileEntityRepairAlter)
		{
			return new ContainerRepairAlter(player.inventory, (TileEntityRepairAlter) tileEntity);
		}
		
		if(tileEntity != null && tileEntity instanceof TileEntityEnderPackage)
			return new ContainerEnderPackage(player.inventory, (TileEntityEnderPackage)tileEntity);


		return null;
	}

	public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public void registerRenderers() {
	}


}
