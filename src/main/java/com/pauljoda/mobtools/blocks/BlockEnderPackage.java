package com.pauljoda.mobtools.blocks;

import java.util.Random;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPackage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEnderPackage extends BlockContainer {

	private final Random field_149955_b = new Random();
	private static final String __OBFID = "CL_00000238";
	public boolean dropSelf = false;

	public BlockEnderPackage()
	{
		super(Material.rock);
		this.setBlockName("enderPackage");
		this.setCreativeTab(MobTools.tabMobTools);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		this.setHardness(3F);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return 22;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		if(dropSelf)
			return Item.getItemFromBlock(this);
		else
			return null;
	}

	public void setAsClone()
	{
		this.dropSelf = false;
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		byte b0 = 0;
		int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			b0 = 2;
		}

		if (l == 1)
		{
			b0 = 5;
		}

		if (l == 2)
		{
			b0 = 3;
		}

		if (l == 3)
		{
			b0 = 4;
		}

		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (world.isRemote)
		{
			return true;
		}
		
		else if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN))
	      return true;
	      
		else
		{
			TileEntityEnderPackage eP = (TileEntityEnderPackage)world.getTileEntity(x, y,  z);
			eP.openInventory();
			p_149727_5_.openGui(MobTools.instance, 0, world, x, y, z);
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
	{
		for (int l = 0; l < 3; ++l)
		{
			double d6 = (double)((float)p_149734_2_ + p_149734_5_.nextFloat());
			double d1 = (double)((float)p_149734_3_ + p_149734_5_.nextFloat());
			d6 = (double)((float)p_149734_4_ + p_149734_5_.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = p_149734_5_.nextInt(2) * 2 - 1;
			int j1 = p_149734_5_.nextInt(2) * 2 - 1;
			d3 = ((double)p_149734_5_.nextFloat() - 0.5D) * 0.125D;
			d4 = ((double)p_149734_5_.nextFloat() - 0.5D) * 0.125D;
			d5 = ((double)p_149734_5_.nextFloat() - 0.5D) * 0.125D;
			double d2 = (double)p_149734_4_ + 0.5D + 0.25D * (double)j1;
			d5 = (double)(p_149734_5_.nextFloat() * 1.0F * (float)j1);
			double d0 = (double)p_149734_2_ + 0.5D + 0.25D * (double)i1;
			d3 = (double)(p_149734_5_.nextFloat() * 1.0F * (float)i1);
			p_149734_1_.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}

	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	{
		TileEntityEnderPackage tileentitychest = (TileEntityEnderPackage)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

		if (tileentitychest != null)
		{
			if(tileentitychest.isOriginal)
				this.dropSelf = true;
			else
				dropSelf = false;

			if(tileentitychest.shouldDropContents)
			{
				for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1)
				{
					ItemStack itemstack = tileentitychest.getStackInSlot(i1);

					if (itemstack != null)
					{
						float f = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
						float f1 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
						EntityItem entityitem;

						for (float f2 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_.spawnEntityInWorld(entityitem))
						{
							int j1 = this.field_149955_b.nextInt(21) + 10;

							if (j1 > itemstack.stackSize)
							{
								j1 = itemstack.stackSize;
							}

							itemstack.stackSize -= j1;
							entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
							float f3 = 0.05F;
							entityitem.motionX = (double)((float)this.field_149955_b.nextGaussian() * f3);
							entityitem.motionY = (double)((float)this.field_149955_b.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double)((float)this.field_149955_b.nextGaussian() * f3);

							if (itemstack.hasTagCompound())
							{
								entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}
						}
					}
				}
			}
			p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
		}

		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon("obsidian");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnderPackage();
	}

}
