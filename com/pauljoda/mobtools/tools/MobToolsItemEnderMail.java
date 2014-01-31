package com.pauljoda.mobtools.tools;

import com.pauljoda.mobtools.MobTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MobToolsItemEnderMail extends Item {

	public MobToolsItemEnderMail(int par1, String unlocalized)
	{
		super(par1);
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(MobTools.tabMobTools);
		this.setMaxStackSize(8);
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
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}


}
