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



