package com.pauljoda.mobtools.rendering;

import org.lwjgl.opengl.GL11;

import com.google.common.primitives.SignedBytes;
import com.pauljoda.mobtools.tileentities.TileEntityInfusingFurnace;
import com.pauljoda.mobtools.tileentities.TileEntityRepairAlter;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityInfuserRenderer extends TileEntitySpecialRenderer {
	private static final ResourceLocation location = new ResourceLocation("mobtools:textures/entity/infuser.png");
	private ModelInfuser infuser = new ModelInfuser();
	private RenderItem itemRenderer;

	public TileEntityInfuserRenderer()
	{
		itemRenderer = new RenderItem() {
			@Override
			public byte getMiniBlockCount(ItemStack stack, byte original) {
				return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 15) + 1);
			}
			@Override
			public byte getMiniItemCount(ItemStack stack, byte original) {
				return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 7) + 1);
			}
			@Override
			public boolean shouldBob() {
				return false;
			}
			@Override
			public boolean shouldSpreadItems() {
				return false;
			}
		};

		itemRenderer.setRenderManager(RenderManager.instance);
	}

	public void renderTileEntityAt(TileEntityInfusingFurnace tile, double par2, double par4, double par6, float par8)
	{
		this.bindTexture(location);
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, -.5F, 0.5F);

		infuser.renderAll();

		GL11.glDisable(32826);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


		//Render Items
		GL11.glPushMatrix();
		GL11.glDisable(2896 /* GL_LIGHTING */);
		EntityItem customitem = new EntityItem(tile.getWorldObj());
		customitem.hoverStart = 0f;

		if(tile.getStackInSlot(1) != null && tile.getStackInSlot(2) == null)
		{
			ItemStack item = tile.getStackInSlot(1);
			float f1 = 0.4375F;
			float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2 + .5F, (float) par4 + 1.6F, (float) par6 + .5F);
			GL11.glScalef(f1 + 0.5F, f1+ 0.5F, f1+ 0.5F);
			GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);

			customitem.setEntityItemStack(item);
			itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);

			GL11.glPopMatrix();
		}

		else if(tile.getStackInSlot(2) != null)
		{
			ItemStack item = tile.getStackInSlot(2);
			float f1 = 0.4375F;
			float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2 + .5F, (float) par4 + 1.6F, (float) par6 + .5F);
			GL11.glScalef(f1 + 0.5F, f1+ 0.5F, f1+ 0.5F);
			GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);

			customitem.setEntityItemStack(item);
			itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);

			GL11.glPopMatrix();
		}

		if(tile.getStackInSlot(0) != null)
		{
			ItemStack item = tile.getStackInSlot(0);
			float f1 = 0.4375F;
			float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

			GL11.glPushMatrix();

			float f6 = timeD * (float)Math.PI * -0.06F;

			GL11.glTranslatef((float) par2 + (MathHelper.cos(f6) / 2 )+ .5F, (float) par4 + 1.6F, (float) par6 + (MathHelper.sin(f6) / 2 )+ .5F);
			GL11.glScalef(f1 + 0.2F, f1+ 0.2F, f1+ 0.2F);
			GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);

			customitem.setEntityItemStack(item);
			itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);

			GL11.glPopMatrix();
		}


		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
	{
		this.renderTileEntityAt((TileEntityInfusingFurnace)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
	}

}
