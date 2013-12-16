package com.pauljoda.mobtools.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.pauljoda.mobtools.common.CommonProxy;
import com.pauljoda.mobtools.infusion.GuiInfusingFurnace;
import com.pauljoda.mobtools.infusion.TileEntityInfusingFurnace;

public class ClientProxy extends CommonProxy {

	@Override
	public int registerRenderers() {
		return 0;
	}



	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{

		TileEntityInfusingFurnace tileEntity = (TileEntityInfusingFurnace)world.getBlockTileEntity(x, y, z);

		if(tileEntity != null)
			return new GuiInfusingFurnace(player.inventory, tileEntity);

		return null;
	}

}
