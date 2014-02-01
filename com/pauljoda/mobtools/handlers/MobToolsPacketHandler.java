package com.pauljoda.mobtools.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.pauljoda.mobtools.containers.ContainerEnderMail;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class MobToolsPacketHandler implements IPacketHandler {

	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
		int id = dat.readInt();

		if(id == 0)
		{
			String userName = dat.readUTF();
			EntityPlayer ep = (EntityPlayer)player;
			ContainerEnderMail mail = (ContainerEnderMail)ep.openContainer;
			if(ep.inventory.getCurrentItem().stackSize > 1)
			{
				if(mail.spawnItemStack(userName, ep.username))
					--ep.inventory.getCurrentItem().stackSize;
			}

			if(ep.inventory.getCurrentItem().stackSize == 1)
			{
				if(mail.spawnItemStack(userName, ep.username))
				{
					ep.inventory.setInventorySlotContents(ep.inventory.currentItem, null);
					ep.closeScreen();
					return;
				}
			}

		}
	}

	public static Packet sendItems(String user)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try
		{
			dos.writeInt(0);
			dos.writeUTF(user);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "Mob Tools";
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		return pkt;
	}


}
