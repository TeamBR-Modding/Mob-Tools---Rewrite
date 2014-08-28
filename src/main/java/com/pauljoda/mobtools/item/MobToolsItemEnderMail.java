package com.pauljoda.mobtools.item;

import java.util.List;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MobToolsItemEnderMail extends Item {

	public MobToolsItemEnderMail()
	{
		super();
		this.setUnlocalizedName("enderMail");
		this.setCreativeTab(MobTools.tabMobTools);
		this.setMaxStackSize(8);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		//Information Types
		//Sword: 1
		//Pick: 2
		//Shovel: 3
		//Hoe: 4
		//Axe: 5
		//Wand: 6
		//Items: 7

		par3List.add("\u00a76" + ToolHandler.getInformation(7, 0, this.getUnlocalizedName()));

	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {

		if (!world.isRemote) 
		{
			entityPlayer.openGui(MobTools.instance, 6, world, (int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ);
		}
		return itemStack;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}


}
