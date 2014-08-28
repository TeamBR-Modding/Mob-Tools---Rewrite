package com.pauljoda.mobtools.tools;

import java.util.List;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class MobToolsHoe extends ItemHoe {
	int ingot;
	int type;
	String material;

	public MobToolsHoe(ToolMaterial par2EnumToolMaterial, String unlocalized, int ingot, int type, String material) {
		super(par2EnumToolMaterial);
		this.setUnlocalizedName(unlocalized);
		this.ingot = ingot;
		this.type = type;
		this.material = material;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		//Hoe Types:
		//1: Creeper
		//2: Ender				
		//3: Spider
		//4: Blaze

		switch(type)
		{

		case 1 :

			for(int xPos = -1; xPos <= 1; xPos++)
			{
				for(int zPos = -1; zPos <= 1; zPos++)
				{
					if (!par2EntityPlayer.canPlayerEdit(par4 + xPos, par5, par6 + zPos, par7, par1ItemStack))
					{
						return false;
					}
					else
					{
						UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4 + xPos, par5, par6 + zPos);


						if (event.getResult() == Result.ALLOW)
						{
							par1ItemStack.damageItem(1, par2EntityPlayer);

						}

						Block i1 = par3World.getBlock(par4 + xPos, par5, par6 + zPos);
						boolean air = par3World.isAirBlock(par4 + xPos, par5 + 1, par6 + zPos);

						if (par7 != 0 && air && (i1 == Blocks.grass || i1 == Blocks.dirt))
						{
							Block block = Blocks.farmland;
							par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

							par3World.setBlock(par4 + xPos, par5, par6 + zPos, block);
							par1ItemStack.damageItem(1, par2EntityPlayer);
						}

					}
				}
			}
			return true;
		case 2 : super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		return true;

		case 3 : 
			if(par2EntityPlayer.isSneaking())
			{
				if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
				{
					return false;
				}
				else
				{
					if (ToolHandler.applyBonemeal(par1ItemStack, par3World, par4, par5, par6, par2EntityPlayer))
					{
						if (!par3World.isRemote)
						{
							par3World.playAuxSFX(2005, par4, par5, par6, 0);
							par1ItemStack.damageItem(1, par2EntityPlayer);
						}

						return true;
					}
				}
			}
			else
				super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
			return true;

		case 4 : super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		return true;

		default : return false;

		}

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));

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

		par3List.add("\u00a76" + ToolHandler.getInformation(4, type, ""));
		par3List.add(material);
	}
}
