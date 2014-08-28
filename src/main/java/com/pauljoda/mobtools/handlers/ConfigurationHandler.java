package com.pauljoda.mobtools.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	public static Configuration configuration;


	public static void init(String configPath) {

		GeneralSettings.init(new File(configPath + "general.properties"));

	}
}
