package com.pauljoda.mobtools.amulets;

import java.util.ArrayList;
import java.util.List;

import com.pauljoda.mobtools.MobTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class MobToolsMagnetAmulet extends MobToolsAmulet {

	int cooldown = 20;
	
	public MobToolsMagnetAmulet() {
		super();
		this.setUnlocalizedName("magnetAmulet");
		this.setCreativeTab(MobTools.tabMobTools);
		this.setMaxDamage(8000);
	}


	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World par2World, EntityPlayer player)
	{

		if(player.isSneaking())
		{
			if(itemstack.stackTagCompound == null)
			{
				itemstack.stackTagCompound = new NBTTagCompound();
				itemstack.stackTagCompound.setBoolean("Active", true);
				if(!par2World.isRemote)
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Magnetism Enabled"));
			}
			else
			{
				if(itemstack.stackTagCompound.getBoolean("Active"))
				{
					itemstack.stackTagCompound.setBoolean("Active", false);
					if(!par2World.isRemote)
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Magnetism Disabled"));
				}
				else
				{
					itemstack.stackTagCompound.setBoolean("Active", true);
					if(!par2World.isRemote)
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Magnetism Enabled"));
				}

			}
		}
		return itemstack;
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean isCurrentItem)
	{
		EntityPlayer player = (EntityPlayer)entity;

		if(itemstack.getItemDamage() >= this.getMaxDamage())
		{
			for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				if(player.inventory.getStackInSlot(i) != null)
				{
					if(player.inventory.getStackInSlot(i).equals(itemstack))
					{
						player.inventory.setInventorySlotContents(i, null);
					}
				}
			}
			return;
		}

		if(itemstack.stackTagCompound != null)
		{
			if(itemstack.stackTagCompound.getBoolean("Active"))
			{
				
				cooldown--;
				if(cooldown < 0)
				{
					itemstack.damageItem(1, player);
					cooldown = 20;
				}
				
				AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(player.posX - 10.0D, player.posY - 10.0D, player.posZ - 10.0D, player.posX + 10.0D, player.posY + 10.0D, player.posZ + 10.0D);

				ArrayList<Entity> entities = new ArrayList();

				List<EntityItem> itemEntities = world.getEntitiesWithinAABB(EntityItem.class, bb);
				List<EntityXPOrb> xpOrbs = world.getEntitiesWithinAABB(EntityXPOrb.class, bb);
				List<EntityCreeper> creepers = world.getEntitiesWithinAABB(EntityCreeper.class, bb);
				List<EntityTNTPrimed> tnt = world.getEntitiesWithinAABB(EntityTNTPrimed.class, bb);
				
				bb = AxisAlignedBB.getBoundingBox(player.posX - 10.0F * 10.0F, player.posY - 10.0F * 10.0F, player.posZ - 10.0F * 10.0F, player.posX + 10.0F * 10.0F, player.posY + 10.0F * 10.0F, player.posZ + 20.0D);

				List<EntityArrow> arrows = world.getEntitiesWithinAABB(EntityArrow.class, bb);
				List<EntitySmallFireball> fireBallSmall =  world.getEntitiesWithinAABB(EntitySmallFireball.class, bb);
				List<EntityLargeFireball> fireBallLarge = world.getEntitiesWithinAABB(EntityLargeFireball.class, bb);
				
				entities.addAll(itemEntities);
				entities.addAll(xpOrbs);
				entities.addAll(creepers);
				entities.addAll(arrows);
				entities.addAll(fireBallLarge);
				entities.addAll(fireBallSmall);
				entities.addAll(tnt);

				if ((entities != null) && (entities.size() > 0)) 
				{
					for (Object obj : entities)
					{
						if ((obj instanceof Entity))
						{
							Entity item = (Entity)obj;

							float modifier = 0.075F;
							
							if ((obj instanceof EntityArrow))
								modifier += .2F;

							if (item.posX < player.posX) 
							{
								item.motionX += modifier;
							}
							else
								item.motionX -= modifier;

							if (item.posY < player.posY)
							{
								item.motionY += modifier;
							}
							else
								item.motionY -= modifier;

							if (item.posZ < player.posZ)
							{
								item.motionZ += modifier;
							}
							else
								item.motionZ -= modifier;
						}
					}
				}
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{

		par3List.add(EnumChatFormatting.GOLD + "Shift-Right Click to Toggle Magnetism");
		if (par1ItemStack.stackTagCompound != null)
		{
			if(par1ItemStack.stackTagCompound.getBoolean("Active"))
				par3List.add(EnumChatFormatting.GREEN + "Active");
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}

}
