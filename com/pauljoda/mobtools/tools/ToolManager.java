package com.pauljoda.mobtools.tools;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.blocks.BlockEnderPad;
import com.pauljoda.mobtools.handlers.GeneralSettings;
import com.pauljoda.mobtools.infusion.BlockInfusingFurnace;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
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
	
	//Blocks
	public static Block enderPad = null;

	public static void registerTools()
	{
		//Adding----------------------------------------------------------------------------------------

		//Infusing Furnace
		infusingFurnace = new BlockInfusingFurnace(GeneralSettings.infusingFurnace, false).setUnlocalizedName("infusingFurnace").setCreativeTab(MobTools.tabMobTools);
		infusingFurnaceActive = new BlockInfusingFurnace(GeneralSettings.infusingFurnaceActive, true).setUnlocalizedName("infusingFurnaceActive").setLightValue(5.0F);
		
		//Ingots
		creepium = new MobToolsIngots(GeneralSettings.creepium, "creepium");
		endium = new MobToolsIngots(GeneralSettings.endium, "endium");
		spidium = new MobToolsIngots(GeneralSettings.spidium, "spidium");
		blazium = new MobToolsIngots(GeneralSettings.blazium, "blazium");

		//Swords
		creeperSword = new MobToolsSword(GeneralSettings.creeperSwordID, MobTools.CREEPER_TOOL_MATERIAL, "creeperSword", creepium.itemID, 1);
		endiumSword = new MobToolsSword(GeneralSettings.endiumSwordID, MobTools.BLAZE_TOOL_MATERIAL, "enderSword", endium.itemID, 2);
		spidiumSword = new MobToolsSword(GeneralSettings.spidiumSwordID, MobTools.SPIDER_TOOL_MATERIAL, "spiderSword", spidium.itemID, 3);
		blaziumSword = new MobToolsSword(GeneralSettings.blaziumSwordID, MobTools.BLAZE_TOOL_MATERIAL, "blazeSword", blazium.itemID, 4);
		
		//Picks
		creeperPick = new MobToolsPick(GeneralSettings.creeperPickID, MobTools.CREEPER_TOOL_MATERIAL, "creeperPick", creepium.itemID, 1);
		endiumPick = new MobToolsPick(GeneralSettings.endiumPickID, MobTools.ENDER_TOOL_MATERIAL, "enderPick", endium.itemID, 2);
		spidiumPick = new MobToolsPick(GeneralSettings.spidiumPickID, MobTools.SPIDER_TOOL_MATERIAL, "spiderPick", spidium.itemID, 3);
		blaziumPick = new MobToolsPick(GeneralSettings.blaziumPickID, MobTools.BLAZE_TOOL_MATERIAL, "blazePick", blazium.itemID, 4);
		
		//Shovel
		creeperShovel = new MobToolsShovel(GeneralSettings.creeperShovelID, MobTools.CREEPER_TOOL_MATERIAL, "creeperShovel", creepium.itemID, 1);
		endiumShovel = new MobToolsShovel(GeneralSettings.endiumShovelID, MobTools.ENDER_TOOL_MATERIAL, "enderShovel", endium.itemID, 2);
		spidiumShovel = new MobToolsShovel(GeneralSettings.spidiumShovelID, MobTools.SPIDER_TOOL_MATERIAL, "spiderShovel", spidium.itemID, 3);
		blaziumShovel = new MobToolsShovel(GeneralSettings.blaziumShovelID, MobTools.BLAZE_TOOL_MATERIAL, "blazeShovel", blazium.itemID, 4);
		
		//Axes
		creeperAxe = new MobToolsAxe(GeneralSettings.creeperAxeID, MobTools.CREEPER_TOOL_MATERIAL, "creeperAxe", creepium.itemID, 1);
		endiumAxe = new MobToolsAxe(GeneralSettings.endiumAxeID, MobTools.ENDER_TOOL_MATERIAL, "enderAxe", endium.itemID, 2);
		spidiumAxe = new MobToolsAxe(GeneralSettings.spidiumAxeID, MobTools.SPIDER_TOOL_MATERIAL, "spiderAxe", spidium.itemID, 3);
		blaziumAxe = new MobToolsAxe(GeneralSettings.blaziumAxeID, MobTools.BLAZE_TOOL_MATERIAL, "blazeAxe", blazium.itemID, 4);
		
		//Hoes
		creeperHoe = new MobToolsHoe(GeneralSettings.creeperHoeID, MobTools.CREEPER_TOOL_MATERIAL, "creeperHoe", creepium.itemID, 1);
		endiumHoe = new MobToolsHoe(GeneralSettings.endiumHoeID, MobTools.ENDER_TOOL_MATERIAL, "enderHoe", endium.itemID, 2);
		spidiumHoe = new MobToolsHoe(GeneralSettings.spidiumHoeID, MobTools.SPIDER_TOOL_MATERIAL, "spiderHoe", spidium.itemID, 3);
		blaziumHoe = new MobToolsHoe(GeneralSettings.blaziumHoeID, MobTools.BLAZE_TOOL_MATERIAL, "blazeHoe", blazium.itemID, 4);
		
		//Wands
		creeperWand = new MobToolsWand(GeneralSettings.creeperWandID, "creeperWand", 1, 100);
		endiumWand = new MobToolsWand(GeneralSettings.endiumWandID, "enderWand", 2, 2000);
		spidiumWand = new MobToolsWand(GeneralSettings.spidiumWandID, "spiderWand", 3, 200);
		blaziumWand = new MobToolsWand(GeneralSettings.blaziumWandID, "blazeWand", 4, 500);
		
		//Items
		inertWandCore = new MobToolsItem(GeneralSettings.inertWandCoreID, "inertWandCore", false, 64);
		goldenrod = new MobToolsItem(GeneralSettings.goldenrodID, "goldenrod", false, 64);
		creeperWandCore = new MobToolsItem(GeneralSettings.creeperWandCoreID, "creeperWandCore", true, 64);
		enderWandCore = new MobToolsItem(GeneralSettings.enderWandCoreID, "enderWandCore", true, 64);
		spiderWandCore = new MobToolsItem(GeneralSettings.spiderWandCoreID, "spiderWandCore", true, 64);
		blazeWandCore = new MobToolsItem(GeneralSettings.blazeWandCoreID, "blazeWandCore", true, 64);
		infuser = new MobToolsItem(GeneralSettings.infuser, "infuser", false, 1);
		infuserComplete = new MobToolsItem(GeneralSettings.infuserComplete, "infuserComplete", true, 1);
		
		//Blocks
		enderPad = new BlockEnderPad(GeneralSettings.enderPad);
		
		
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
		GameRegistry.registerItem(goldenrod, "goldenrod");
		GameRegistry.registerItem(creeperWandCore, "creeperWandCore");
		GameRegistry.registerItem(enderWandCore, "enderWandCore");
		GameRegistry.registerItem(spiderWandCore, "spiderWandCore");
		GameRegistry.registerItem(blazeWandCore, "blazeWandCore");
		GameRegistry.registerItem(infuser, "infuer");
		GameRegistry.registerItem(infuserComplete, "infuserComplete");
		
		//Blocks
		GameRegistry.registerBlock(enderPad, "enderPad");

		//Naming-----------------------------------------------------------------------------

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
		
		//Blocks
		LanguageRegistry.addName(enderPad, "Ender Pad");
	}

	public static void registerCraftingRecipes()
	{
		//Infusing Furnace

		CraftingManager.getInstance().addRecipe(new ItemStack(infusingFurnace, 1),
				"DXD",
				"XxX",
				"DXD", 'X', Block.blockGold, 'x', infuserComplete, 'D', Item.diamond);
		
		//Inert Wand Core
		CraftingManager.getInstance().addRecipe(new ItemStack(inertWandCore, 1),
				"SRS",
				"LDL",
				"SRS", 'S', Block.stone, 'R', Item.redstone, 'L', new ItemStack(Item.dyePowder, 1, 4), 'D', Item.diamond );
		
		//Goldenrod
		CraftingManager.getInstance().addRecipe(new ItemStack(goldenrod , 1),
				"  G",
				" G ",
				"G  ", 'G', Item.ingotGold);
		
		//Infuser
		CraftingManager.getInstance().addRecipe(new ItemStack(infuser, 1), 
				"GgE",
				"gDg",
				"SgB", 'G', Item.gunpowder, 'g', Item.ingotGold, 'E', Item.enderPearl, 'D', Item.diamond, 'S', Item.spiderEye, 'B', Item.blazeRod);
		
		//Ender Pad
		CraftingManager.getInstance().addRecipe(new ItemStack(enderPad, 2), 
				"WEW",
				"WWW",
				"WEW", 'W', Block.cloth, 'E', Item.enderPearl);

	}
}


