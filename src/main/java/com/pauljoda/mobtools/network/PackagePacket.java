package com.pauljoda.mobtools.network;

import com.pauljoda.mobtools.containers.ContainerEnderPackage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PackagePacket extends AbstractPacket {

	String recipient;

	public PackagePacket() {}

	public PackagePacket(String recipient)
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
		ContainerEnderPackage enderMail = (ContainerEnderPackage)player.openContainer;
		
		if(enderMail.setNewPackage(recipient, player.getCommandSenderName()))
		{
			player.addChatComponentMessage(new ChatComponentText("Package Sent"));
		}
	}
}