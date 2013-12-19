package com.pauljoda.mobtools.blocks;

import java.util.Random;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.tileentities.MobToolsSpawnerLogic;
import com.pauljoda.mobtools.tileentities.TileEntityMobToolsSpawner;
import com.pauljoda.mobtools.tools.MobToolsItemPowerCore;
import com.pauljoda.mobtools.tools.ToolManager;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMobToolsSpawner extends BlockContainer {

	public BlockMobToolsSpawner(int par1) {
		super(par1, Material.iron);
		setUnlocalizedName("mobToolsSpawner");
		setCreativeTab(MobTools.tabMobTools);
		this.setHardness(3.0F);	
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMobToolsSpawner();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntityMobToolsSpawner spawner = (TileEntityMobToolsSpawner) world.getBlockTileEntity(x, y, z);
		if(player.inventory.getCurrentItem() != null)
		{
			if(player.inventory.getCurrentItem().itemID == ToolManager.powerCore.itemID && !spawner.isActive)
			{
				if(player.inventory.getCurrentItem().stackTagCompound != null)
				{
					if(spawner.inv[0] == null)
					{
						ItemStack powerCore = player.inventory.getCurrentItem();
						spawner.mobName = powerCore.stackTagCompound.getString("mobName");
						spawner.isActive = true;
						spawner.maxCoolDown = MobToolsSpawnerLogic.getMaxCoolDown(powerCore.stackTagCompound.getInteger("tier"));
						spawner.spawnRate = powerCore.stackTagCompound.getInteger("tier") + 1;
						spawner.dimension = MobToolsItemPowerCore.getDimension(powerCore.stackTagCompound.getString("mobName"));
						spawner.tier = powerCore.stackTagCompound.getInteger("tier");
						spawner.inv[0] = powerCore;
						player.setCurrentItemOrArmor(0, null);
						return true;
					}
				}
			}
		}
		if(player.isSneaking())
		{
			player.inventory.addItemStackToInventory(spawner.inv[0]);
			spawner.isActive = false;
			spawner.inv[0] = null;
		}
		return true;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return this.blockID;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("mob_spawner");
	}
}
