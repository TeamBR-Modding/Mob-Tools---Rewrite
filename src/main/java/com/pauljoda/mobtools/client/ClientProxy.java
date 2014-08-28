package com.pauljoda.mobtools.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import com.pauljoda.mobtools.blocks.BlockManager;
import com.pauljoda.mobtools.common.CommonProxy;
import com.pauljoda.mobtools.containers.ContainerEnderMail;
import com.pauljoda.mobtools.containers.ContainerEnderPackage;
import com.pauljoda.mobtools.containers.InventoryMail;
import com.pauljoda.mobtools.gui.GuiEnderMail;
import com.pauljoda.mobtools.gui.GuiEnderPackage;
import com.pauljoda.mobtools.gui.GuiInfusingFurnace;
import com.pauljoda.mobtools.gui.GuiRepairAlter;
import com.pauljoda.mobtools.lib.Reference;
import com.pauljoda.mobtools.rendering.TileEntityEnderPackageItemRenderer;
import com.pauljoda.mobtools.rendering.TileEntityEnderPackageRenderer;
import com.pauljoda.mobtools.rendering.TileEntityInfuserItemRenderer;
import com.pauljoda.mobtools.rendering.TileEntityInfuserRenderer;
import com.pauljoda.mobtools.rendering.TileEntityRepairAlterItemRenderer;
import com.pauljoda.mobtools.rendering.TileEntityRepairAlterRenderer;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPackage;
import com.pauljoda.mobtools.tileentities.TileEntityInfusingFurnace;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() 
	{
		Reference.repairAlterRenderID = RenderingRegistry.getNextAvailableRenderId();
		Reference.infuserRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.repairAlter), new TileEntityRepairAlterItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRepairAlter.class, new TileEntityRepairAlterRenderer());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.enderPackage), new TileEntityEnderPackageItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnderPackage.class, new TileEntityEnderPackageRenderer());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.infusingFurnace), new TileEntityInfuserItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfusingFurnace.class, new TileEntityInfuserRenderer());
	}



	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if(id == 6)
			return new GuiEnderMail((ContainerEnderMail) new ContainerEnderMail(player.inventory, new InventoryMail(player.getHeldItem()), world));


		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null && tileEntity instanceof TileEntityInfusingFurnace)
			return new GuiInfusingFurnace(player.inventory, (TileEntityInfusingFurnace) tileEntity);
		
		if(tileEntity != null && tileEntity instanceof TileEntityRepairAlter)
			return new GuiRepairAlter((TileEntityRepairAlter) tileEntity, player.inventory);
		
		if(tileEntity != null && tileEntity instanceof TileEntityEnderPackage)
			return new GuiEnderPackage((ContainerEnderPackage) new ContainerEnderPackage(player.inventory, (TileEntityEnderPackage)tileEntity));

		return null;
	}

}
