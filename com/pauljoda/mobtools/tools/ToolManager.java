package com.pauljoda.mobtools.tools;

import com.pauljoda.mobtools.MobTools;
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

		//Naming-----------------------------------------------------------------------------

		//Infusing Furnace
		LanguageRegistry.addName(infusingFurnace, "Infusing Furnace");
		
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
	}

	public static void registerCraftingRecipes()
	{
		//Infusing Furnace
		CraftingManager.getInstance().addRecipe(new ItemStack(infusingFurnace, 1),
				"XXX",
				"XxX",
				"XXX", 'X', Block.obsidian, 'x', Item.diamond);

	}
}


