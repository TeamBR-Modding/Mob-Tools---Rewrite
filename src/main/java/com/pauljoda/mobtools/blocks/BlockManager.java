package com.pauljoda.mobtools.blocks;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.item.ItemManager;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class BlockManager {
	
	//Infusing Furnace
	public static Block infusingFurnace = null;
	public static Block infusingFurnaceActive = null;
	
	
	//Blocks
	public static Block enderPad = null;
	public static Block mobToolsSpawner = null;
	public static Block repairAlter = null;
	public static Block blazeTorch = null;
	public static Block enderPackage = null;
	
	public static void registerBlocks()
	{

		//Infusing Furnace
		infusingFurnace = new BlockInfusingFurnace(false).setBlockName("infusingFurnace").setCreativeTab(MobTools.tabMobTools);
		infusingFurnaceActive = new BlockInfusingFurnace(true).setBlockName("infusingFurnaceActive").setLightLevel(5.0F);

		
		//Blocks
		enderPad = new BlockEnderPad();
		mobToolsSpawner = new BlockMobToolsSpawner();
		repairAlter = new BlockRepairAlter();
		blazeTorch = new BlockBlazeTorch();
		enderPackage = new BlockEnderPackage();
		

		//Infusing Furnace
		GameRegistry.registerBlock(infusingFurnace, "infusingFurnace");
		GameRegistry.registerBlock(infusingFurnaceActive, "infusingFurnaceActive");

		
		//Blocks
		GameRegistry.registerBlock(enderPad, "enderPad");
		GameRegistry.registerBlock(mobToolsSpawner, "mobToolsSpawner");
		GameRegistry.registerBlock(repairAlter, "repairAlter");
		GameRegistry.registerBlock(blazeTorch, "blazeTorch");
		GameRegistry.registerBlock(enderPackage, "enderPackage");
	}
	
	public static void registerBlockCrafting()
	{
		//Infusing Furnace

		CraftingManager.getInstance().addRecipe(new ItemStack(infusingFurnace, 1),
				"OIO",
				" N ",
				" N ", 'O', Blocks.obsidian, 'I', ItemManager.infuserComplete, 'N', Blocks.nether_brick);

		//Ender Pad
		CraftingManager.getInstance().addRecipe(new ItemStack(enderPad, 2), 
				"WEW",
				"WWW",
				"WEW", 'W', Blocks.wool, 'E', Items.ender_pearl);
//Spawner Cage
		CraftingManager.getInstance().addRecipe(new ItemStack(mobToolsSpawner, 1),
				"BBB",
				"BGB",
				"BBB", 'B', Blocks.iron_bars, 'G', Items.fire_charge);
		
		//Repair Alter
		CraftingManager.getInstance().addRecipe(new ItemStack(repairAlter, 1), 
				"GgE",
				"gDg",
				"SgB", 'G', Items.gunpowder, 'g', Items.gold_ingot, 'E', Items.ender_pearl, 'D', Blocks.anvil, 'S', Items.spider_eye, 'B', Items.blaze_rod);

		//EnderPackage
		CraftingManager.getInstance().addRecipe(new ItemStack(enderPackage, 1),
				"OMO",
				"OGO",
				"OEO", 'O', Blocks.obsidian, 'G', Items.gold_ingot, 'M', ItemManager.enderMail, 'E', Items.ender_eye);
		
	}

}
