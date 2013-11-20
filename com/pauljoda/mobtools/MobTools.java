package com.pauljoda.mobtools;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;

import com.pauljoda.mobtools.common.CommonProxy;
import com.pauljoda.mobtools.handlers.ConfigurationHandler;
import com.pauljoda.mobtools.handlers.LogHelper;
import com.pauljoda.mobtools.handlers.VersionHelper;
import com.pauljoda.mobtools.handlers.VersionTickHandler;
import com.pauljoda.mobtools.lib.Reference;
import com.pauljoda.mobtools.tools.ToolManager;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(name = Reference.MOD_NAME, modid = Reference.MOD_ID, version = Reference.Version)
@NetworkMod(channels = { Reference.MOD_NAME },clientSideRequired=true, serverSideRequired=false)

public class MobTools {
	

	@Instance("mobtools")
	public static MobTools instance;
	
	@SidedProxy( clientSide="com.pauljoda.mobtools.client.ClientProxy", serverSide="com.pauljoda.mobtools.common.CommonProxy")
	public static CommonProxy proxy;
	
	
	//Creeper
	public static EnumToolMaterial CREEPER_TOOL_MATERIAL = EnumHelper.addToolMaterial("CREEPER_TOOL_MATERIAL", 2, 200, 100.0F, 10.0F, 0);
	//Ender
	public static EnumToolMaterial ENDER_TOOL_MATERIAL = EnumHelper.addToolMaterial("ENDER_TOOL_MATERIAL", 2, 1600, 8.0F, 5F, 4);
	//Blaze
	public static EnumToolMaterial BLAZE_TOOL_MATERIAL = EnumHelper.addToolMaterial("BLAZE_TOOL_MATERIAL", 2, 400, 9.0F, 6F, 8);
	//Spider
	public static EnumToolMaterial SPIDER_TOOL_MATERIAL = EnumHelper.addToolMaterial("SPIDER_TOOL_MATERIAL", 2, 400, 5.0F, 4F, 50);
	
	public static CreativeTabs tabMobTools = new CreativeTabs("tabMobTools") {
		public ItemStack getIconItemStack() {
			return new ItemStack(ToolManager.creeperSword.itemID, 1, 0);
		}
	};
	
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
	     // Initialize the log helper
        LogHelper.init();

        // Initialize the configuration
        ConfigurationHandler.init(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME.toLowerCase() + File.separator);

        // Conduct the version check and log the result
        VersionHelper.execute();
        
        // Initialize the Version Check Tick Handler (Client only)
        TickRegistry.registerTickHandler(new VersionTickHandler(), Side.CLIENT);
        
        // Add Creative Tab
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabMobTools", "en_US", "Mob Tools");
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		//Register Tools
		ToolManager.registerTools();
		ToolManager.registerCraftingRecipes();
		
		//RegisterTileEntity
		proxy.registerTileEntities();
		
	    //Lets get Gui
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}

