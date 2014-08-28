package com.pauljoda.mobtools.network;

import com.pauljoda.mobtools.containers.ContainerEnderMail;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class MailPacket extends AbstractPacket {

	String recipient;

	public MailPacket() {}

	public MailPacket(String recipient)
	{
		this.recipient = recipient;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		ByteBufUtils.writeUTF8String(buffer, recipient);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		recipient = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
	}


	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		
		EntityPlayerMP player1 = (EntityPlayerMP)player;
		ContainerEnderMail enderMail = (ContainerEnderMail)player.openContainer;

		if(player.inventory.getCurrentItem().stackSize > 1)
		{
			if(enderMail.spawnItemStack(recipient, player.getCommandSenderName()))
				--player.inventory.getCurrentItem().stackSize;
		}

		if(player.inventory.getCurrentItem().stackSize == 1)
		{
			if(enderMail.spawnItemStack(recipient, player.getCommandSenderName()))
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				player1.closeScreen();
				return;
			}
		}
	}

}
