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

	//Creeper Tools
	public static Item creeperSword = null;
	public static Item endiumSword = null;
	public static Item spidiumSword = null;
	public static Item blaziumSword = null;

	public static void registerTools()
	{
		//Adding

		//Infusing Furnace
		infusingFurnace = new BlockInfusingFurnace(GeneralSettings.infusingFurnace, false).setUnlocalizedName("infusingFurnace").setCreativeTab(MobTools.tabMobTools);
		infusingFurnaceActive = new BlockInfusingFurnace(GeneralSettings.infusingFurnaceActive, true).setUnlocalizedName("infusingFurnaceActive");
		
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

		//Registering

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

		//Naming

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


