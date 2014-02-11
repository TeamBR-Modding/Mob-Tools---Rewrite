package com.pauljoda.mobtools.infusion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pauljoda.mobtools.tools.ToolManager;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipiesInfuser {

	/** The list of smelting results. */
	private Map<Integer, Float> experienceList = new HashMap<Integer, Float>();
	private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

	/**
	 * Used to get the resulting ItemStack form a source ItemStack
	 * @param item The Source ItemStack
	 * @return The result ItemStack
	 */
	public static ItemStack getSmeltingResult(ItemStack item, ItemStack item2) 
	{
		if (item == null)
		{
			return null;
		}

		if(item2.getItem() == Items.diamond)
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creepium, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.endium, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spidium, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazium, 1);

		}

		else if(item2.getItem() == Items.diamond_sword  && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperSword, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.endiumSword, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spidiumSword, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blaziumSword, 1);

		}

		else if(item2.getItem() == Items.diamond_pickaxe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperPick, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.endiumPick, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spidiumPick, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blaziumPick, 1);
		}

		else if(item2.getItem() == Items.diamond_shovel && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperShovel, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.endiumShovel, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spidiumShovel, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blaziumShovel, 1);
		}

		else if(item2.getItem() == Items.diamond_axe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperAxe, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.endiumAxe, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spidiumAxe, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blaziumAxe, 1);
		}

		else if(item2.getItem() == Items.diamond_hoe && !item2.isItemDamaged())
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperHoe, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.endiumHoe, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spidiumHoe, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blaziumHoe, 1);
		}

		else if(item2.getItem() == ToolManager.inertWandCore)
		{
			if(item.getItem() == Items.gunpowder)
				return new ItemStack(ToolManager.creeperWandCore, 1);

			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.enderWandCore, 1);

			if(item.getItem() == Items.spider_eye)
				return new ItemStack(ToolManager.spiderWandCore, 1);

			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeWandCore, 1);
		}

		else if(item2.getItem() == ToolManager.goldenrod)
		{
			if(item.getItem() == ToolManager.creeperWandCore)
				return new ItemStack(ToolManager.creeperWand, 1);

			if(item.getItem() == ToolManager.enderWandCore)
				return new ItemStack(ToolManager.endiumWand, 1);

			if(item.getItem() == ToolManager.spiderWandCore)
				return new ItemStack(ToolManager.spidiumWand, 1);

			if(item.getItem() == ToolManager.blazeWandCore)
				return new ItemStack(ToolManager.blaziumWand, 1);
		}

		else if(item2.getItem() == ToolManager.blankAmulet)
		{
			if(item.getItem() == Items.blaze_rod)
				return new ItemStack(ToolManager.blazeAmulet, 1);

			if(item.getItem() == Items.feather)
				return new ItemStack(ToolManager.speedAmulet, 1);
			
			if(item.getItem() == Items.slime_ball)
				return new ItemStack(ToolManager.jumpAmulet, 1);
			
			if(item.getItem() == Items.ender_pearl)
				return new ItemStack(ToolManager.teleportAmulet, 1);

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
				|| itemstack1.getItem() == ToolManager.creeperWandCore || itemstack1.getItem() == ToolManager.enderWandCore || itemstack1.getItem() == ToolManager.spiderWandCore 
				|| itemstack1.getItem() == ToolManager.blazeWandCore || itemstack1.getItem() == Items.feather || itemstack1.getItem() == Items.slime_ball) 
			return true;
		else
			return false;
	}
}