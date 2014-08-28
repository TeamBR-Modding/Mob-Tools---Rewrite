package com.pauljoda.mobtools.lib;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.pauljoda.mobtools.item.ItemManager;
import com.pauljoda.mobtools.tools.ToolManager;

public class RecipiesInfuser {

	//Before you go saying this is a horrible way to do it I know. Its not that great, but it works
	
	/** The list of smelting results. */
	private Map<Integer, Float> experienceList = new HashMap<Integer, Float>();
	private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();


	public static ItemStack getSmeltingResult(ItemStack item, ItemStack item2) 
	{
		if (item == null)
		{
			return null;
		}

		//Wooden Tools-----------------------------------------------------------
		if(item2.getItem() == Items.wooden_axe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperAxeWood, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderAxeWood, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderAxeWood, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeAxeWood, 1);
		}
		
		else if(item2.getItem() == Items.wooden_hoe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperHoeWood, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderHoeWood, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderHoeWood, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeHoeWood, 1);
		}
		
		else if(item2.getItem() == Items.wooden_pickaxe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperPickWood, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderPickWood, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderPickWood, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazePickWood, 1);
		}
		
		else if(item2.getItem() == Items.wooden_shovel && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperShovelWood, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderShovelWood, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderShovelWood, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeShovelWood, 1);
		}
		
		else if(item2.getItem() == Items.wooden_sword && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperSwordWood, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderSwordWood, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderSwordWood, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeSwordWood, 1);
		}
		
		//Stone Tools--------------------------------------------------------------
		if(item2.getItem() == Items.stone_axe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperAxeStone, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderAxeStone, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderAxeStone, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeAxeStone, 1);
		}
		
		else if(item2.getItem() == Items.stone_hoe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperHoeStone, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderHoeStone, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderHoeStone, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeHoeStone, 1);
		}
		
		else if(item2.getItem() == Items.stone_pickaxe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperPickStone, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderPickStone, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderPickStone, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazePickStone, 1);
		}
		
		else if(item2.getItem() == Items.stone_shovel && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperShovelStone, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderShovelStone, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderShovelStone, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeShovelStone, 1);
		}
		
		else if(item2.getItem() == Items.stone_sword && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperSwordStone, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderSwordStone, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderSwordStone, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeSwordStone, 1);
		}
		
		//Iron Tools---------------------------------------------------------------
		if(item2.getItem() == Items.iron_axe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperAxeIron, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderAxeIron, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderAxeIron, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeAxeIron, 1);
		}
		
		else if(item2.getItem() == Items.iron_hoe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperHoeIron, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderHoeIron, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderHoeIron, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeHoeIron, 1);
		}
		
		else if(item2.getItem() == Items.iron_pickaxe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperPickIron, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderPickIron, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderPickIron, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazePickIron, 1);
		}
		
		else if(item2.getItem() == Items.iron_shovel && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperShovelIron, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderShovelIron, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderShovelIron, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeShovelIron, 1);
		}
		
		else if(item2.getItem() == Items.iron_sword && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperSwordIron, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderSwordIron, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderSwordIron, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeSwordIron, 1);
		}
		
		//Gold Tools---------------------------------------------------------------
		if(item2.getItem() == Items.golden_axe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperAxeGold, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderAxeGold, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderAxeGold, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeAxeGold, 1);
		}
		
		else if(item2.getItem() == Items.golden_hoe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperHoeGold, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderHoeGold, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderHoeGold, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeHoeGold, 1);
		}
		
		else if(item2.getItem() == Items.golden_pickaxe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperPickGold, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderPickGold, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderPickGold, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazePickGold, 1);
		}
		
		else if(item2.getItem() == Items.golden_shovel && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperShovelGold, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderShovelGold, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderShovelGold, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeShovelGold, 1);
		}
		
		else if(item2.getItem() == Items.golden_sword && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperSwordGold, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderSwordGold, 1);
			
			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderSwordGold, 1);
			
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeSwordGold, 1);
		}
		
		
		//Diamond Tools------------------------------------------------------------
		else if(item2.getItem() == Items.diamond_sword  && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperSwordDiamond, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderSwordDiamond, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderSwordDiamond, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeSwordDiamond, 1);

		}

		else if(item2.getItem() == Items.diamond_pickaxe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperPickDiamond, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderPickDiamond, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderPickDiamond, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazePickDiamond, 1);
		}

		else if(item2.getItem() == Items.diamond_shovel && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperShovelDiamond, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderShovelDiamond, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderShovelDiamond, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeShovelDiamond, 1);
		}

		else if(item2.getItem() == Items.diamond_axe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperAxeDiamond, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderAxeDiamond, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderAxeDiamond, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeAxeDiamond, 1);
		}

		else if(item2.getItem() == Items.diamond_hoe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperHoeDiamond, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderHoeDiamond, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderHoeDiamond, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeHoeDiamond, 1);
		}

		else if(item2.getItem() == ItemManager.inertWandCore)
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ItemManager.creeperWandCore, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ItemManager.enderWandCore, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ItemManager.spiderWandCore, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ItemManager.blazeWandCore, 1);
		}
		
		else if(item2.getItem() == Items.diamond)
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ItemManager.creepium, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ItemManager.endium, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ItemManager.spidium, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ItemManager.blazium, 1);

		}

		else if(item2.getItem() == ItemManager.goldenrod)
		{
			if(item.getItem() == ItemManager.creeperWandCore)
				return new ItemStack(ToolManager.creeperWand, 1);

			if(item.getItem() == ItemManager.enderWandCore)
				return new ItemStack(ToolManager.endiumWand, 1);

			if(item.getItem() == ItemManager.spiderWandCore)
				return new ItemStack(ToolManager.spidiumWand, 1);

			if(item.getItem() == ItemManager.blazeWandCore)
				return new ItemStack(ToolManager.blaziumWand, 1);
		}

		else if(item2.getItem() == ItemManager.blankAmulet)
		{
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ItemManager.blazeAmulet, 1);

			if(item.getItem() == Items.feather)
				return new ItemStack(ItemManager.speedAmulet, 1);
			
			if(item.getItem() == Items.slime_ball)
				return new ItemStack(ItemManager.jumpAmulet, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ItemManager.teleportAmulet, 1);
			
			if(item.getItem() == Items.iron_ingot)
				return new ItemStack(ItemManager.magnetAmulet, 1);
			
			if(item.getItem() == Items.golden_carrot)
				return new ItemStack(ItemManager.hungerAmulet, 1);

		}

		return null;
	}

	/**
	 * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
	 */
	public float getExperience(ItemStack item)
	{
		if (item == null || item.getItem() == null)
		{
			return 0;
		}
		float ret = item.getItem().getSmeltingExperience(item);
		if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.getUnlocalizedName(), item.getItemDamage())))
		{
			ret = metaExperience.get(Arrays.asList(item.getUnlocalizedName(), item.getItemDamage()));
		}
		if (ret < 0 && experienceList.containsKey(item))
		{
			ret = ((Float)experienceList.get(item).floatValue());
		}
		return (ret < 0 ? 0 : ret);
	}


	public static boolean isInput(ItemStack itemstack1) {
		if(itemstack1.getItem() == Items.gunpowder || itemstack1.getItem() == Items.ender_pearl || itemstack1.getItem() == Items.spider_eye || itemstack1.getItem() == Items.blaze_rod 
				|| itemstack1.getItem() == ItemManager.creeperWandCore || itemstack1.getItem() == ItemManager.enderWandCore || itemstack1.getItem() == ItemManager.spiderWandCore 
				|| itemstack1.getItem() == ItemManager.blazeWandCore || itemstack1.getItem() == Items.feather || itemstack1.getItem() == Items.slime_ball) 
			return true;
		else
			return false;
	}
}