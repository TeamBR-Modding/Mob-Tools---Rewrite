package com.pauljoda.mobtools.rendering;

import org.lwjgl.opengl.GL11;

import com.pauljoda.mobtools.tileentities.TileEntityEnderPackage;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class TileEntityEnderPackageItemRenderer implements IItemRenderer {

	private ModelChest chest;

	public TileEntityEnderPackageItemRenderer()
	{
		chest = new ModelChest();
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
			renderEnderPackage(0.5F, 0.5F, 0.5F, item.getItemDamage());
			break;
		}
        case EQUIPPED:
        {
        	renderEnderPackage(1.0F, 1.0F, 1.0F, item.getItemDamage());
            break;
        }
        case EQUIPPED_FIRST_PERSON:
        {
        	renderEnderPackage(1.0F, 1.0F, 1.0F, item.getItemDamage());
            break;
        }
        case INVENTORY:
        {
        	renderEnderPackage(0.0F, 0.075F, 0.0F, item.getItemDamage());
            break;
        }
        default:
            break;
    }
}

	public void renderEnderPackage(float x, float y, float z, int metaData)
	{
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("mobtools:textures/entity/enderPackage.png"));

		GL11.glPushMatrix(); //start
		GL11.glTranslatef(x, y, z); //size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		chest.renderAll();
		GL11.glPopMatrix(); //end
	}
}
