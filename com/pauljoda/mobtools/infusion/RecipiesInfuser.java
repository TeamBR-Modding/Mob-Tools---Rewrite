package com.pauljoda.mobtools.infusion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pauljoda.mobtools.tools.ToolManager;

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

	        if(item2.itemID == Item.ingotIron.itemID)
	        {
	        	if(item.itemID == Item.gunpowder.itemID)
	        		return new ItemStack(ToolManager.creepium, 1);
	        	
	        	if(item.itemID == Item.enderPearl.itemID)
	        		return new ItemStack(ToolManager.endium, 1);
	        	
	        	if(item.itemID == Item.spiderEye.itemID)
	        		return new ItemStack(ToolManager.spidium, 1);
	        	
	        	if(item.itemID == Item.blazeRod.itemID)
	        		return new ItemStack(ToolManager.blazium, 1);
	        	
	        }
	        
	        else if(item2.itemID == Item.swordIron.itemID)
	        {
	        	if(item.itemID == Item.gunpowder.itemID)
	        		return new ItemStack(ToolManager.creeperSword, 1);
	        	
	        	if(item.itemID == Item.enderPearl.itemID)
	        		return new ItemStack(ToolManager.endiumSword, 1);
	        	
	        	if(item.itemID == Item.spiderEye.itemID)
	        		return new ItemStack(ToolManager.spidiumSword, 1);
	        	
	        	if(item.itemID == Item.blazeRod.itemID)
	        		return new ItemStack(ToolManager.blaziumSword, 1);
	        	
	        }
	        
	        else if(item2.itemID == Item.pickaxeIron.itemID)
	        {
	        	if(item.itemID == Item.gunpowder.itemID)
	        		return new ItemStack(ToolManager.creeperPick, 1);
	        	
	        	if(item.itemID == Item.enderPearl.itemID)
	        		return new ItemStack(ToolManager.endiumPick, 1);
	        	
	        	if(item.itemID == Item.spiderEye.itemID)
	        		return new ItemStack(ToolManager.spidiumPick, 1);
	        	
	        	if(item.itemID == Item.blazeRod.itemID)
	        		return new ItemStack(ToolManager.blaziumPick, 1);
	        }
	        
	        else if(item2.itemID == Item.shovelIron.itemID)
	        {
	        	if(item.itemID == Item.gunpowder.itemID)
	        		return new ItemStack(ToolManager.creeperShovel, 1);
	        	
	        	if(item.itemID == Item.enderPearl.itemID)
	        		return new ItemStack(ToolManager.endiumShovel, 1);
	        	
	        	if(item.itemID == Item.spiderEye.itemID)
	        		return new ItemStack(ToolManager.spidiumShovel, 1);
	        	
	        	if(item.itemID == Item.blazeRod.itemID)
	        		return new ItemStack(ToolManager.blaziumShovel, 1);
	        }
	        
	        else if(item2.itemID == Item.axeIron.itemID)
	        {
	        	if(item.itemID == Item.gunpowder.itemID)
	        		return new ItemStack(ToolManager.creeperAxe, 1);
	        	
	        	if(item.itemID == Item.enderPearl.itemID)
	        		return new ItemStack(ToolManager.endiumAxe, 1);
	        	
	        	if(item.itemID == Item.spiderEye.itemID)
	        		return new ItemStack(ToolManager.spidiumAxe, 1);
	        	
	        	if(item.itemID == Item.blazeRod.itemID)
	        		return new ItemStack(ToolManager.blaziumAxe, 1);
	        }
	        
	        else if(item2.itemID == Item.hoeIron.itemID)
	        {
	        	if(item.itemID == Item.gunpowder.itemID)
	        		return new ItemStack(ToolManager.creeperHoe, 1);
	        	
	        	if(item.itemID == Item.enderPearl.itemID)
	        		return new ItemStack(ToolManager.endiumHoe, 1);
	        	
	        	if(item.itemID == Item.spiderEye.itemID)
	        		return new ItemStack(ToolManager.spidiumHoe, 1);
	        	
	        	if(item.itemID == Item.blazeRod.itemID)
	        		return new ItemStack(ToolManager.blaziumHoe, 1);
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
	        if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
	        {
	            ret = metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
	        }
	        if (ret < 0 && experienceList.containsKey(item.itemID))
	        {
	            ret = ((Float)experienceList.get(item.itemID)).floatValue();
	        }
	        return (ret < 0 ? 0 : ret);
	    }

	
		public static boolean isInput(ItemStack itemstack1) {
			if(itemstack1.itemID == Item.gunpowder.itemID || itemstack1.itemID == Item.enderPearl.itemID || itemstack1.itemID == Item.spiderEye.itemID || itemstack1.itemID == Item.blazeRod.itemID) 
				return true;
			else
				return false;
		}
	}