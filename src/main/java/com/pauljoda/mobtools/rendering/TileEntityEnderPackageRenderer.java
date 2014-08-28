package com.pauljoda.mobtools.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.primitives.SignedBytes;
import com.pauljoda.mobtools.tileentities.TileEntityEnderPackage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityEnderPackageRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_147520_b = new ResourceLocation("mobtools:textures/entity/enderPackage.png");
	private ModelChest chest = new ModelChest();
	private RenderItem itemRenderer;
	private static final String __OBFID = "CL_00000967";

	public TileEntityEnderPackageRenderer()
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

	public void renderTileEntityAt(TileEntityEnderPackage tile, double par2, double par4, double par6, float par8)
	{
		int meta = 0;

		if (tile.hasWorldObj())
		{
			meta = tile.getBlockMetadata();
		}

		this.bindTexture(field_147520_b);
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short var11 = 0;
		if (meta == 2) {
			var11 = 180;
		}
		if (meta == 3) {
			var11 = 0;
		}
		if (meta == 4) {
			var11 = 90;
		}
		if (meta == 5) {
			var11 = -90;
		}
		GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float var12 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * par8;
		var12 = 1.0F - var12;
		var12 = 1.0F - var12 * var12 * var12;
		chest.chestLid.offsetY = (-(var12 * 3.141593F / 4.0F));
		chest.chestKnob.offsetY = (-(var12 * 3.141593F / 4.0F));
		chest.renderAll();
		GL11.glDisable(32826);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		//Render Items
		GL11.glPushMatrix();
		GL11.glDisable(2896 /* GL_LIGHTING */);
		EntityItem customitem = new EntityItem(tile.getWorldObj());
		customitem.hoverStart = 0f;
		
		float f1 = 0.4375F;
		float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

		GL11.glTranslatef((float) par2 + .5F, (float) (par4 + .15F) + ((var12 * 3.141593F / 4.0F)), (float) par6 + .5F);
		GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
		
		customitem.setEntityItemStack(new ItemStack(Items.ender_eye));
		itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);

	
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
	{
		this.renderTileEntityAt((TileEntityEnderPackage)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
	}
}