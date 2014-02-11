package com.pauljoda.mobtools.tools;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.amulets.MobToolsAmulet;
import com.pauljoda.mobtools.amulets.MobToolsBlazeAmulet;
import com.pauljoda.mobtools.amulets.MobToolsJumpAmulet;
import com.pauljoda.mobtools.amulets.MobToolsSpeedAmulet;
import com.pauljoda.mobtools.amulets.MobToolsTeleportAmulet;
import com.pauljoda.mobtools.blocks.BlockBlazeTorch;
import com.pauljoda.mobtools.blocks.BlockEnderPad;
import com.pauljoda.mobtools.blocks.BlockMobToolsSpawner;
import com.pauljoda.mobtools.blocks.BlockRepairAlter;
import com.pauljoda.mobtools.handlers.GeneralSettings;
import com.pauljoda.mobtools.infusion.BlockInfusingFurnace;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ToolManager {

	//Infusing Furnace
	public static Block infusingFurnace = null;
	public static Block infusingFurnaceActive = null;
	
	//Ingots
	public static Item creepium = null;
	public static Item endium = null;
	public static Item spidium = null;
	public static Item blazium = null;

	//Swords
	public static Item creeperSword = null;
	public static Item endiumSword = null;
	public static Item spidiumSword = null;
	public static Item blaziumSword = null;
	
	//PickAxe
	public static Item creeperPick = null;
	public static Item endiumPick = null;
	public static Item spidiumPick = null;
	public static Item blaziumPick = null;
	
	//Shovels
	public static Item creeperShovel = null;
	public static Item endiumShovel = null;
	public static Item spidiumShovel = null;
	public static Item blaziumShovel = null;
	
	//Axes
	public static Item creeperAxe = null;
	public static Item endiumAxe = null;
	public static Item spidiumAxe = null;
	public static Item blaziumAxe = null;
	
	//Hoes
	public static Item creeperHoe = null;
	public static Item endiumHoe = null;
	public static Item spidiumHoe = null;
	public static Item blaziumHoe = null;
	
	//Wands
	public static Item creeperWand = null;
	public static Item endiumWand = null;
	public static Item spidiumWand = null;
	public static Item blaziumWand = null;
	
	//Items
	public static Item inertWandCore = null;
	public static Item goldenrod = null;
	public static Item creeperWandCore = null;
	public static Item enderWandCore = null;
	public static Item spiderWandCore = null;
	public static Item blazeWandCore = null;
	public static Item infuser = null;
	public static Item infuserComplete = null;
	public static Item powerCore = null;
	public static Item enderMail = null;
	
	//Amulets
	public static Item blankAmulet = null;
	public static Item blazeAmulet = null;
	public static Item speedAmulet = null;
	public static Item jumpAmulet = null;
	public static Item teleportAmulet = null;
	
	//Blocks
	public static Block enderPad = null;
	public static Block mobToolsSpawner = null;
	public static Block repairAlter = null;
	public static Block blazeTorch = null;

	public static void registerTools()
	{
		//Adding----------------------------------------------------------------------------------------

		//Infusing Furnace
		infusingFurnace = new BlockInfusingFurnace(false).setBlockName("infusingFurnace").setCreativeTab(MobTools.tabMobTools);
		infusingFurnaceActive = new BlockInfusingFurnace(true).setBlockName("infusingFurnaceActive").setLightLevel(5.0F);
		
		//Ingots
		creepium = new MobToolsGems("creepium");
		endium = new MobToolsGems("endium");
		spidium = new MobToolsGems("spidium");
		blazium = new MobToolsGems("blazium");

		//Swords
		creeperSword = new MobToolsSword(MobTools.CREEPER_TOOL_MATERIAL, "creeperSword", 0, 1);
		endiumSword = new MobToolsSword( MobTools.BLAZE_TOOL_MATERIAL, "enderSword", 0, 2);
		spidiumSword = new MobToolsSword(MobTools.SPIDER_TOOL_MATERIAL, "spiderSword", 0, 3);
		blaziumSword = new MobToolsSword(MobTools.BLAZE_TOOL_MATERIAL, "blazeSword", 0, 4);
		
		//Picks
		creeperPick = new MobToolsPick(MobTools.CREEPER_TOOL_MATERIAL, "creeperPick", 0, 1);
		endiumPick = new MobToolsPick(MobTools.ENDER_TOOL_MATERIAL, "enderPick", 0, 2);
		spidiumPick = new MobToolsPick(MobTools.SPIDER_TOOL_MATERIAL, "spiderPick", 0, 3);
		blaziumPick = new MobToolsPick(MobTools.BLAZE_TOOL_MATERIAL, "blazePick", 0, 4);
		
		//Shovel
		creeperShovel = new MobToolsShovel(MobTools.CREEPER_TOOL_MATERIAL, "creeperShovel", 0, 1);
		endiumShovel = new MobToolsShovel(MobTools.ENDER_TOOL_MATERIAL, "enderShovel", 0, 2);
		spidiumShovel = new MobToolsShovel(MobTools.SPIDER_TOOL_MATERIAL, "spiderShovel", 0, 3);
		blaziumShovel = new MobToolsShovel(MobTools.BLAZE_TOOL_MATERIAL, "blazeShovel", 0, 4);
		
		//Axes
		creeperAxe = new MobToolsAxe(MobTools.CREEPER_TOOL_MATERIAL, "creeperAxe", 0, 1);
		endiumAxe = new MobToolsAxe(MobTools.ENDER_TOOL_MATERIAL, "enderAxe", 0, 2);
		spidiumAxe = new MobToolsAxe(MobTools.SPIDER_TOOL_MATERIAL, "spiderAxe", 0, 3);
		blaziumAxe = new MobToolsAxe(MobTools.BLAZE_TOOL_MATERIAL, "blazeAxe", 0, 4);
		
		//Hoes
		creeperHoe = new MobToolsHoe(MobTools.CREEPER_TOOL_MATERIAL, "creeperHoe", 0, 1);
		endiumHoe = new MobToolsHoe(MobTools.ENDER_TOOL_MATERIAL, "enderHoe",0, 2);
		spidiumHoe = new MobToolsHoe(MobTools.SPIDER_TOOL_MATERIAL, "spiderHoe", 0, 3);
		blaziumHoe = new MobToolsHoe( MobTools.BLAZE_TOOL_MATERIAL, "blazeHoe", 0, 4);
		
		//Wands
		creeperWand = new MobToolsWand("creeperWand", 1, 100);
		endiumWand = new MobToolsWand("enderWand", 2, 2000);
		spidiumWand = new MobToolsWand("spiderWand", 3, 200);
		blaziumWand = new MobToolsWand("blazeWand", 4, 500);
		
		//Items
		inertWandCore = new MobToolsItem("inertWandCore", false, 64);
		goldenrod = new MobToolsItem("goldenRod", false, 64);
		creeperWandCore = new MobToolsItem("creeperWandCore", true, 64);
		enderWandCore = new MobToolsItem("enderWandCore", true, 64);
		spiderWandCore = new MobToolsItem("spiderWandCore", true, 64);
		blazeWandCore = new MobToolsItem("blazeWandCore", true, 64);
		infuser = new MobToolsItem("infuser", false, 1);
		infuserComplete = new MobToolsItem("infuserComplete", true, 1);
		powerCore = new MobToolsItemPowerCore();
		
		//Amulets
		blankAmulet = new MobToolsAmulet();
		blazeAmulet = new MobToolsBlazeAmulet();
		speedAmulet = new MobToolsSpeedAmulet();
		jumpAmulet = new MobToolsJumpAmulet();
		teleportAmulet = new MobToolsTeleportAmulet();
		
		//Blocks
		enderPad = new BlockEnderPad();
		mobToolsSpawner = new BlockMobToolsSpawner();
		repairAlter = new BlockRepairAlter();
		blazeTorch = new BlockBlazeTorch();
		
		
		//Registering---------------------------------------------------------------------

		//Infusing Furnace
		GameRegistry.registerBlock(infusingFurnace, "infusingFurnace");
		GameRegistry.registerBlock(infusingFurnaceActive, "infusingFurnaceActive");
		
		//Ingots
		GameRegistry.registerItem(creepium, "creepium");
		GameRegistry.registerItem(endium, "endium");
		GameRegistry.registerItem(spidium, "spidium");
		GameRegistry.registerItem(blazium, "blazium");

		//Swords
		GameRegistry.registerItem(creeperSword, "creeperSword");
		GameRegistry.registerItem(endiumSword, "enderSword");
		GameRegistry.registerItem(spidiumSword, "spiderSword");
		GameRegistry.registerItem(blaziumSword, "blazeSword");
		
		//Picks
		GameRegistry.registerItem(creeperPick, "creeperPick");
		GameRegistry.registerItem(endiumPick, "enderPick");
		GameRegistry.registerItem(spidiumPick, "spiderPick");
		GameRegistry.registerItem(blaziumPick, "blazePick");
		
		//Shovels
		GameRegistry.registerItem(creeperShovel, "creeperShovel");
		GameRegistry.registerItem(endiumShovel, "enderShovel");
		GameRegistry.registerItem(spidiumShovel, "spiderShovel");
		GameRegistry.registerItem(blaziumShovel, "blazeShovel");
		
		//Axes
		GameRegistry.registerItem(creeperAxe, "creeperAxe");
		GameRegistry.registerItem(endiumAxe, "enderAxe");
		GameRegistry.registerItem(spidiumAxe, "spiderAxe");
		GameRegistry.registerItem(blaziumAxe, "blazeAxe");
		
		//Hoes
		GameRegistry.registerItem(creeperHoe, "creeperHoe");
		GameRegistry.registerItem(endiumHoe, "enderHoe");
		GameRegistry.registerItem(spidiumHoe, "spiderHoe");
		GameRegistry.registerItem(blaziumHoe, "blazeHoe");
		
		//Wands
		GameRegistry.registerItem(creeperWand, "creeperWand");
		GameRegistry.registerItem(endiumWand, "enderWand");
		GameRegistry.registerItem(spidiumWand, "spiderWand");
		GameRegistry.registerItem(blaziumWand, "blazeWand");
		
		//Items
		GameRegistry.registerItem(inertWandCore, "inertWandCore");
		GameRegistry.registerItem(goldenrod, "goldenRod");
		GameRegistry.registerItem(creeperWandCore, "creeperWandCore");
		GameRegistry.registerItem(enderWandCore, "enderWandCore");
		GameRegistry.registerItem(spiderWandCore, "spiderWandCore");
		GameRegistry.registerItem(blazeWandCore, "blazeWandCore");
		GameRegistry.registerItem(infuser, "infuer");
		GameRegistry.registerItem(infuserComplete, "infuserComplete");
		GameRegistry.registerItem(powerCore, "powerCore");
		GameRegistry.registerItem(enderMail, "enderMail");
		
		//Amulets
		GameRegistry.registerItem(blankAmulet, "blankAmulet");
		GameRegistry.registerItem(blazeAmulet, "blazeAmulet");
		GameRegistry.registerItem(speedAmulet, "speedAmulet");
		GameRegistry.registerItem(jumpAmulet, "jumpAmulet");
		GameRegistry.registerItem(teleportAmulet, "teleportAmulet");
		
		//Blocks
		GameRegistry.registerBlock(enderPad, "enderPad");
		GameRegistry.registerBlock(mobToolsSpawner, "mobToolsSpawner");
		GameRegistry.registerBlock(repairAlter, "repairAlter");
		GameRegistry.registerBlock(blazeTorch, "blazeTorch");

		//Naming-----------------------------------------------------------------------------
/*
		//Infusing Furnace
		LanguageRegistry.addName(infusingFurnace, "Infusing Furnace");
		LanguageRegistry.addName(infusingFurnaceActive, "Infusing Furnace");

		
		//Ingots
		LanguageRegistry.addName(creepium, "Creepium");
		LanguageRegistry.addName(endium, "Endium");
		LanguageRegistry.addName(spidium, "Spidium");
		LanguageRegistry.addName(blazium, "Blazium");

		//Swords
		LanguageRegistry.addName(creeperSword, "Creepium Sword");
		LanguageRegistry.addName(endiumSword, "Endium Sword");
		LanguageRegistry.addName(spidiumSword, "Spidium Sword");
		LanguageRegistry.addName(blaziumSword, "Blazium Sword");
		
		//Picks
		LanguageRegistry.addName(creeperPick, "Creepium Pick");
		LanguageRegistry.addName(endiumPick, "Endium Pick");
		LanguageRegistry.addName(spidiumPick, "Spidium Pick");
		LanguageRegistry.addName(blaziumPick, "Blazium Pick");
		
		//Shovels
		LanguageRegistry.addName(creeperShovel, "Creepium Shovel");
		LanguageRegistry.addName(endiumShovel, "Endium Shovel");
		LanguageRegistry.addName(spidiumShovel, "Spidium Shovel");
		LanguageRegistry.addName(blaziumShovel, "Blazium Shovel");
		
		//Axes
		LanguageRegistry.addName(creeperAxe, "Creepium Axe");
		LanguageRegistry.addName(endiumAxe, "Endium Axe");
		LanguageRegistry.addName(spidiumAxe, "Spidium Axe");
		LanguageRegistry.addName(blaziumAxe, "Blazium Axe");
		
		//Hoes
		LanguageRegistry.addName(creeperHoe, "Creepium Hoe");
		LanguageRegistry.addName(endiumHoe, "Endium Hoe");
		LanguageRegistry.addName(spidiumHoe, "Spidium Hoe");
		LanguageRegistry.addName(blaziumHoe, "Blazium Hoe");
		
		//Wands
		LanguageRegistry.addName(creeperWand, "Creeper Wand");
		LanguageRegistry.addName(endiumWand, "Ender Wand");
		LanguageRegistry.addName(spidiumWand, "Spider Wand");
		LanguageRegistry.addName(blaziumWand, "Blaze Wand");
		
		//Items
		LanguageRegistry.addName(inertWandCore, "Inert Wand Core");
		LanguageRegistry.addName(goldenrod, "Goldenrod");
		LanguageRegistry.addName(creeperWandCore, "Creeper Wand Core");
		LanguageRegistry.addName(enderWandCore, "Ender Wand Core");
		LanguageRegistry.addName(spiderWandCore, "Spider Wand Core");
		LanguageRegistry.addName(blazeWandCore, "Blaze Wand Core");
		LanguageRegistry.addName(infuser, "Infuser Core");
		LanguageRegistry.addName(infuserComplete, "Infuser Core");
		LanguageRegistry.addName(powerCore, "Mob Power Core");
		LanguageRegistry.addName(enderMail, "Ender Mail");
		
		//Amulets
		LanguageRegistry.addName(blankAmulet, "Blank Amulet");
		LanguageRegistry.addName(blazeAmulet, "Amulet of Illumination");
		LanguageRegistry.addName(speedAmulet, "Amulet of Speed");
		LanguageRegistry.addName(jumpAmulet, "Amulet of Lift");
		LanguageRegistry.addName(teleportAmulet, "Amulet of Memory");
		
		//Blocks
		LanguageRegistry.addName(enderPad, "Ender Pad");
		LanguageRegistry.addName(mobToolsSpawner, "Spawner Cage");
		LanguageRegistry.addName(repairAlter, "Repair Alter");
		LanguageRegistry.addName(blazeTorch, "Blaze Torch");*/
	}

	public static void registerCraftingRecipes()
	{
		//Infusing Furnace

		CraftingManager.getInstance().addRecipe(new ItemStack(infusingFurnace, 1),
				"DXD",
				"XxX",
				"DXD", 'X', Blocks.gold_block, 'x', infuserComplete, 'D', Items.diamond);
		
		//Inert Wand Core
		CraftingManager.getInstance().addRecipe(new ItemStack(inertWandCore, 1),
				"SRS",
				"LDL",
				"SRS", 'S', Blocks.stone, 'R', Items.redstone, 'L', new ItemStack(Items.dye, 1, 4), 'D', Items.diamond );
		
		//Goldenrod
		CraftingManager.getInstance().addRecipe(new ItemStack(goldenrod , 1),
				"  G",
				" G ",
				"G  ", 'G', Items.gold_ingot);
		
		//Infuser
		CraftingManager.getInstance().addRecipe(new ItemStack(infuser, 1), 
				"GgE",
				"gDg",
				"SgB", 'G', Items.gunpowder, 'g', Items.gold_ingot, 'E', Items.ender_pearl, 'D', Items.diamond, 'S', Items.spider_eye, 'B', Items.blaze_rod);
		
		//Ender Pad
		CraftingManager.getInstance().addRecipe(new ItemStack(enderPad, 2), 
				"WEW",
				"WWW",
				"WEW", 'W', Blocks.wool, 'E', Items.ender_pearl);
		
		//Power Core 
		CraftingManager.getInstance().addRecipe(new ItemStack(powerCore, 1), 
				"CIE",
				"III",
				"SIB", 'C', creepium, 'I', Items.iron_ingot, 'E', endium, 'S', spidium, 'B', blazium);
		
		//Erase Power Core
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(powerCore, 1), new ItemStack(Items.water_bucket, 1), new ItemStack(powerCore, 1));

		//Ender Mail
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(enderMail, 8), new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.paper, 1));
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
		
		//Amulet
		CraftingManager.getInstance().addRecipe(new ItemStack(blankAmulet, 1),
				"BBB",
				"BGB",
				"BBB", 'B', Items.gold_ingot, 'G', Items.glowstone_dust);
		
	}
}


