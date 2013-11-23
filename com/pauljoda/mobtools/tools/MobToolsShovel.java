package com.pauljoda.mobtools.tools;

import java.util.ArrayList;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.handlers.ToolHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class MobToolsShovel extends ItemSpade {
	
	int ingot;
	int type;

	public MobToolsShovel(int par1, EnumToolMaterial par2EnumToolMaterial, String unlocalized, int ingot, int type) {
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
			final Block block = Block.blocksList[blockID];

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
						int localblockID = world.getBlockId(xPos, yPos, zPos);
						Block localBlock = Block.blocksList[localblockID];
						int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
						int hlvl = MinecraftForge.getBlockHarvestLevel(localBlock, meta, "shovel");
						float localHardness = localBlock == null ? Float.MAX_VALUE : localBlock.getBlockHardness(world, xPos, yPos, zPos);

						if (hlvl <= 2 && localHardness - 1.5 <= blockHardness)
						{
							
							boolean cancelHarvest = false;

							if (!cancelHarvest)
							{
								if (localBlock != null && !(localHardness < 0))
								{
								
									if (!player.capabilities.isCreativeMode)
									{	
										
										
										if (localBlock.removeBlockByPlayer(world, player, xPos, yPos, zPos))
										{

											if(stack.getItemDamage() == stack.getMaxDamage())
												return false;
											
											localBlock.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, localMeta);
										}

										localBlock.harvestBlock(world, player, xPos, yPos, zPos, localMeta);
										localBlock.onBlockHarvested(world, xPos, yPos, zPos, localMeta, player);

										if (blockHardness > 0f)
										{		
											if(stack.getItemDamage() == stack.getMaxDamage())
												return false;

											world.destroyBlock(xPos, yPos, zPos, true); 
											stack.damageItem(1, player);

										}
										//onBlockDestroyed(stack, world, localblockID, xPos, yPos, zPos, player);
										else
										{
											world.setBlockToAir(xPos, yPos, zPos);
										}
									}
								}
							}
						}
					}
				}
				if (!world.isRemote)
					world.playAuxSFX(2001, x, y, z, blockID + (meta << 12));
			}

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
			return false;

		case 4 :
			return false;


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
