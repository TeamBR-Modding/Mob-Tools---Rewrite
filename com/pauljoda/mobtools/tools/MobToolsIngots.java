package com.pauljoda.mobtools.tools;

import com.pauljoda.mobtools.MobTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class MobToolsIngots extends Item {
	public MobToolsIngots(int id, String unlocalized){
		super(id);
		this.setUnlocalizedName(unlocalized);
		setCreativeTab(MobTools.tabMobTools);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}

}
