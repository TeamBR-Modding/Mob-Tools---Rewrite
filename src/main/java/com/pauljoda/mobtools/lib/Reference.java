package com.pauljoda.mobtools.lib;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.pauljoda.mobtools.handlers.GeneralSettings;
import com.pauljoda.mobtools.item.ItemManager;

public class Reference {
	
	public static final String MOD_ID = "mobtools";
	public static final String MOD_NAME = "Mob Tools";
	public static final String Version = "1.9";
	public static final int VERSION_CHECK_ATTEMPTS = 3;
	public static final String CHANNEL_NAME = MOD_ID;
	
	
	static String validInputs[] = {
						Items.diamond.getUnlocalizedName(), Items.diamond_sword.getUnlocalizedName(),
						Items.diamond_pickaxe.getUnlocalizedName(), Items.diamond_shovel.getUnlocalizedName(),
						Items.diamond_hoe.getUnlocalizedName(), Items.diamond_axe.getUnlocalizedName(),
						ItemManager.inertWandCore.getUnlocalizedName(), ItemManager.goldenrod.getUnlocalizedName(),
						ItemManager.blankAmulet.getUnlocalizedName()
						};
	
	public static boolean isValidFusee(ItemStack item)
	{
		for(int i = 0; i < validInputs.length; i++)
		{
			if(validInputs[i].equals(item.getUnlocalizedName()))
				return true;
		}
		return false;
	}
	

	public static int getTierMax(int kills)
	{
		switch(kills)
		{
		case 0: return GeneralSettings.tierZeroKills;
		case 1: return GeneralSettings.tierOneKills;
		case 2: return GeneralSettings.tierTwoKills;
		case 3: return GeneralSettings.tierThreeKills;
		case 4: return GeneralSettings.tierFourKills;
		}
		return 0;
	}
	
	public static int repairAlterRenderID;
	public static int infuserRenderID;
	
	public static boolean isWoodenTool(Item item)
	{
		if(item == Items.wooden_axe || item == Items.wooden_hoe || item == Items.wooden_pickaxe
				|| item == Items.wooden_shovel || item == Items.wooden_sword)
			return true;
		
		return false;
	}
	
	public static boolean isStoneTool(Item item)
	{
		if(item == Items.stone_axe || item == Items.stone_hoe || item == Items.stone_pickaxe
				|| item == Items.stone_shovel || item == Items.stone_sword)
			return true;
		
		return false;
	}
	
	public static boolean isIronTool(Item item)
	{
		if(item == Items.iron_axe || item == Items.iron_hoe || item == Items.iron_pickaxe
				|| item == Items.iron_shovel || item == Items.iron_sword)
			return true;
		
		return false;
	}
	
	public static boolean isGoldTool(Item item)
	{
		if(item == Items.golden_axe || item == Items.golden_hoe || item == Items.golden_pickaxe
				|| item == Items.golden_shovel || item == Items.golden_sword)
			return true;
		
		return false;
	}
	
	public static boolean isDiamondTool(Item item)
	{
		if(item == Items.diamond_axe || item == Items.diamond_hoe || item == Items.diamond_pickaxe
				|| item == Items.diamond_shovel || item == Items.diamond_sword)
			return true;
		
		return false;
	}
	
	public static boolean isModItem(Item item)
	{
		if(item == ItemManager.inertWandCore || item == ItemManager.goldenrod || item == ItemManager.blankAmulet
				|| item == Items.diamond)
			return true;
		
		return false;
	}
		
}
