package com.pauljoda.mobtools.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pauljoda.mobtools.common.CommonProxy;
import com.pauljoda.mobtools.gui.GuiRepairAlter;
import com.pauljoda.mobtools.infusion.GuiInfusingFurnace;
import com.pauljoda.mobtools.infusion.TileEntityInfusingFurnace;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;

public class ClientProxy extends CommonProxy {

	@Override
	public int registerRenderers() {
		return 0;
	}



	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{

		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null && tileEntity instanceof TileEntityInfusingFurnace)
			return new GuiInfusingFurnace(player.inventory, (TileEntityInfusingFurnace) tileEntity);
		
		if(tileEntity != null && tileEntity instanceof TileEntityRepairAlter)
			return new GuiRepairAlter((TileEntityRepairAlter) tileEntity, player.inventory);

		return null;
	}

}
