package com.pauljoda.mobtools.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.pauljoda.mobtools.MobTools;
import com.pauljoda.mobtools.containers.ContainerEnderMail;
import com.pauljoda.mobtools.item.ItemManager;
import com.pauljoda.mobtools.network.MailPacket;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnderMail extends GuiContainer {

	boolean active;
	public boolean isPlayerOnline;
	public GuiTextField recipient;
	List<String> matchingPlayers = new ArrayList();
	int position = 0;
	private static final ResourceLocation field_110410_t = new ResourceLocation("mobtools:textures/enderMail.png");
	private static final ResourceLocation valid = new ResourceLocation("textures/gui/widgets.png");

	public GuiEnderMail(ContainerEnderMail mail)
	{
		super(mail);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		final String invTitle = "Ender Mail";
		final String recipient = "Recipient's Name";
		final String itemToSend = "Items To Send:";
		fontRendererObj.drawString(invTitle, xSize / 2 - fontRendererObj.getStringWidth(invTitle) / 2, 6, 4210752);
		fontRendererObj.drawString(recipient, 7, 23, 4210752);
		fontRendererObj.drawString(itemToSend, 7, 55, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
		GL11.glColor4f(1f, 1f, 1f, 1f);

		this.mc.getTextureManager().bindTexture(field_110410_t);

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		this.mc.getTextureManager().bindTexture(valid);
		if(!isPlayerOnline)
			drawTexturedModalRect(x + 99, y + 31, 192, 0, 15, 15);
		else
			drawTexturedModalRect(x + 99, y + 31, 208, 0, 15, 15);

	}

	protected void mouseClicked (int mouseX, int mouseY, int mouseButton)
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (mouseButton == 0)
		{
			int x = (width - xSize) / 2;
			int y = (height - ySize) / 2;

			int gLeft = x + 8;
			int gTop = y + 33;
			int gWidth = 87;
			int gHeight = 10;
			active = mouseX > gLeft && mouseX < (gLeft + gWidth) && mouseY > gTop && mouseY < (gTop + gHeight);
			this.recipient.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}

	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.recipient.drawTextBox();
	}


	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();

		Keyboard.enableRepeatEvents(true);

		this.buttonList.clear();

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		this.buttonList.add(new GuiButton(0, x + 99, y + 50, 30, 20, "Send"));

		this.recipient = new GuiTextField(fontRendererObj, x + 10, y + 35, 75, 17);
		this.recipient.setTextColor(-1);
		this.recipient.setDisabledTextColour(-1);
		this.recipient.setEnableBackgroundDrawing(false);
		this.recipient.setMaxStringLength(15);

	}

	@Override
	public void actionPerformed(GuiButton button)
	{

		switch(button.id)
		{
		case 0 :
			if(isPlayerOnline)
			{
				EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().thePlayer;
				if(player.inventory.getCurrentItem() != null)
				{
					if(player.inventory.getCurrentItem().getItem() == ItemManager.enderMail)
					{
						MailPacket packet = new MailPacket(recipient.getText());
						MobTools.packetPipeline.sendToServer(packet);
					}
				}
			}
			break;

		}
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void keyTyped(char par1, int par2)
	{
		if(!active)
		{
			super.keyTyped(par1, par2);
			return;
		}

		if(par2 == 15)
		{
			completePlayers();
		}
		else
		{
			matchingPlayers.clear();
		}
		this.recipient.textboxKeyTyped(par1, par2);
		updatePlayer();
	}

	public void updatePlayer()
	{
		if ((!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1))
		{
			this.mc.mcProfiler.startSection("playerList");
			NetHandlerPlayClient netclienthandler = this.mc.thePlayer.sendQueue;
			List list = netclienthandler.playerInfoList;

			for(int i = 0; i < list.size(); i++)
			{
				GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo)list.get(i);
				if(guiplayerinfo.name.equals(this.recipient.getText()))
				{
					isPlayerOnline = true;
					return;
				}
				else
					isPlayerOnline = false;
			}
		}
	}

	public void completePlayers()
	{
		String starting;
		if(recipient.getText() != null)
		{
			starting = recipient.getText();
			if ((!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1))
			{
				this.mc.mcProfiler.startSection("playerList");
				NetHandlerPlayClient netclienthandler = this.mc.thePlayer.sendQueue;
				List list = netclienthandler.playerInfoList;

				for(int i = 0; i < list.size(); i++)
				{
					GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo)list.get(i);
					if(guiplayerinfo.name.toLowerCase().contains(starting.toLowerCase()))
					{
						matchingPlayers.add(guiplayerinfo.name);
					}
				}
			}
		}

		if(matchingPlayers.size() > 0)
		{
			++position;
			if(position >= matchingPlayers.size())
				position = 0;
			recipient.setText(matchingPlayers.get(position));

		}

	}

}
