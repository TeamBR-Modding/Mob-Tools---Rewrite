package com.pauljoda.mobtools.handlers;

import com.pauljoda.mobtools.lib.Reference;
import com.pauljoda.mobtools.tools.MobToolsItemPowerCore;
import com.pauljoda.mobtools.tools.ToolManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
				if(!event.entity.getEntityData().getBoolean("fromMobTools"))
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
									if(creeperKills < 26)
										inv.getStackInSlot(i).stackTagCompound.setInteger("creeperKills", creeperKills);

									ItemStack par1ItemStack = inv.getStackInSlot(i);
									if(par1ItemStack.stackTagCompound != null)
									{
										boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 25;
										boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 25;
										boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 25;
										boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 25;
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

	@ForgeSubscribe
	public void enderDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityEnderman)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				if(!event.entity.getEntityData().getBoolean("fromMobTools"))
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
									if(enderKills < 26)
										inv.getStackInSlot(i).stackTagCompound.setInteger("enderKills", enderKills);

									ItemStack par1ItemStack = inv.getStackInSlot(i);
									if(par1ItemStack.stackTagCompound != null)
									{
										boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 25;
										boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 25;
										boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 25;
										boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 25;
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

	@ForgeSubscribe
	public void spiderDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntitySpider)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				if(!event.entity.getEntityData().getBoolean("fromMobTools"))
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
									if(spiderKills < 26)
										inv.getStackInSlot(i).stackTagCompound.setInteger("spiderKills", spiderKills);

									ItemStack par1ItemStack = inv.getStackInSlot(i);
									if(par1ItemStack.stackTagCompound != null)
									{
										boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 25;
										boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 25;
										boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 25;
										boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 25;
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

	@ForgeSubscribe
	public void blazeDeath(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityBlaze)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				if(!event.entity.getEntityData().getBoolean("fromMobTools"))
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
									if(blazeKills < 26)
										inv.getStackInSlot(i).stackTagCompound.setInteger("blazeKills", blazeKills);

									ItemStack par1ItemStack = inv.getStackInSlot(i);
									if(par1ItemStack.stackTagCompound != null)
									{
										boolean creeper = par1ItemStack.stackTagCompound.getInteger("creeperKills") == 25;
										boolean ender = par1ItemStack.stackTagCompound.getInteger("enderKills") == 25;
										boolean spider = par1ItemStack.stackTagCompound.getInteger("spiderKills") == 25;
										boolean blaze = par1ItemStack.stackTagCompound.getInteger("blazeKills") == 25;
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

	@ForgeSubscribe
	public void isVulnerablePlayer(LivingHurtEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.getActivePotionEffect(Potion.invisibility) == null && player.isInvisible() && !player.capabilities.isCreativeMode)
			{
				player.setHealth(0F);
			}
		}
	}
	@ForgeSubscribe
	public void powerCore(LivingDeathEvent event)
	{
		if(event.source.getEntity() instanceof EntityPlayer)
		{
			if(!event.entity.getEntityData().getBoolean("fromMobTools"))
			{
				EntityPlayer player = (EntityPlayer)event.source.getEntity();
				for(int i = 0; i < 10; i++)
				{
					if(player.inventory.getStackInSlot(i) != null)
					{
						if(player.inventory.getStackInSlot(i).getItem() == ToolManager.powerCore)
						{
							ItemStack powerCore = player.inventory.getStackInSlot(i);
							if(powerCore.stackTagCompound == null)
							{
								if(isValidEntity(event.entity))
								{
									powerCore.stackTagCompound = new NBTTagCompound();
									powerCore.stackTagCompound.setInteger("kills", 1);
									powerCore.stackTagCompound.setInteger("tierKills", 1);
									powerCore.stackTagCompound.setInteger("tier", 0);
									powerCore.stackTagCompound.setInteger("dimension", 0);
									powerCore.stackTagCompound.setString("mobName", MobToolsItemPowerCore.getMobName(event.entity.getClass().getName()));
									return;
								}
							}
							else
							{
								if(powerCore.stackTagCompound.getString("mobName").equals(MobToolsItemPowerCore.getMobName(event.entity.getClass().getName())) && powerCore.stackTagCompound.getInteger("tier") < 5)
								{
									powerCore.stackTagCompound.setInteger("kills", powerCore.stackTagCompound.getInteger("kills") + 1);
									powerCore.stackTagCompound.setInteger("tierKills", powerCore.stackTagCompound.getInteger("tierKills") + 1);
									if(powerCore.stackTagCompound.getInteger("tierKills") == Reference.getTierMax(powerCore.stackTagCompound.getInteger("tier")))
									{
										powerCore.stackTagCompound.setInteger("tierKills", 0);
										powerCore.stackTagCompound.setInteger("tier", powerCore.stackTagCompound.getInteger("tier") + 1);

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

	private boolean isValidEntity(Entity entity) 
	{
		if(entity.getClass().getName() == EntityCow.class.getName())
			return true;
		if(entity.getClass().getName() == EntityChicken.class.getName())
			return true;
		if(entity.getClass().getName() == EntityPig.class.getName())
			return true;
		if(entity.getClass().getName() == EntitySheep.class.getName())
			return true;
		if(entity.getClass().getName() == EntityVillager.class.getName())
			return true;
		if(entity.getClass().getName() == EntityEnderman.class.getName())
			return true;
		if(entity.getClass().getName() == EntitySpider.class.getName())
			return true;
		if(entity.getClass().getName() == EntityBlaze.class.getName())
			return true;
		if(entity.getClass().getName() == EntitySkeleton.class.getName())
			return true;
		if(entity.getClass().getName() == EntitySlime.class.getName())
			return true;
		if(entity.getClass().getName() == EntityCreeper.class.getName())
			return true;
		if(entity.getClass().getName() == EntityZombie.class.getName())
			return true;

		return false;
	}


}
