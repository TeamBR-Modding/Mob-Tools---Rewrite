package com.pauljoda.mobtools.amulets;

import com.pauljoda.mobtools.MobTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MobToolsAmulet extends Item {

	public MobToolsAmulet(int par1) {
		super(par1);
		this.setUnlocalizedName("blankAmulet");
		this.setCreativeTab(MobTools.tabMobTools);
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack is)
	{ 
		return EnumRarity.uncommon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}

}