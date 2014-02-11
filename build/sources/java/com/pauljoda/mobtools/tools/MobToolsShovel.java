package com.pauljoda.mobtools.tools;

import java.util.ArrayList;
import java.util.List;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class MobToolsShovel extends ItemSpade {

	int ingot;
	int type;

	public MobToolsShovel(ToolMaterial par2EnumToolMaterial, String unlocalized, int ingot, int type) {
		super(par2EnumToolMaterial);
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(MobTools.tabMobTools);
		this.ingot = ingot;
		this.type = type;
	}


	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		//Shovel Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		switch(type)
		{
		case 1 :
			return false;
		case 2 : 
			World world1 = player.worldObj;
			Block blockID1 = world1.getBlock(x, y, z);
			int meta1 = world1.getBlockMetadata(x, y, z);

			NBTTagList ench = stack.getEnchantmentTagList();
			int level = 0;
			if(ench != null)
			{
				for (int x1 = 0; x1 < ench.tagCount(); x1++)
				{
					NBTTagCompound nbt = (NBTTagCompound) ench.getCompoundTagAt(x1);
					int id = nbt.getShort("id");
					if (id == Enchantment.fortune.effectId)
					{
						level = nbt.getShort("lvl");
					}
				}
			}
			ArrayList<ItemStack> items = blockID1.getDrops(world1, x, y, z, meta1, level);
			if(items == null || items.size() == 0)
			{
				return false;
			}
			for (ItemStack item : items)
			{
				if (item == null)
				{
					return false;
				}

				ItemStack copy = item.copy();

				world1.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
						blockID1.stepSound.getBreakSound(),
						(blockID1.stepSound.getVolume() + 1.0F) / 2.0F,
						blockID1.stepSound.getPitch() * 0.8F);

				world1.setBlockToAir(x, y, z);
				if(!world1.isRemote)
				{
					world1.playSoundEffect(x, y, z, "random.pop", .5F, 1.0F);
					player.playSound("random.pop", .5F, 1.0F);
					player.inventory.addItemStackToInventory(copy);
				}
				stack.damageItem(1, player);
			}

			return true;


		case 3 : 
			return false;

		case 4 :
			return false;


		default : return false;

		}
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(type == 1)
		{
			ToolHandler.creeperMine(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, "shovel");
			return true;
		}
		return false;

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
		
        par3List.add("\u00a76" + ToolHandler.getInformation(3, type, ""));
    }
}
