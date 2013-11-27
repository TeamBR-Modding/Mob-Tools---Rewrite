package com.pauljoda.mobtools.tools;

import java.util.ArrayList;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class MobToolsPick extends ItemPickaxe {

	int ingot;
	int type;
	public MobToolsPick(int par1, EnumToolMaterial par2EnumToolMaterial, String unlocalized, int ingot, int type) {
		super(par1, par2EnumToolMaterial);
		this.setUnlocalizedName(unlocalized);
		this.setCreativeTab(MobTools.tabMobTools);
		this.ingot = ingot;
		this.type = type;
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
			World world = player.worldObj;
			final int blockID = world.getBlockId(x, y, z);
			final int meta = world.getBlockMetadata(x, y, z);
			Block block = Block.blocksList[blockID];

			if (block == null)
				return super.onBlockStartBreak(stack, x, y, z, player);

			float blockHardness = block.getBlockHardness(world, x, y, z);

			MovingObjectPosition mop = ToolHandler.raytraceFromEntity(world, player, true, 5.0D);
			if(mop == null)
				break;

			int xRange = 1;
			int yRange = 1;
			int zRange = 1;
			switch (mop.sideHit)
			{
			case 0:
			case 1:
				yRange = 0;
				break;
			case 2:
			case 3:
				zRange = 0;
				break;
			case 4:
			case 5:
				xRange = 0;
				break;
			}

			for (int xPos = x - xRange; xPos <= x + xRange; xPos++)
			{
				for (int yPos = y - yRange; yPos <= y + yRange; yPos++)
				{
					for (int zPos = z - zRange; zPos <= z + zRange; zPos++)
					{
						if(stack != null)
						{
							if(stack.getItemDamage() < stack.getMaxDamage())
							{
								int localblockID = world.getBlockId(xPos, yPos, zPos);
								Block localBlock = Block.blocksList[localblockID];
								int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
								int hlvl = MinecraftForge.getBlockHarvestLevel(localBlock, localMeta, "pickaxe");
								float localHardness = localBlock == null ? Float.MAX_VALUE : localBlock.getBlockHardness(world, xPos, yPos, zPos);

								if (hlvl <= 2 && localHardness - 1.5 <= blockHardness)
								{
									if (localBlock != null && !(localHardness < 0))
									{
										if( getStrVsBlock(stack, Block.blocksList[localblockID], meta) > 1.0F )
										{
											if (!player.capabilities.isCreativeMode)
											{
												world.destroyBlock(xPos, yPos, zPos, true);
											}
											else
											{
												world.setBlockToAir(xPos, yPos, zPos);
											}
										}
									}
								}
							}
							else
								return false;
						}
					}
				}
			}
			stack.damageItem(1, player);
			return true;

	
		case 2 : 
			World world1 = player.worldObj;
			int blockID1 = world1.getBlockId(x, y, z);
			int meta1 = world1.getBlockMetadata(x, y, z);

			NBTTagList ench = stack.getEnchantmentTagList();
			int level = 0;
			if(ench != null)
			{
				for (int x1 = 0; x1 < ench.tagCount(); x1++)
				{
					NBTTagCompound nbt = (NBTTagCompound) ench.tagAt(x1);
					int id = nbt.getShort("id");
					if (id == Enchantment.fortune.effectId)
					{
						level = nbt.getShort("lvl");
					}
				}
			}
			ArrayList<ItemStack> items = Block.blocksList[blockID1].getBlockDropped(world1, x, y, z, meta1, level);
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
						Block.blocksList[blockID1].stepSound.getBreakSound(),
						(Block.blocksList[blockID1].stepSound.getVolume() + 1.0F) / 2.0F,
						Block.blocksList[blockID1].stepSound.getPitch() * 0.8F);

				world1.setBlock(x, y, z, 0);
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

			World world3 = player.worldObj;
			int blockID3 = world3.getBlockId(x, y, z);
			int meta3 = world3.getBlockMetadata(x, y, z);

			ItemStack items2 = new ItemStack(Block.blocksList[world3.getBlockId(x, y, z)], 1, meta3);

			world3.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
					Block.blocksList[blockID3].stepSound.getBreakSound(),
					(Block.blocksList[blockID3].stepSound.getVolume() + 1.0F) / 2.0F,
					Block.blocksList[blockID3].stepSound.getPitch() * 0.8F);

			world3.setBlock(x, y, z, 0);
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
			int blockID11 = world11.getBlockId(x, y, z);
			int meta11 = world11.getBlockMetadata(x, y, z);

			NBTTagList ench1 = stack.getEnchantmentTagList();
			int level1 = 0;
			if(ench1 != null)
			{
				for (int x1 = 0; x1 < ench1.tagCount(); x1++)
				{
					NBTTagCompound nbt = (NBTTagCompound) ench1.tagAt(x1);
					int id = nbt.getShort("id");
					if (id == Enchantment.fortune.effectId)
					{
						level1 = nbt.getShort("lvl");
					}
				}
			}
			ArrayList<ItemStack> items1 = Block.blocksList[blockID11].getBlockDropped(world11, x, y, z, meta11, level1);
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
						Block.blocksList[blockID11].stepSound.getBreakSound(),
						(Block.blocksList[blockID11].stepSound.getVolume() + 1.0F) / 2.0F,
						Block.blocksList[blockID11].stepSound.getPitch() * 0.8F);

				world11.setBlock(x, y, z, 0);
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

		return true;
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));

	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return ingot == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

}
