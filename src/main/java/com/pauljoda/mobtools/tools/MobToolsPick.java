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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class MobToolsPick extends ItemPickaxe {

	int ingot;
	int type;
	int level;
	String material;
	public MobToolsPick(ToolMaterial par2EnumToolMaterial, String unlocalized, int ingot, int type, int level, String material) {
		super(par2EnumToolMaterial);
		this.setUnlocalizedName(unlocalized);
		this.ingot = ingot;
		this.type = type;
		this.level = level;
		this.material = material;
	}
	

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		//Pick Types:
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
					//world1.playSoundEffect(x, y, z, "random.pop", .5F, 1.0F);
					//player.playSound("random.pop", .5F, 1.0F);
					world1.spawnEntityInWorld(new EntityItem(world1, player.posX, player.posY + 2, player.posZ, copy));
					//player.inventory.addItemStackToInventory(copy);
				}
				stack.damageItem(1, player);
			}

			return true;


		case 3 : 

			World world3 = player.worldObj;
			Block blockID3 = world3.getBlock(x, y, z);
			int meta3 = world3.getBlockMetadata(x, y, z);

			ItemStack items2 = new ItemStack(blockID3, 1, meta3);

			world3.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
					blockID3.stepSound.getBreakSound(),
					(blockID3.stepSound.getVolume() + 1.0F) / 2.0F,
					blockID3.stepSound.getPitch() * 0.8F);

			world3.setBlockToAir(x, y, z);
			if(!world3.isRemote)
			{
				ItemStack fineTouch = items2.copy();
				float f = world3.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = world3.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = world3.rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityitem = new EntityItem(world3, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), fineTouch);

				world3.spawnEntityInWorld(entityitem);
			}
			stack.damageItem(1, player);

			return true;

		case 4 : 
			World world11 = player.worldObj;
			Block blockID11 = world11.getBlock(x, y, z);
			int meta11 = world11.getBlockMetadata(x, y, z);

			NBTTagList ench1 = stack.getEnchantmentTagList();
			int level1 = 0;
			if(ench1 != null)
			{
				for (int x1 = 0; x1 < ench1.tagCount(); x1++)
				{
					NBTTagCompound nbt = (NBTTagCompound) ench1.getCompoundTagAt(x1);
					int id = nbt.getShort("id");
					if (id == Enchantment.fortune.effectId)
					{
						level1 = nbt.getShort("lvl");
					}
				}
			}
			ArrayList<ItemStack> items1 = blockID11.getDrops(world11, x, y, z, meta11, level1);
			if(items1 == null || items1.size() == 0)
			{
				return false;
			}
			for (ItemStack item : items1)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(item) == null)
				{
					return false;
				}
				ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(item).copy();

				world11.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
						blockID11.stepSound.getBreakSound(),
						(blockID11.stepSound.getVolume() + 1.0F) / 2.0F,
						blockID11.stepSound.getPitch() * 0.8F);

				world11.setBlockToAir(x, y, z);
				if(!world11.isRemote)
				{
					float f = world11.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world11.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world11.rand.nextFloat() * 0.8F + 0.1F;

					EntityItem entityitem = new EntityItem(world11, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), smelted);

					world11.spawnEntityInWorld(entityitem);
				}
				stack.damageItem(1, player);
			}

			return true;


		default : return false;

		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(type == 1)
		{
			ToolHandler.creeperMine(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, "pickaxe", level);
			return true;
		}
		return false;

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

		par3List.add("\u00a76" + ToolHandler.getInformation(2, type, ""));
		par3List.add(material);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));

	}

}
