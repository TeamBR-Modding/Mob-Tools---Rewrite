package com.pauljoda.mobtools.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ToolHandler {
	
	static Random itemRand = new Random();

	public static void randomTeleport(EntityPlayer player, World world)
	{
		Random r = new Random();
		int x, y, z;
		boolean canSpawn = false;
		while(!canSpawn)
		{
			int mod;

			mod = r.nextInt(2);
			if(mod == 0)
				x = (int)player.posX + r.nextInt(15);
			else
				x = (int)player.posX - r.nextInt(15);

			mod = r.nextInt(2);
			if(mod == 0)
				z = (int)player.posZ + r.nextInt(15);
			else
				z = (int)player.posZ - r.nextInt(15);

			y = (int)player.posY;

			if(world.isAirBlock(x, y, z) || world.isAirBlock(x, y - 1, z))
			{
				player.setPositionAndUpdate(x, y, z);
				player.playSound("mob.endermen.portal", 2.0F, 1.0F);
				canSpawn = true;
			}

		}

	}

	public static void swordEffect(int swordType, ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		//Sword Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		switch(swordType)
		{
		case (1) : break;
		case (2) : break;
		case (3) : break;
		case (4) : 	par2EntityLivingBase.setFire(10);
					par1ItemStack.damageItem(1, par3EntityLivingBase);
					break;

		default : break;


		}
	}

	public static void swordBlockEffect(int swordType, ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		//Sword Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		switch(swordType)
		{
		case (1) :
			par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 5.0F, false);
		par1ItemStack.damageItem(5, par3EntityPlayer);
		break;

		case (2) : 
			randomTeleport(par3EntityPlayer, par2World);
		par1ItemStack.damageItem(5, par3EntityPlayer);
		break;

		case (3) : 
			if(par3EntityPlayer.isSneaking())
			{
				if(par3EntityPlayer.isInvisible())
				{
					par3EntityPlayer.setInvisible(false);
					par3EntityPlayer.playSound("random.pop", 2.0F, 1.0F);
				}
				else
				{
					par3EntityPlayer.setInvisible(true);
					par3EntityPlayer.playSound("random.pop", 2.0F, 1.0F);
				}
			}
		par1ItemStack.damageItem(1, par3EntityPlayer);
		break;

		case (4) : 
			if(par3EntityPlayer.isSneaking())
			{
				par3EntityPlayer.extinguish();
				par3EntityPlayer.playSound("random.extinguish", 2.0F, 1.0F);
				par1ItemStack.damageItem(1, par3EntityPlayer);
			}
		break;

		default : break;

		}
	}



	public static MovingObjectPosition raytraceFromEntity (World world, Entity player, boolean par3, double range)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + 1.62D - (double) player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
		Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
		}
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return world.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
	}


	public static boolean applyBonemeal(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4, EntityPlayer player)
	{
		int l = par1World.getBlockId(par2, par3, par4);

		BonemealEvent event = new BonemealEvent(player, par1World, l, par2, par3, par4);
		if (MinecraftForge.EVENT_BUS.post(event))
		{
			return false;
		}

		if (event.getResult() == Result.ALLOW)
		{
			if (!par1World.isRemote)
			{
				par0ItemStack.damageItem(1, player);
			}
			return true;
		}

		if (l == Block.sapling.blockID)
		{
			if (!par1World.isRemote)
			{
				if ((double)par1World.rand.nextFloat() < 0.45D)
				{
					((BlockSapling)Block.sapling).markOrGrowMarked(par1World, par2, par3, par4, par1World.rand);
				}

				par0ItemStack.damageItem(1, player);
			}

			return true;
		}
		else if (l != Block.mushroomBrown.blockID && l != Block.mushroomRed.blockID)
		{
			if (l != Block.melonStem.blockID && l != Block.pumpkinStem.blockID)
			{
				if (l > 0 && Block.blocksList[l] instanceof BlockCrops)
				{
					if (par1World.getBlockMetadata(par2, par3, par4) == 7)
					{
						return false;
					}
					else
					{
						if (!par1World.isRemote)
						{
							((BlockCrops)Block.blocksList[l]).fertilize(par1World, par2, par3, par4);
							par0ItemStack.damageItem(1, player);
						}

						return true;
					}
				}
				else
				{
					int i1;
					int j1;
					int k1;

					if (l == Block.cocoaPlant.blockID)
					{
						i1 = par1World.getBlockMetadata(par2, par3, par4);
						j1 = BlockDirectional.getDirection(i1);
						k1 = BlockCocoa.func_72219_c(i1);

						if (k1 >= 2)
						{
							return false;
						}
						else
						{
							if (!par1World.isRemote)
							{
								++k1;
								par1World.setBlockMetadataWithNotify(par2, par3, par4, k1 << 2 | j1, 2);
								par0ItemStack.damageItem(1, player);
							}

							return true;
						}
					}
					else if (l != Block.grass.blockID)
					{
						return false;
					}
					else
					{
						if (!par1World.isRemote)
						{
							par0ItemStack.damageItem(1, player);
							label102:

								for (i1 = 0; i1 < 128; ++i1)
								{
									j1 = par2;
									k1 = par3 + 1;
									int l1 = par4;

									for (int i2 = 0; i2 < i1 / 16; ++i2)
									{
										j1 += itemRand.nextInt(3) - 1;
										k1 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
										l1 += itemRand.nextInt(3) - 1;

										if (par1World.getBlockId(j1, k1 - 1, l1) != Block.grass.blockID || par1World.isBlockNormalCube(j1, k1, l1))
										{
											continue label102;
										}
									}

									if (par1World.getBlockId(j1, k1, l1) == 0)
									{
										if (itemRand.nextInt(10) != 0)
										{
											if (Block.tallGrass.canBlockStay(par1World, j1, k1, l1))
											{
												par1World.setBlock(j1, k1, l1, Block.tallGrass.blockID, 1, 3);
											}
										}
										else
										{
											ForgeHooks.plantGrass(par1World, j1, k1, l1);
										}
									}
								}
						}

						return true;
					}
				}
			}
			else if (par1World.getBlockMetadata(par2, par3, par4) == 7)
			{
				return false;
			}
			else
			{
				if (!par1World.isRemote)
				{
					((BlockStem)Block.blocksList[l]).fertilizeStem(par1World, par2, par3, par4);
					par0ItemStack.damageItem(1, player);
				}

				return true;
			}
		}
		else
		{
			if (!par1World.isRemote)
			{
				if ((double)par1World.rand.nextFloat() < 0.4D)
				{
					((BlockMushroom)Block.blocksList[l]).fertilizeMushroom(par1World, par2, par3, par4, par1World.rand);
				}

				par0ItemStack.damageItem(1, player);
			}

			return true;
		}
	}

}
