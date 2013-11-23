package com.pauljoda.mobtools.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Reference {
	
	public static final String MOD_ID = "mobtools";
	public static final String MOD_NAME = "Mob Tools";
	public static final String Version = "0.3";
	public static final int VERSION_CHECK_ATTEMPTS = 3;
	public static final String CHANNEL_NAME = MOD_ID;
	
	
	static int validInputs[] = {
						Item.ingotIron.itemID, Item.swordIron.itemID,
						Item.pickaxeIron.itemID, Item.shovelIron.itemID,
						Item.hoeIron.itemID, Item.axeIron.itemID
						};
	
	public static boolean isValidFusee(ItemStack item)
	{
		for(int i = 0; i < validInputs.length; i++)
		{
			if(validInputs[i] == item.itemID)
				return true;
		}
		return false;
	}

}
