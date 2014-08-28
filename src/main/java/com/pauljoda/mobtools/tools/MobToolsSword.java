package com.pauljoda.mobtools.tools;

import java.util.List;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class MobToolsSword extends ItemSword {

	int ingot;
	int type;
	String material;
	
	public MobToolsSword(ToolMaterial par2EnumToolMaterial, String unlocalized, int ingot, int type, String Material) {
		super(par2EnumToolMaterial);
		this.setUnlocalizedName(unlocalized);
		this.ingot = ingot;
		this.type = type;
		this.material = Material;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{		
		super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
		ToolHandler.swordEffect(type, par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
		return true;
	}
	
	@Override
	 public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		ToolHandler.swordBlockEffect(type, par1ItemStack, par2World, par3EntityPlayer);
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
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
		
        par3List.add("\u00a76" + ToolHandler.getInformation(1, type, ""));
        par3List.add(material);
    }

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));

	}

}
