package com.pauljoda.mobtools.handlers;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import com.pauljoda.mobtools.lib.ConfigurationSettings;
import com.pauljoda.mobtools.lib.Reference;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;

public class GeneralSettings {

	private static Configuration config;

	public static void init(File configFile) {

		config = new Configuration(configFile);

		try {
			config.load();

			/* Version check */
			ConfigurationSettings.DISPLAY_VERSION_RESULT = config.get(CATEGORY_GENERAL, ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME, ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT).getBoolean(ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT);
			ConfigurationSettings.LAST_DISCOVERED_VERSION = config.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_DEFAULT).getString();
			ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE = config.get(CATEGORY_GENERAL, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_CONFIGNAME, ConfigurationSettings.LAST_DISCOVERED_VERSION_TYPE_DEFAULT).getString();

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



