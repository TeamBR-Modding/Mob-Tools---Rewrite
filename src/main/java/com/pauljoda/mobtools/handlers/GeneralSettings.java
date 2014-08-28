package com.pauljoda.mobtools.handlers;

import java.io.File;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.Configuration;

import com.pauljoda.mobtools.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class GeneralSettings {

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

			//Vaules
			maxEnderPadDistance = config.get("Spawner Settings", "Max Ender Pad Distance", 25).getInt();
			tierZeroKills = config.get("Spawner Settings", "Tier 0 Max Kills", 20).getInt();
			tierOneKills = config.get("Spawner Settings", "Tier 1 Max Kills", 100).getInt();
			tierTwoKills = config.get("Spawner Settings", "Tier 2 Max Kills", 300).getInt();
			tierThreeKills = config.get("Spawner Settings", "Tier 3 Max Kills", 400).getInt();
			tierFourKills = config.get("Spawner Settings", "Tier 4 Max Kills", 500).getInt();
					
			
			
			
		}
		catch (Exception e) {
			FMLLog.log(Level.ERROR, e, Reference.MOD_NAME + " has had a problem loading its general configuration");
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



