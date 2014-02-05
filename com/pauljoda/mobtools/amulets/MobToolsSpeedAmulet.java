package com.pauljoda.mobtools.amulets;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.pauljoda.mobtools.MobTools;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MobToolsSpeedAmulet extends MobToolsAmulet {

	int cooldown;
	public MobToolsSpeedAmulet(int par1) {
		super(par1);
		this.setUnlocalizedName("speedAmulet");
		this.setCreativeTab(MobTools.tabMobTools);
		this.setMaxDamage(300);
		cooldown = 20;
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
					player.addChatMessage(EnumChatFormatting.GREEN + "Speed Enabled");
			}
			else
			{
				if(itemstack.stackTagCompound.getBoolean("Active"))
				{
					itemstack.stackTagCompound.setBoolean("Active", false);
					if(!par2World.isRemote)
						player.addChatMessage(EnumChatFormatting.RED + "Speed Disabled");
				}
				else
				{
					itemstack.stackTagCompound.setBoolean("Active", true);
					if(!par2World.isRemote)
						player.addChatMessage(EnumChatFormatting.GREEN + "Speed Enabled");
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
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 4, true));
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 2, true));
				if(cooldown < 0)
				{
					cooldown = 20;
					itemstack.damageItem(1, player);
				}
			}

		}
		cooldown--;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{

		par3List.add(EnumChatFormatting.GOLD + "Shift-Right Click to Toggle Speed");
		if (par1ItemStack.stackTagCompound != null)
		{
			if(par1ItemStack.stackTagCompound.getBoolean("Active"))
				par3List.add(EnumChatFormatting.GREEN + "Active");
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}

}
