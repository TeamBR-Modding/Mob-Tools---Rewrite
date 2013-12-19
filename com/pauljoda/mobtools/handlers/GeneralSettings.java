package com.pauljoda.mobtools.handlers;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import com.pauljoda.mobtools.lib.ConfigurationSettings;
import com.pauljoda.mobtools.lib.Reference;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;

public class GeneralSettings {

	//Infuser
	public static int infusingFurnace;
	public static int infusingFurnaceActive;
	
	//Ingots
	public static int creepium;
	public static int endium;
	public static int spidium;
	public static int blazium;
	
	//Swords
	public static int creeperSwordID;
	public static int endiumSwordID;
	public static int spidiumSwordID;
	public static int blaziumSwordID;
	
	//Pickaxes
	public static int creeperPickID;
	public static int endiumPickID;
	public static int spidiumPickID;
	public static int blaziumPickID;
	
	//Shovels
	public static int creeperShovelID;
	public static int endiumShovelID;
	public static int spidiumShovelID;
	public static int blaziumShovelID;
	
	//Axes
	public static int creeperAxeID;
	public static int endiumAxeID;
	public static int spidiumAxeID;
	public static int blaziumAxeID;
	
	//Hoes
	public static int creeperHoeID;
	public static int endiumHoeID;
	public static int spidiumHoeID;
	public static int blaziumHoeID;
	
	//Wands
	public static int creeperWandID;
	public static int endiumWandID;
	public static int spidiumWandID;
	public static int blaziumWandID;
	
	//Items
	public static int inertWandCoreID;
	public static int goldenrodID;
	public static int creeperWandCoreID;
	public static int enderWandCoreID;
	public static int spiderWandCoreID;
	public static int blazeWandCoreID;
	public static int infuser;
	public static int infuserComplete;
	public static int powerCore;
	
	//Blocks
	public static int enderPad;
	public static int mobToolsSpawner;
	
	//Values
	public static int maxEnderPadDistance;
	public static int tierZeroKills;
	public static int tierOneKills;
	public static int tierTwoKills;
	public static int tierThreeKills;
	public static int tierFourKills;
	
	
	private static Configuration config;

	public static void init(File configFile) {

		config = new Configuration(configFile);

		try {
			config.load();

			/* Version check */
			ConfigurationSettings.DISPLAY_VERSION_RESULT = config.get(CATEGORY_GENERAL, ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME, ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT).getBoolean(ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT);
			ConfigurationSettings.LAST_DISCOVERED_VERSION = config.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_DEFAULT).getString();
			ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE = config.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_DEFAULT).getString();

			//Infusing Furnace
			infusingFurnace = config.getBlock("Infusing Furnace", 798).getInt();
			infusingFurnaceActive = config.getBlock("Infusing Furnace Active", 799).getInt();
			
			//Ingots
			creepium = config.getItem("Creepium ID", 800).getInt();
			endium = config.getItem("Endium ID", 801).getInt();
			spidium = config.getItem("Spidium ID", 802).getInt();
			blazium = config.getItem("Blazium ID", 803).getInt();
			
			//Swords
			creeperSwordID = config.getItem("Creeper Sword ID", 805).getInt();
			endiumSwordID = config.getItem("Endium Sword ID", 806).getInt();
			spidiumSwordID = config.getItem("Spidium Sword ID", 807).getInt();
			blaziumSwordID = config.getItem("Blazium Sword ID", 808).getInt();
			
			//Picks
			creeperPickID = config.getItem("Creeper PickAxe ID", 809).getInt();
			endiumPickID = config.getItem("Endium PickAxe ID", 810).getInt();
			spidiumPickID = config.getItem("Spidium PickAxe ID", 811).getInt();
			blaziumPickID = config.getItem("Blazium PickAxe ID", 812).getInt();
			
			//Shovels
			creeperShovelID = config.getItem("Creeper Shovel ID", 813).getInt();
			endiumShovelID = config.getItem("Endium Shovel ID", 814).getInt();
			spidiumShovelID = config.getItem("Spidium Shovel ID", 815).getInt();
			blaziumShovelID = config.getItem("Blazium Shovel ID", 816).getInt();
			
			//Axes
			creeperAxeID = config.getItem("Creeper Axe ID", 817).getInt();
			endiumAxeID = config.getItem("Endium Axe ID", 818).getInt();
			spidiumAxeID = config.getItem("Spidium Axe ID", 819).getInt();
			blaziumAxeID = config.getItem("Blazium Axe ID", 820).getInt();
			
			//Hoes
			creeperHoeID = config.getItem("Creeper Hoe ID", 821).getInt();
			endiumHoeID = config.getItem("Endium Hoe ID", 822).getInt();
			spidiumHoeID = config.getItem("Spider Hoe ID", 823).getInt();
			blaziumHoeID = config.getItem("Blazium Hoe ID", 824).getInt();
			
			//Wands
			creeperWandID = config.getItem("Creeper Wand ID",  825).getInt();
			endiumWandID = config.getItem("Endium Wand ID", 826).getInt();
			spidiumWandID = config.getItem("Spidium Wand ID", 827).getInt();
			blaziumWandID = config.getItem("Blazium Wand ID", 828).getInt();
			
			//Items
			inertWandCoreID = config.getItem("Inert Core ID", 829).getInt();
			goldenrodID = config.getItem("GoldenRod ID", 830).getInt();
			creeperWandCoreID = config.getItem("Creeper Wand Core", 831).getInt();
			enderWandCoreID = config.getItem("Ender Wand Core ID", 832).getInt();
			spiderWandCoreID = config.getItem("Spider Wand Core ID", 833).getInt();
			blazeWandCoreID = config.getItem("Blaze Wand Core ID", 834).getInt();
			infuser = config.getItem("Infuer", 835).getInt();
			infuserComplete = config.getItem("Infuser Complete", 836).getInt();
			powerCore = config.getItem("Power Core", 837).getInt();
			
			//Blocks
			enderPad = config.getBlock("Ender Pad", 840).getInt();
			mobToolsSpawner = config.getBlock("Mob Tools Spawner", 841).getInt();
			
			//Vaules
			maxEnderPadDistance = config.get(CATEGORY_GENERAL, "Max Ender Pad Distance", 25).getInt();
			tierZeroKills = config.get(CATEGORY_GENERAL, "Tier 0 Max Kills", 20).getInt();
			tierOneKills = config.get(CATEGORY_GENERAL, "Tier 1 Max Kills", 100).getInt();
			tierTwoKills = config.get(CATEGORY_GENERAL, "Tier 2 Max Kills", 300).getInt();
			tierThreeKills = config.get(CATEGORY_GENERAL, "Tier 3 Max Kills", 400).getInt();
			tierFourKills = config.get(CATEGORY_GENERAL, "Tier 4 Max Kills", 500).getInt();
					
			
			
			
		}
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its general configuration");
		}
		finally {
			config.save();
		}
	}
	
    public static void set(String categoryName, String propertyName, String newValue) {

    	config.load();
        if (config.getCategoryNames().contains(categoryName)) {
            if (config.getCategory(categoryName).containsKey(propertyName)) {
            	config.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        config.save();
    }
}



