package com.pauljoda.mobtools.handlers;

import com.pauljoda.mobtools.tools.ToolManager;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public class MobToolsEventHandler {

	@ForgeSubscribe
	public void destorySword(PlayerDropsEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.isInvisible())
			{
				player.setInvisible(false);
			}
		}
	}

	@ForgeSubscribe
	public void fallEvent(LivingFallEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.inventory.getCurrentItem() != null)
			{
				if(player.inventory.getCurrentItem().itemID == ToolManager.spidiumWand.itemID)
				{
					player.inventory.getCurrentItem().damageItem((int)event.distance, player);
					event.setCanceled(true);
				}
			}
		}
	}

	@ForgeSubscribe
	public void creeperDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityCreeper)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)event.source.getEntity();
				IInventory inv = player.inventory;
				for(int i=0; i < inv.getSizeInventory(); i++)
				{
					if(inv.getStackInSlot(i) != null)
					{
						if(inv.getStackInSlot(i).itemID == ToolManager.infuser.itemID)
						{
							if(inv.getStackInSlot(i).stackTagCompound != null)
							{
								int creeperKills = inv.getStackInSlot(i).stackTagCompound.getInteger("creeperKills") + 1;
								if(creeperKills < 101)
								inv.getStackInSlot(i).stackTagCompound.setInteger("creeperKills", creeperKills);
								
								ItemStack par1ItemStack = inv.getStackInSlot(i);
								if(par1ItemStack.stackTagCompound != null)
								{
									boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 100;
									boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 100;
									boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 100;
									boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 100;
									if(creeper && ender && spider && blaze)
									{
										par1ItemStack.stackTagCompound.setBoolean("done", true);
					 				}
								}
								break;
							}
						}
					}
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void enderDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityEnderman)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)event.source.getEntity();
				IInventory inv = player.inventory;
				for(int i=0; i < inv.getSizeInventory(); i++)
				{
					if(inv.getStackInSlot(i) != null)
					{
						if(inv.getStackInSlot(i).itemID == ToolManager.infuser.itemID)
						{
							if(inv.getStackInSlot(i).stackTagCompound != null)
							{
								int enderKills = inv.getStackInSlot(i).stackTagCompound.getInteger("enderKills") + 1;
								if(enderKills < 101)
								inv.getStackInSlot(i).stackTagCompound.setInteger("enderKills", enderKills);
								
								ItemStack par1ItemStack = inv.getStackInSlot(i);
								if(par1ItemStack.stackTagCompound != null)
								{
									boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 100;
									boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 100;
									boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 100;
									boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 100;
									if(creeper && ender && spider && blaze)
									{
										par1ItemStack.stackTagCompound.setBoolean("done", true);
					 				}
								}
								break;
							}
						}
					}
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void spiderDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntitySpider)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)event.source.getEntity();
				IInventory inv = player.inventory;
				for(int i=0; i < inv.getSizeInventory(); i++)
				{
					if(inv.getStackInSlot(i) != null)
					{
						if(inv.getStackInSlot(i).itemID == ToolManager.infuser.itemID)
						{
							if(inv.getStackInSlot(i).stackTagCompound != null)
							{
								int spiderKills = inv.getStackInSlot(i).stackTagCompound.getInteger("spiderKills") + 1;
								if(spiderKills < 101)
								inv.getStackInSlot(i).stackTagCompound.setInteger("spiderKills", spiderKills);
								
								ItemStack par1ItemStack = inv.getStackInSlot(i);
								if(par1ItemStack.stackTagCompound != null)
								{
									boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 100;
									boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 100;
									boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 100;
									boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 100;
									if(creeper && ender && spider && blaze)
									{
										par1ItemStack.stackTagCompound.setBoolean("done", true);
					 				}
								}
								break;
							}
						}
					}
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void blazeDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityBlaze)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)event.source.getEntity();
				IInventory inv = player.inventory;
				for(int i=0; i < inv.getSizeInventory(); i++)
				{
					if(inv.getStackInSlot(i) != null)
					{
						if(inv.getStackInSlot(i).itemID == ToolManager.infuser.itemID)
						{
							if(inv.getStackInSlot(i).stackTagCompound != null)
							{
								int blazeKills = inv.getStackInSlot(i).stackTagCompound.getInteger("blazeKills") + 1;
								if(blazeKills < 101)
								inv.getStackInSlot(i).stackTagCompound.setInteger("blazeKills", blazeKills);
								
								ItemStack par1ItemStack = inv.getStackInSlot(i);
								if(par1ItemStack.stackTagCompound != null)
								{
									boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 100;
									boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 100;
									boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 100;
									boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 100;
									if(creeper && ender && spider && blaze)
									{
										par1ItemStack.stackTagCompound.setBoolean("done", true);
					 				}
								}
								break;
							}
						}
					}
				}
			}
		}
	}

}
