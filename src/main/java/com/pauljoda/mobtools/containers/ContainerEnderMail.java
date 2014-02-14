package com.pauljoda.mobtools.containers;

import java.util.List;

import com.pauljoda.mobtools.handlers.GeneralSettings;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ContainerEnderMail extends Container {

	World world;
	InventoryMail mail;
	public ContainerEnderMail(InventoryPlayer inPlayer, InventoryMail mail, World world)
	{
		this.world = world;
		this.mail = mail;

		addSlotToContainer(new Slot(mail, 0, 80, 51));
		bindPlayerInventory(inPlayer);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);

		if (!this.world.isRemote)
		{
			ItemStack itemstack = mail.getStackInSlot(0);
			if(itemstack != null)
			{
				par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
			}
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) 
	{
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		//null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			if(slot == 0)
			{

				if (!this.mergeItemStack(stackInSlot, 0, 35, true))
				{
					return null;
				}
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}


	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	public boolean spawnItemStack(String userName, String sender)
	{
		if(mail.getStackInSlot(0) != null)
		{
			List<EntityPlayerMP> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
			for(int i = 0; i < players.size(); i++)
			{
				EntityPlayerMP recipient = players.get(i);
				System.out.println(recipient.getDisplayName() + " is it " + userName);
				if(recipient.getDisplayName().equals(userName))
				{
					System.out.println("Yes");
					ItemStack mailContents = mail.getStackInSlot(0);
					EntityItem itemstack = new EntityItem(recipient.worldObj, recipient.posX, recipient.posY + 2, recipient.posZ, mailContents);
					recipient.worldObj.spawnEntityInWorld(itemstack);
					mail.setInventorySlotContents(0, null);
					recipient.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + sender + " has sent you " + mailContents.stackSize + " " + mailContents.getDisplayName()));
					return true;
				}	
			}
		}
		return false;
	}

}
