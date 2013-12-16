package com.pauljoda.mobtools.tools;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class MobToolsWand extends Item {

	int type;
	int block = 0;

	public MobToolsWand(int par1, String unlocalized, int type, int damage)
	{
		super(par1);
		this.setMaxDamage(damage);
		this.setUnlocalizedName(unlocalized);
		setCreativeTab(MobTools.tabMobTools);
		this.type = type;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		ToolHandler.wandEffect(par1ItemStack, par2World, par3EntityPlayer, type);
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

		par3List.add("\u00a75" + ToolHandler.getInformation(6, type));

		if(type == 2)
		{

			if (par1ItemStack.stackTagCompound != null) {
				String owner = par1ItemStack.stackTagCompound.getString("owner");
				par3List.add("Owner: " + owner);
				int layers = par1ItemStack.stackTagCompound.getInteger("layers");
				par3List.add("Area: " + (layers * 2 - 1) + "x" + (layers * 2 -1));

				if(par1ItemStack.stackTagCompound.getInteger("block") != 0)
				{
					String block = Block.blocksList[par1ItemStack.stackTagCompound.getInteger("block")].getLocalizedName();
					par3List.add("\u00a76" + "Current Block: " + block);
					if(par1ItemStack.stackTagCompound.getInteger("meta") != 0)
						par3List.add("Meta Data: " + par1ItemStack.stackTagCompound.getInteger("meta"));

				}	
			}
		}
	}


	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{

		if(type == 2)
		{

			if(par2EntityPlayer.isSneaking())
			{
				if(par1ItemStack.stackTagCompound == null)
				{
					this.block = par3World.getBlockId(par4, par5, par6);
					par1ItemStack.stackTagCompound = new NBTTagCompound();
					par1ItemStack.stackTagCompound.setString("owner", par2EntityPlayer.username);

					par1ItemStack.stackTagCompound.setInteger("block", this.block);
					int meta = par3World.getBlockMetadata(par4, par5, par6);
					par1ItemStack.stackTagCompound.setInteger("meta", meta);

					par1ItemStack.stackTagCompound.setInteger("layers", 3);
					return false;
				}
				
				this.block = par3World.getBlockId(par4, par5, par6);
				par1ItemStack.stackTagCompound.setInteger("block", this.block);
				int meta = par3World.getBlockMetadata(par4, par5, par6);
				par1ItemStack.stackTagCompound.setInteger("meta", meta);

				if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && par1ItemStack.stackTagCompound.getInteger("layers") != 0)
				{
					int layers = par1ItemStack.stackTagCompound.getInteger("layers");
					if(layers == 5)
					{
						layers = 0;
					}
					layers = layers + 1;
					par1ItemStack.stackTagCompound.setInteger("layers", layers);
					if(!par3World.isRemote)
						par2EntityPlayer.addChatMessage(EnumChatFormatting.AQUA + "Area set to: " +(layers * 2 - 1) + "x" + (layers * 2 - 1));
					return false;
				}

				return false;
			}

			if(par1ItemStack.stackTagCompound != null)
				return ToolHandler.exhangeBlocks(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par1ItemStack.stackTagCompound.getInteger("block"), par1ItemStack.stackTagCompound.getInteger("meta"));

		}
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack is)
	{ 
		return EnumRarity.epic;
	}

	@Override
	public boolean hasEffect(ItemStack is)
	{ 
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));

	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}


}