package com.pauljoda.mobtools.amulets;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.rendering.ParticleHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MobToolsTeleportAmulet extends MobToolsAmulet {

	public MobToolsTeleportAmulet() {
		super();
		this.setUnlocalizedName("teleportAmulet");
		this.setCreativeTab(MobTools.tabMobTools);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World par2World, EntityPlayer player)
	{

		if(player.isSneaking())
		{
			if(itemstack.stackTagCompound == null)
			{
				itemstack.stackTagCompound = new NBTTagCompound();
				if(!par2World.isRemote)
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Location Set"));
				itemstack.stackTagCompound.setInteger("XCoord", (int)player.posX);
				itemstack.stackTagCompound.setInteger("YCoord", (int)player.posY);
				itemstack.stackTagCompound.setInteger("ZCoord", (int)player.posZ);
				itemstack.stackTagCompound.setString("World ID", player.worldObj.provider.getDimensionName());
			}
		}

		else if(itemstack.stackTagCompound != null)
		{
			if(player.worldObj.provider.getDimensionName().equals(itemstack.stackTagCompound.getString("World ID")))
			{
				int x = itemstack.stackTagCompound.getInteger("XCoord");
				int y = itemstack.stackTagCompound.getInteger("YCoord");
				int z = itemstack.stackTagCompound.getInteger("ZCoord");
				player.setPositionAndUpdate((double)x, (double)y, (double)z);
				ParticleHelper.spawnEnderCircles(par2World, x, y, z);
				player.playSound("mob.endermen.portal", 1F, 1F);

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
			}

			else
			{
				if(!par2World.isRemote)
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Cannot Teleport to different Dimension"));
			}

		}

		return itemstack;
	}



	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{

		if (par1ItemStack.stackTagCompound == null)
			par3List.add(EnumChatFormatting.GOLD + "Shift-Right Click to Set Location");


		if (par1ItemStack.stackTagCompound != null)
		{
			par3List.add(EnumChatFormatting.GOLD + "Right Click to Telport to Location");
			par3List.add(EnumChatFormatting.DARK_AQUA + "World ID: " +par1ItemStack.stackTagCompound.getString("World ID"));
			par3List.add(EnumChatFormatting.DARK_AQUA + "X-Coord: " + par1ItemStack.stackTagCompound.getInteger("XCoord"));
			par3List.add(EnumChatFormatting.DARK_AQUA + "Y-Coord: " + par1ItemStack.stackTagCompound.getInteger("YCoord"));
			par3List.add(EnumChatFormatting.DARK_AQUA + "X-Coord: " + par1ItemStack.stackTagCompound.getInteger("ZCoord"));
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("mobtools:" + (this.getUnlocalizedName().substring(5)));
	}

}
