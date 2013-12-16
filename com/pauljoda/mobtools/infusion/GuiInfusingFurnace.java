package com.pauljoda.mobtools.infusion;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiInfusingFurnace extends GuiContainer {
    
    private TileEntityInfusingFurnace furnaceInventory;
    private static final ResourceLocation field_110410_t = new ResourceLocation("mobtools:textures/infuser.png");
    
    public GuiInfusingFurnace(InventoryPlayer par1InventoryPlayer, TileEntityInfusingFurnace tileEntity)
    {
        super(new ContainerInfusingFurnace(par1InventoryPlayer, tileEntity));
        this.furnaceInventory = tileEntity;
    }
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    	String s = this.furnaceInventory.isInvNameLocalized() ? this.furnaceInventory.getInvName() : I18n.getString(this.furnaceInventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_110410_t);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        var7 = this.furnaceInventory.getCookProgressScaled(82);
        this.drawTexturedModalRect(var5 + 46, var6 + 34, 0, 166, var7 + 1, 16);
    }
}
