package com.pauljoda.mobtools.handlers;

import java.util.Random;

import com.pauljoda.mobtools.tools.ToolManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
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


	public static void wandEffect(ItemStack itemstack, World world, EntityPlayer entityplayer, int type)
	{
		//Wand Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		switch(type)
		{

		case 1:
			if(!world.isRemote)
				world.createExplosion(entityplayer, entityplayer.posX, entityplayer.posY, entityplayer.posZ, 2.0F, true);

			if(!entityplayer.capabilities.isCreativeMode)
				itemstack.damageItem(1, entityplayer);

			break;

		case 2:  
			break;


		case 3:
			//Always active, does not need effect on click
			break;

		case 4: 
			if (!world.isRemote)
			{
				Vec3 look = entityplayer.getLookVec();
				EntitySmallFireball fireball2 = new EntitySmallFireball(world, entityplayer, 1, 1, 1);
				fireball2.setPosition(
						entityplayer.posX + look.xCoord,
						entityplayer.posY + look.yCoord + entityplayer.getEyeHeight(),
						entityplayer.posZ + look.zCoord);
				fireball2.accelerationX = look.xCoord * 0.1;
				fireball2.accelerationY = look.yCoord * 0.1;
				fireball2.accelerationZ = look.zCoord * 0.1;
				world.spawnEntityInWorld(fireball2);

			}
			itemstack.damageItem(1, entityplayer);
			break;

		default: break;

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
			par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 2.0F, false);
		par1ItemStack.damageItem(5, par3EntityPlayer);
		break;

		case (2) : 
			if(par3EntityPlayer.isSneaking())
			{
				if (par3EntityPlayer.capabilities.isCreativeMode)
				{
					par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

					if (!par2World.isRemote)
					{
						par2World.spawnEntityInWorld(new EntityEnderPearl(par2World, par3EntityPlayer));
					}

					par1ItemStack.damageItem(10, par3EntityPlayer);
				}

				else if (par3EntityPlayer.ridingEntity != null)
				{
					break;
				}

				else
				{

					par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

					if (!par2World.isRemote)
					{
						par2World.spawnEntityInWorld(new EntityEnderPearl(par2World, par3EntityPlayer));
					}

					par1ItemStack.damageItem(10, par3EntityPlayer);
				}
			}
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

	public static String getInformation(int i, int type) 
	{
		//Information Types
		//Sword: 1
		//Pick: 2
		//Shovel: 3
		//Hoe: 4
		//Axe: 5
		//Wand: 6
		//Items: 7

		//Types:
		//1: Creeper
		//2: Ender
		//3: Spider
		//4: Blaze

		//Items
		int creepium = ToolManager.creepium.itemID;
		int endium = ToolManager.endium.itemID;
		int spidium = ToolManager.spidium.itemID;
		int blazium = ToolManager.blazium.itemID;
		int inertCore = ToolManager.inertWandCore.itemID;
		int goldenRod = ToolManager.goldenrod.itemID;
		int creeperCore = ToolManager.creeperWandCore.itemID;
		int enderCore = ToolManager.enderWandCore.itemID;
		int spiderCore = ToolManager.spiderWandCore.itemID;
		int blazeCore = ToolManager.blazeWandCore.itemID;
		int infuser = ToolManager.infuser.itemID;
		int infuserComplete = ToolManager.infuserComplete.itemID;

		switch(i)
		{
		case 1: 
			switch(type)
			{
			case 1: return "Right click For an Explosion";
			case 2: return "Shift Right Click For Teleport";
			case 3: return "Shift Right Click to Become Invisible";
			case 4: return "Right Click to Extinguish Yourself";
			}
		case 2: 
			switch(type)
			{
			case 1: return "On Right Click Mines a 3x3 area, Uses Gunpowder";
			case 2: return "Teleports Items to Your Inventory";
			case 3: return "Silk Touch";
			case 4: return "Auto-Smelting";
			}
		case 3: 
			switch(type)
			{
			case 1: return "On Right Click Digs a 3x3 area, Uses Gunpowder";
			case 2: return "Teleports Items to Your Inventory";
			case 3: return "It's a Shovel";
			case 4: return "Kinda Cool Looking";
			}
		case 4: 
			switch(type)
			{
			case 1: return "Tills a 3x3 Area";
			case 2: return "Scary Looking";
			case 3: return "Right Click Crops To Grow Them";
			case 4: return "Can Till Dirt";
			}
		case 5: 
			switch(type)
			{
			case 1: return "Chops a 3x3x3 Area";
			case 2: return "Teleports Items to Your Inventory";
			case 3: return "Kinda Like Shears";
			case 4: return "Burns Logs";
			}
		case 6: 
			switch(type)
			{
			case 1: return "Right Click to Unleash Destruction";
			case 2: return "Trade Blocks From Your Inventory";
			case 3: return "Hold This to Prevent Fall Damage";
			case 4: return "Shoot Fireballs";
			}
		case 7: 

			if(type == creepium)
				return "Used to Repair Creepium Tools";
			if(type == endium)
				return "Used to Repair Endium Tools";
			if(type == spidium)
				return "Used to Repair Spidium Tools";
			if(type == blazium)
				return "Used to Repair Blazium Tools";
			if(type == inertCore)
				return "Infuse this to get wand cores";
			if(type == goldenRod)
				return "Used to Make Wands";
			if(type == creeperCore)
				return "Infuse with Goldenrods";
			if(type == enderCore)
				return "Infuse with Goldenrods";
			if(type == spiderCore)
				return "Infuse with Goldenrods";
			if(type == blazeCore)
				return "Infuse with Goldenrods";
			if(type == infuser)
				return "Kill Mobs to Charge";
			if(type == infuserComplete)
				return "Completed! Use This to Craft an Infuser";


		}

		return "It's a thingy!";
	}

	public static boolean  creeperMine(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, String tool)
	{

		int block = world.getBlockId(x, y, z);
		int localMeta = world.getBlockMetadata(x, y, z);
		if(itemStack.getStrVsBlock(Block.blocksList[block]) > 1.0F)
		{
			if(MinecraftForge.getBlockHarvestLevel(Block.blocksList[block], localMeta, tool) <= 3 && Block.blocksList[block].getBlockHardness(world, x, y, z) != -1)
			{

				if(player.inventory.hasItem(Item.gunpowder.itemID) || player.capabilities.isCreativeMode)
				{
					if(!player.capabilities.isCreativeMode)
					{
						player.inventory.consumeInventoryItem(Item.gunpowder.itemID);
						itemStack.damageItem(5, player);
					}

					int p = 2; 
					for(int time = 0; time < p; time++)
					{
						int size = time;
						for(int i = -size; i <= size; i++)
						{
							for(int j = -size; j <= size; j++)
							{
								int xPos = x;
								int yPos = y;
								int zPos = z;

								switch(side)
								{
								case 0 :
								case 1 :
									xPos = xPos + i;
									zPos = zPos + j;
									break;
								case 2 :
								case 3 : 
									xPos = xPos + i;
									yPos = yPos + j;
									break;
								case 4 :
								case 5 : 
									yPos = yPos + j;
									zPos = zPos + i;
									break;
								}

								int localBlock = world.getBlockId(xPos, yPos, zPos);
								int metaLocal = world.getBlockMetadata(xPos, yPos, zPos);

								if(localBlock != 0)
								{
									if(MinecraftForge.getBlockHarvestLevel(Block.blocksList[localBlock], metaLocal, tool) <= 3)
									{
										world.destroyBlock(xPos, yPos, zPos, true);
									}
								}
							}
						}
					}
				}

				return true;
			}

		}

		return false;
	}

	public static boolean  exhangeBlocks(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, int blockid, int meta)
	{
		if(blockid == 0)
			return false;

		if(Block.blocksList[blockid].hasTileEntity(blockid))
			return false;

		int block = world.getBlockId(x, y, z);

		int localMeta = world.getBlockMetadata(x, y, z);

		boolean hasMeta = (meta == localMeta);
		if(block == blockid && hasMeta)
			return false;


		if(MinecraftForge.getBlockHarvestLevel(Block.blocksList[block], localMeta, "pickaxe") <= 1 && Block.isNormalCube(block) && Block.blocksList[block].getBlockHardness(world, x, y, z) != -1)
		{
			if(itemStack.stackTagCompound != null)
			{
				int p = itemStack.stackTagCompound.getInteger("layers"); 
				for(int time = 0; time < p; time++)
				{
					int size = time;

					world.playSoundAtEntity(player, "mob.endermen.portal", 1.0F, 1.0F);
					for(int i = -size; i <= size; i++)
					{
						for(int j = -size; j <= size; j++)
						{
							int xPos = x;
							int yPos = y;
							int zPos = z;

							switch(side)
							{
							case 0 :
							case 1 :
								xPos = xPos + i;
								zPos = zPos + j;
								break;
							case 2 :
							case 3 : 
								xPos = xPos + i;
								yPos = yPos + j;
								break;
							case 4 :
							case 5 : 
								yPos = yPos + j;
								zPos = zPos + i;
								break;
							}

							if(hasItem(player, blockid, 1, meta) || player.capabilities.isCreativeMode)
							{
								int localBlock = world.getBlockId(xPos, yPos, zPos);
								int metaLocal = world.getBlockMetadata(xPos, yPos, zPos);
								boolean metaBoolean = (meta != metaLocal);
								boolean metaCheck = metaBoolean ? (block == localBlock) : (block == localBlock && blockid != localBlock);
								boolean areaCheck = localBlock == block ? ((metaLocal == localMeta || metaLocal == 0) ? true : false) : false;

								if(localBlock != 0)
								{
									if(MinecraftForge.getBlockHarvestLevel(Block.blocksList[localBlock], metaLocal, "pickaxe") <= 1 && !Block.blocksList[localBlock].hasTileEntity(metaLocal)
											&& metaCheck && areaCheck)
									{
										world.setBlock(xPos, yPos, zPos, blockid);
										world.setBlockMetadataWithNotify(xPos, yPos, zPos, meta, 2);
										world.markBlockForUpdate(xPos, yPos, zPos);
										if(!player.capabilities.isCreativeMode)
										{
											removeItem(player, blockid, 1, meta);
											player.inventory.addItemStackToInventory(new ItemStack(Block.blocksList[localBlock], 1, localMeta));
										}
									}


								}
							}

						}
					}
				}
			}
			return true;


		}
		return false;
	}

	public static void removeItem(EntityPlayer ep, int removeitem, int remove, int metadata) 
	{
		IInventory inv = ep.inventory;
		for(int i=0; i < inv.getSizeInventory(); i++)
		{
			if(inv.getStackInSlot(i) != null)
			{
				ItemStack j = inv.getStackInSlot(i);
				if(j.getItem() != null && j.getItem().itemID == removeitem && j.getItemDamage() == metadata)
				{
					inv.decrStackSize(i, remove);
					return;
				}
				if(ep.inventory.hasItem(removeitem) && metadata == 0)
				{
					ep.inventory.consumeInventoryItem(removeitem);
					return;
				}
			}

		}
	}

	public static boolean hasItem(EntityPlayer ep, int removeitem, int remove, int metadata) 
	{
		IInventory inv = ep.inventory;
		for(int i=0; i < inv.getSizeInventory(); i++)
		{
			if(inv.getStackInSlot(i) != null)
			{
				ItemStack j = inv.getStackInSlot(i);
				if(j.getItem() != null && j.getItem().itemID == removeitem && j.getItemDamage() == metadata)
				{
					return true;
				}
				if(ep.inventory.hasItem(removeitem) && metadata == 0)
					return true;
			}

		}
		return false;
	}

}



