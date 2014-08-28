package com.pauljoda.mobtools.item;

import com.pauljoda.mobtools.amulets.MobToolsAmulet;
import com.pauljoda.mobtools.amulets.MobToolsBlazeAmulet;
import com.pauljoda.mobtools.amulets.MobToolsHungerAmulet;
import com.pauljoda.mobtools.amulets.MobToolsJumpAmulet;
import com.pauljoda.mobtools.amulets.MobToolsMagnetAmulet;
import com.pauljoda.mobtools.amulets.MobToolsSpeedAmulet;
import com.pauljoda.mobtools.amulets.MobToolsTeleportAmulet;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ItemManager 
{
	
	//Ingots
	public static Item creepium = null;
	public static Item endium = null;
	public static Item spidium = null;
	public static Item blazium = null;
	
	
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
	public static Item magnetAmulet = null;
	public static Item hungerAmulet = null;
	
	public static void registerItems()
	{
		//Ingots
		creepium = new MobToolsGems("creepium");
		endium = new MobToolsGems("endium");
		spidium = new MobToolsGems("spidium");
		blazium = new MobToolsGems("blazium");
		
		
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
		enderMail = new MobToolsItemEnderMail();
		
		//Amulets
		blankAmulet = new MobToolsAmulet();
		blazeAmulet = new MobToolsBlazeAmulet();
		speedAmulet = new MobToolsSpeedAmulet();
		jumpAmulet = new MobToolsJumpAmulet();
		teleportAmulet = new MobToolsTeleportAmulet();
		magnetAmulet = new MobToolsMagnetAmulet();
		hungerAmulet = new MobToolsHungerAmulet();
		
		
		//Ingots
		GameRegistry.registerItem(creepium, "creepium");
		GameRegistry.registerItem(endium, "endium");
		GameRegistry.registerItem(spidium, "spidium");
		GameRegistry.registerItem(blazium, "blazium");
		
		
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
		GameRegistry.registerItem(magnetAmulet, "magnetAmulet");
		GameRegistry.registerItem(hungerAmulet, "hungerAmulet");
	}
	
	public static void registerItemCrafting()
	{
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
	
	
	//Power Core 
	CraftingManager.getInstance().addRecipe(new ItemStack(powerCore, 1), 
			"CIE",
			"III",
			"SIB", 'C', creepium, 'I', Items.iron_ingot, 'E', endium, 'S', spidium, 'B', blazium);
	
	//Erase Power Core
	CraftingManager.getInstance().addShapelessRecipe(new ItemStack(powerCore, 1), new ItemStack(Items.water_bucket, 1), new ItemStack(powerCore, 1));

	//Ender Mail
	CraftingManager.getInstance().addShapelessRecipe(new ItemStack(enderMail, 8), new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.paper, 1));
	
	
	//Amulet
	CraftingManager.getInstance().addRecipe(new ItemStack(blankAmulet, 1),
			"BBB",
			"BGB",
			"BBB", 'B', Items.gold_ingot, 'G', Items.glowstone_dust);
	
	
	
	}

}
