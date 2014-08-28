package com.pauljoda.mobtools.rendering;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityRepairAlterItemRenderer implements IItemRenderer 
{
	private ModelRepairAlter repairAlter;
	
	public TileEntityRepairAlterItemRenderer()
	{
		repairAlter = new ModelRepairAlter();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch (type)
		{
		case ENTITY:
		{
			renderRepairAlter(0.5F, 0.5F, 0.5F, item.getItemDamage());
			break;
		}
        case EQUIPPED:
        {
        	renderRepairAlter(1.0F, 1.0F, 1.0F, item.getItemDamage());
            break;
        }
        case EQUIPPED_FIRST_PERSON:
        {
        	renderRepairAlter(1.0F, 1.0F, 1.0F, item.getItemDamage());
            break;
        }
        case INVENTORY:
        {
        	renderRepairAlter(0.0F, 0.075F, 0.0F, item.getItemDamage());
            break;
        }
        default:
            break;
    }
}

	public void renderRepairAlter(float x, float y, float z, int metaData)
	{
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("mobtools:textures/entity/repairAlter.png"));

		GL11.glPushMatrix(); //start
		GL11.glTranslatef(x, y, z); //size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		repairAlter.renderAll();
		GL11.glPopMatrix(); //end
	}
}