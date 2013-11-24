package com.pauljoda.mobtools.infusion;

import java.util.Random;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.tools.ToolManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockInfusingFurnace extends BlockContainer{
	/**
	 * Is the random generator used by furnace to drop the inventory contents in random directions.
	 */
	private Random furnaceRand = new Random();

	/** True if this is an active furnace, false if idle */
	private final boolean isActive;

	/**
	 * This flag is used to prevent the furnace inventory to be dropped upon block removal, is used internally when the
	 * furnace block changes from idle to active and vice-versa.
	 */
	private static boolean keepFurnaceInventory = false;

	public BlockInfusingFurnace(int par1, boolean par2)
	{
		super(par1, Material.rock);
		this.isActive = par2;
	}
	@SideOnly(Side.CLIENT)
	private Icon mtinfuserBlockTop;
	@SideOnly(Side.CLIENT)
	private Icon mtinfuserBlock;

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ToolManager.infusingFurnace.blockID;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}


	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			{
				b0 = 2;
			}

			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}

			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}


	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return par1 == 3 ? this.mtinfuserBlock : (par1 == 1 ? this.mtinfuserBlockTop : (par1 == 0 ? this.mtinfuserBlockTop : (par1 != par2 ? this.blockIcon : this.mtinfuserBlock)));
	}

	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("mobtools:" + "mtinfuserBlockSide");
		this.mtinfuserBlock = par1IconRegister.registerIcon(this.isActive ? "mobtools:" + "mtinfuserBlockActive" : "mobtools:" + "mtinfuserBlock");
		this.mtinfuserBlockTop = par1IconRegister.registerIcon("mobtools:" + "mtinfuserBlockTop");
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (this.isActive)
		{
			super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

			for (int l = par2 - 2; l <= par2 + 2; ++l)
			{
				for (int i1 = par4 - 2; i1 <= par4 + 2; ++i1)
				{
					if (l > par2 - 2 && l < par2 + 2 && i1 == par4 - 1)
					{
						i1 = par4 + 2;
					}

					if (par5Random.nextInt(1) == 0)
					{
						for (int j1 = par3; j1 <= par3 + 2; ++j1)
						{
							if (par1World.getBlockId(l, j1, i1) == Block.bookShelf.blockID || par1World.getBlockId(l, j1, i1) == Block.skull.blockID)
							{
								if (!par1World.isAirBlock((l - par2) / 2 + par2, j1, (i1 - par4) / 2 + par4))
								{
									break;
								}

								par1World.spawnParticle("enchantmenttable", (double)par2 + 0.5D, (double)par3 + 2.0D, (double)par4 + 0.5D, (double)((float)(l - par2) + par5Random.nextFloat()) - 0.5D, (double)((float)(j1 - par3) - par5Random.nextFloat() - 1.0F), (double)((float)(i1 - par4) + par5Random.nextFloat()) - 0.5D);

								par1World.spawnParticle("enchantmenttable", (double)par2 + 0.5D, (double)par3 + 2.0D, (double)par4 + 0.5D, (double)((float)(l - par2) + par5Random.nextFloat()) - 0.5D, (double)((float)(j1 - par3) - par5Random.nextFloat() - 1.0F), (double)((float)(i1 - par4) + par5Random.nextFloat()) - 0.5D);
							}
						}
					}
				}
			}
		}
	}


	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityInfusingFurnace tileentityfurnace = (TileEntityInfusingFurnace)par1World.getBlockTileEntity(par2, par3, par4);

			if (tileentityfurnace != null)
			{
				tileentityfurnace.getInfusingSpeed();
				par5EntityPlayer.openGui(MobTools.instance, 0, par1World, par2, par3, par4);
			}

			return true;
		}
	}

	/**
	 * Update which block ID the furnace is using depending on whether or not it is burning
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}

		if (par6ItemStack.hasDisplayName())
		{
			((TileEntityInfusingFurnace)par1World.getBlockTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
		}
	}

	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
		keepFurnaceInventory = true;

		if (par0)
		{
			par1World.setBlock(par2, par3, par4, ToolManager.infusingFurnaceActive.blockID);
		}
		else
		{
			par1World.setBlock(par2, par3, par4, ToolManager.infusingFurnace.blockID);
		}

		keepFurnaceInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setBlockTileEntity(par2, par3, par4, tileentity);
		}
	}
	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if (!keepFurnaceInventory)
		{
			TileEntityInfusingFurnace var7 = (TileEntityInfusingFurnace)par1World.getBlockTileEntity(par2, par3, par4);

			if (var7 != null)
			{
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
				{
					ItemStack var9 = var7.getStackInSlot(var8);

					if (var9 != null)
					{
						float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
						float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
						float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

						while (var9.stackSize > 0)
						{
							int var13 = this.furnaceRand.nextInt(21) + 10;

							if (var13 > var9.stackSize)
							{
								var13 = var9.stackSize;
							}

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

							if (var9.hasTagCompound())
							{
								var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
							}

							float var15 = 0.05F;
							var14.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
							var14.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
							var14.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {

		return new TileEntityInfusingFurnace();
	}

}