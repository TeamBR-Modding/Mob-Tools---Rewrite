package com.pauljoda.mobtools.gui;

import org.lwjgl.opengl.GL11;

import com.pauljoda.mobtools.containers.ContainerRepairAlter;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRepairAlter extends GuiContainer {
	
    private static final ResourceLocation field_110410_t = new ResourceLocation("mobtools:textures/repairAlter.png");

    private TileEntityRepairAlter alter;
	public GuiRepairAlter(TileEntityRepairAlter alter, InventoryPlayer inventoryPlaer) {
		super(new ContainerRepairAlter(inventoryPlaer, alter));
		this.alter = alter;
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
		final String invTitle = "Repair Alter";
        fontRenderer.drawString(invTitle, xSize / 2 - fontRenderer.getStringWidth(invTitle) / 2, 6, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
        GL11.glColor4f(1f, 1f, 1f, 1f);
        
        this.mc.getTextureManager().bindTexture(field_110410_t);
        
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
