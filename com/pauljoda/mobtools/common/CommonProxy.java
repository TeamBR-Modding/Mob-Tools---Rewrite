package com.pauljoda.mobtools.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.pauljoda.mobtools.infusion.ContainerInfusingFurnace;
import com.pauljoda.mobtools.infusion.TileEntityInfusingFurnace;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPad;
import com.pauljoda.mobtools.tileentities.TileEntityMobToolsSpawner;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler{
	
	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityInfusingFurnace.class, "tileEntityInfusingFurnace");
		GameRegistry.registerTileEntity(TileEntityEnderPad.class, "tileEntityEnderPad");
		GameRegistry.registerTileEntity(TileEntityMobToolsSpawner.class, "tileEntityMobToolsSpawner");
	}

	public Object getServerGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityInfusingFurnace tileEntity = (TileEntityInfusingFurnace)world.getBlockTileEntity(x, y, z);

		if(tileEntity != null)
		{
			return new ContainerInfusingFurnace(player.inventory, tileEntity);
		}


		return null;
	}

	public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public int registerRenderers() {
		return 0;
	}


}
